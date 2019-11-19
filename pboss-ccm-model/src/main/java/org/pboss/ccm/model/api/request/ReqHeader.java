/**
 * 
 */
package org.pboss.ccm.model.api.request;

import java.time.OffsetDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;

/**
 * @author gregf
 *
 * TODO: Replace the Date object with Java8 OffsetDateTime
 *
 */
@JsonTypeName("req_header")
@JsonInclude(Include.NON_NULL)
public class ReqHeader {
	
	//@JsonProperty("request_timestamp")
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ssX", timezone="GMT+2")
    //private Date requestTimestamp;
	
	@JsonProperty("request_timestamp")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ssX", timezone="GMT+2")
	@JsonSerialize(using = OffsetDateTimeSerializer.class)
    private OffsetDateTime requestTimestamp;
	
	@JsonProperty("api_version")
    private String apiVersion;
	
	@JsonProperty("corr_id")
    private String corrId;
	
	@JsonProperty("test_level")
    private int testLevel=0;
	
	@JsonProperty("api_credentials")
    private ApiCredentials apiCredentials;
    
	@JsonProperty("system_instance_id")
    private String systemInstanceId;
	
	//public Date getRequestTimestamp() {
	//	return requestTimestamp;
	//}
	
	//public void setRequestTimestamp(Date requestTimestamp) {
	//	this.requestTimestamp = requestTimestamp;
	//}


	public OffsetDateTime getRequestTimestamp() {
		return requestTimestamp;
	}

	public void setRequestTimestamp(OffsetDateTime requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
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
	
	public int getTestLevel() {
		return testLevel;
	}
	
	public void setTestLevel(int testLevel) {
		this.testLevel = testLevel;
	}
	
	public ApiCredentials getApiCredentials() {
		return apiCredentials;
	}
	
	public void setApiCredentials(ApiCredentials apiCredentials) {
		this.apiCredentials = apiCredentials;
	}

	public String getSystemInstanceId() {
		return systemInstanceId;
	}

	public void setSystemInstanceId(String systemInstanceId) {
		this.systemInstanceId = systemInstanceId;
	}

	@Override
	public String toString() {
		return "C3mReqHeader [requestTimestamp=" + requestTimestamp + ", apiVersion=" + apiVersion + ", corrId="
				+ corrId + ", testLevel=" + testLevel + ", apiCredentials=" + apiCredentials + ", systemInstanceId="
				+ systemInstanceId + "]";
	}

}
