/**
 * 
 */
package org.pboss.ccm.model.creation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author gregf
 *
 */
@JsonTypeName("create_resp")
@JsonInclude(Include.NON_NULL)
public class CreateResp {
	
	@JsonProperty("resp_code")
	int respCode;
	
	@JsonProperty("resp_msg")
	String respMsg;

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

	@Override
	public String toString() {
		return "CreateResp [respCode=" + respCode + ", respMsg=" + respMsg + "]";
	}

}
