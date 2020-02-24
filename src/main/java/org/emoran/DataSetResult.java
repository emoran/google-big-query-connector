package org.emoran;

public class DataSetResult {
	String datasetId;
	public String getDatasetId() {
		return datasetId;
	}
	public void setDatasetId(String datasetId) {
		this.datasetId = datasetId;
	}
	public String getDatasetName() {
		return dataSetName;
	}
	public void setDatasetName(String datasetName) {
		this.dataSetName = datasetName;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public Boolean getCreated() {
		return created;
	}
	public void setCreated(Boolean created) {
		this.created = created;
	}
	String dataSetName;
	String resultMessage;
	Boolean created;
}
