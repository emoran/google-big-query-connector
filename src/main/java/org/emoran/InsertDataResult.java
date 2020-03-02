package org.emoran;

import com.google.cloud.bigquery.InsertAllResponse;

public class InsertDataResult {
	public String resultMessage;
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public Boolean getHasErros() {
		return hasErros;
	}
	public void setHasErros(Boolean hasErros) {
		this.hasErros = hasErros;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public InsertAllResponse getFullresponse() {
		return fullresponse;
	}
	public void setFullresponse(InsertAllResponse fullresponse) {
		this.fullresponse = fullresponse;
	}
	public Boolean hasErros;
	public Boolean success;
	public InsertAllResponse fullresponse;
}
