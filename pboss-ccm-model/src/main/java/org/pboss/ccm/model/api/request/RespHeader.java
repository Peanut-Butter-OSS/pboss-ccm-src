/**
 * 
 */
package org.pboss.ccm.model.api.request;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author gregf
 *
 */
@JsonTypeName("resp_header")
@JsonInclude(Include.NON_NULL)
public class RespHeader {
	
	//@JsonProperty("response_timestamp")
	//private String responseTimestamp;
	
	@JsonProperty("response_timestamp")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ssX", timezone="GMT+2")
	@JsonSerialize(using = OffsetDateTimeSerializer.class)
    private OffsetDateTime responseTimestamp;
	
	@JsonProperty("api_version")
    private String apiVersion;
	
	@JsonProperty("corr_id")
    private String corrId;	
	
	@JsonProperty("req_guid")
    private String reqGuid;	
	
	@JsonProperty("resp_code")
	private int respCode;

	@JsonProperty("resp_msg")
	private String respMsg;

	@JsonProperty("error_id")
	private Integer errorId;
	
	/*
	public String getResponseTimestamp() {
		return responseTimestamp;
	}

	public void setResponseTimestamp(String responseTimestamp) {
		this.responseTimestamp = responseTimestamp;
	}*/

	public OffsetDateTime getResponseTimestamp() {
		return responseTimestamp;
	}

	public void setResponseTimestamp(OffsetDateTime responseTimestamp) {
		this.responseTimestamp = responseTimestamp;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getCorrId() {
		return corrId;
	}

	public void setCorrId(String corrId) {
		this.corrId = corrId;
	}

	public String getReqGuid() {
		return reqGuid;
	}

	public void setReqGuid(String reqGuid) {
		this.reqGuid = reqGuid;
	}

	public int getRespCode() {
		return respCode;
	}

	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public Integer getErrorId() {
		return errorId;
	}

	public void setErrorId(Integer errorId) {
		this.errorId = errorId;
	}

	@Override
	public String toString() {
		return "C3mRespHeader [responseTimestamp=" + responseTimestamp + ", apiVersion=" + apiVersion + ", corrId="
				+ corrId + ", reqGuid=" + reqGuid + ", respCode=" + respCode + ", respMsg=" + respMsg + ", errorId="
				+ errorId + "]";
	}
	
}
