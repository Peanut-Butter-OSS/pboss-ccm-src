/**
 * 
 */
package org.pboss.ccm.model.delivery;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author gregf
 *
 */
@JsonTypeName("email_resp")
@JsonInclude(Include.NON_NULL)
public class EmailResp {
	
	@JsonProperty("req_guid")
	private String reqGuid;
	
	@JsonProperty("deliver_spec_guid")
	private String deliverSpecGuid;
	
	@JsonProperty("resp_code")
	int respCode;
	
	@JsonProperty("resp_msg")
	String respMsg;
	
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

	public String getDeliverSpecGuid() {
		return deliverSpecGuid;
	}

	public void setDeliverSpecGuid(String deliverSpecGuid) {
		this.deliverSpecGuid = deliverSpecGuid;
	}

	@Override
	public String toString() {
		return "EmailResp [reqGuid=" + reqGuid + ", deliverSpecGuid=" + deliverSpecGuid + ", respCode=" + respCode
				+ ", respMsg=" + respMsg + "]";
	}
}
