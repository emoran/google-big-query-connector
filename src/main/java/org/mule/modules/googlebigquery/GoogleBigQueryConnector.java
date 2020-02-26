package org.mule.modules.googlebigquery;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.emoran.CreateTable;
import org.emoran.DataSetResult;
import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;

import org.mule.modules.googlebigquery.config.ConnectorConfig;

import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableSchema;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryException;
import com.google.cloud.bigquery.Dataset;
import com.google.cloud.bigquery.DatasetInfo;
import com.google.cloud.bigquery.Field;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.LegacySQLTypeName;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.Schema;
import com.google.cloud.bigquery.StandardTableDefinition;
import com.google.cloud.bigquery.TableDefinition;
import com.google.cloud.bigquery.TableId;
import com.google.cloud.bigquery.TableInfo;
import com.google.cloud.bigquery.TableResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Connector(name="google-big-query", friendlyName="Unofficial Google Big Query", minMuleVersion="3.8")
public class GoogleBigQueryConnector {
	
	 private static final Log logger = LogFactory.getLog(GoogleBigQueryConnector.class);

    @Config
    ConnectorConfig config;
    
    BigQuery bigquery=null;

    /**
     * Custom processor
     * @param queryString  The query to execute against google big query
     * @return Query result
     */
    @Processor
    public TableResult Query(String queryString) {
    		bigquery = config.bigquery;	
    		TableResult result = null;    		
    		try {
    			QueryJobConfiguration queryConfig =
    			        QueryJobConfiguration.newBuilder(queryString)
    			            .setUseLegacySql(false)
    			            .build();

    			    JobId jobId = JobId.of(UUID.randomUUID().toString());
    			    Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());
    			    queryJob = queryJob.waitFor();
    			    if (queryJob == null) {
    			    		throw new RuntimeException("Job no longer exists");
    			    } 
    			    else if (queryJob.getStatus().getError() != null) {
    			    		throw new RuntimeException(queryJob.getStatus().getError().toString());
    			    }
    			    result = queryJob.getQueryResults();
    		}
    		catch(Exception er) {
    			System.out.println(er.getMessage());
    		}
    		
    		return result;
    }
    
    /**
     * Custom processor
     * @param datasetName  Name of the dataSet
     * @return DataSetResult
     */
    @Processor
    public DataSetResult createDataSet(String datasetName) {
    		bigquery = config.bigquery;
		DataSetResult result = new DataSetResult();
		try {
			
			DatasetInfo datasetInfo = DatasetInfo.newBuilder(datasetName).build();
			Dataset newDataset = bigquery.create(datasetInfo);
			String newDatasetName = newDataset.getDatasetId().getDataset();			
			result.setDatasetName(newDatasetName);
			result.setDatasetId(String.valueOf(newDataset.getDatasetId()));
			result.setResultMessage(newDatasetName + " created successfully");
			result.setCreated(true);
	    } 
		catch (BigQueryException e) {
			result.setResultMessage(e.getMessage());
			result.setCreated(false);
	    }		
		return result;
	}
    
    /**
     * Custom processor
     * @param tableName  Name of the table
     * @param datasetName Name of the existing dataset
     * @param jsonSchema Table schema fields
     * @return DataSetResult
     */
    @Processor
    public CreateTable createTable(String tableName,String datasetName,String jsonSchema) 
    		throws JsonMappingException, JsonProcessingException {	
    	
    		bigquery = config.bigquery;
    		CreateTable result = new CreateTable();
    	
	    	try {
	    		ObjectMapper mapper = new ObjectMapper();
            //String jsonSchema = "[{\"FieldName\":\"Name\",\"FieldType\":\"STRING\",\"fieldDescription\":\"This is name\",\"fieldMode\":\"NULLABLE\"},{\"FieldName\":\"EmployeeID\",\"FieldType\":\"STRING\",\"fieldDescription\":\"This is Employee Id\",\"fieldMode\":\"NULLABLE\"},{\"FieldName\":\"Age\",\"FieldType\":\"INTEGER\",\"fieldDescription\":\"This is Age\",\"fieldMode\":\"NULLABLE\"},{\"FieldName\":\"ContactNumber\",\"FieldType\":\"STRING\",\"fieldDescription\":\"This is Contact Number\",\"fieldMode\":\"NULLABLE\"},{\"FieldName\":\"Designation\",\"FieldType\":\"STRING\",\"fieldDescription\":\"This is Designation\",\"fieldMode\":\"NULLABLE\"},{\"FieldName\":\"Salary\",\"FieldType\":\"FLOAT\",\"fieldDescription\":\"This is Salary\",\"fieldMode\":\"NULLABLE\"}]";
            List<TableFieldSchema>  fieldList =  mapper.readValue(jsonSchema, new TypeReference<List<TableFieldSchema>>(){});

            List<Field> schemaFieldSingle = new ArrayList<Field>();

            for(TableFieldSchema f :fieldList){
              schemaFieldSingle.add(Field.of((String)f.get("FieldName"),getLegacy((String)f.get("FieldType"))));
            }

            Schema schema = Schema.of(schemaFieldSingle);

            TableId tableId = TableId.of(datasetName, tableName);
            TableDefinition tableDefinition = StandardTableDefinition.of(schema);
            TableInfo tableInfo = TableInfo.newBuilder(tableId, tableDefinition).build();

            com.google.cloud.bigquery.Table table = bigquery.create(tableInfo);
            
            result.setSuccess(true);
	    		result.setTableName(table.getGeneratedId());
	    		result.setResultMessage("Created");
	    	}
	    	catch(Exception er) {
	    		logger.info("Error: "+er.getMessage());
	    		result.setSuccess(false);
	    		result.setResultMessage(er.getMessage());
	    	}
    		
    		
		
    		return result;
	}
    
    public static LegacySQLTypeName getLegacy(String fieldType){

        if(fieldType.contains("STRING")){
          return LegacySQLTypeName.STRING;
        }
        else if(fieldType.contains("INTEGER")){
          return LegacySQLTypeName.INTEGER;
        }
        else if(fieldType.contains("FLOAT")){
          return LegacySQLTypeName.FLOAT;
        }
        else if(fieldType.contains("DATE")){
          return LegacySQLTypeName.DATE;
        }
        else if(fieldType.contains("DATETIME")){
          return LegacySQLTypeName.DATETIME;
        }
        else{
          return null;
        }

      }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

}