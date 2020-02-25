package org.emoran;

public class CreateTable {
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getDataSetName() {
		return dataSetName;
	}
	public void setDataSetName(String dataSetName) {
		this.dataSetName = dataSetName;
	}
	public String getCreateresult() {
		return createresult;
	}
	public void setCreateresult(String createresult) {
		this.createresult = createresult;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	String tableName;
	String dataSetName;
	String createresult;
	String resultMessage;
	Boolean success;
}
