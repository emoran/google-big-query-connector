package org.mule.modules.googlebigquery;

import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;

import org.mule.modules.googlebigquery.config.ConnectorConfig;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;

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
    			            // Use standard SQL syntax for queries.
    			            // See: https://cloud.google.com/bigquery/sql-reference/
    			            .setUseLegacySql(false)
    			            .build();

    			    // Create a job ID so that we can safely retry.
    			    JobId jobId = JobId.of(UUID.randomUUID().toString());
    			    Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

    			    // Wait for the query to complete.
    			    queryJob = queryJob.waitFor();
    			    
    			    // Check for errors
    			    if (queryJob == null) {
    			      throw new RuntimeException("Job no longer exists");
    			    } else if (queryJob.getStatus().getError() != null) {
    			      // You can also look at queryJob.getStatus().getExecutionErrors() for all
    			      // errors, not just the latest one.
    			      throw new RuntimeException(queryJob.getStatus().getError().toString());
    			    }

    			    // Get the results.
    			    result = queryJob.getQueryResults();
    		}
    		catch(Exception er) {
    			System.out.println(er.getMessage());
    		}
    		
    		return result;
    }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

}