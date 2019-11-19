/**
 * 
 */
package org.pboss.ccm.model.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author gregf
 *
 */
@JsonTypeName("custom_assembly_spec")
public class CustomAssemblySpec extends AssemblySpec {
	
	@JsonProperty("req_queue")
	String reqQueue;

	@JsonProperty("resp_queue")
	String respQueue;

	public String getReqQueue() {
		return reqQueue;
	}

	public void setReqQueue(String reqQueue) {
		this.reqQueue = reqQueue;
	}

	public String getRespQueue() {
		return respQueue;
	}

	public void setRespQueue(String respQueue) {
		this.respQueue = respQueue;
	}
	
}
