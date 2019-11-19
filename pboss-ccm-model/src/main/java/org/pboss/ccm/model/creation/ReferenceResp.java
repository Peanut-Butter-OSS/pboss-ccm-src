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
@JsonTypeName("reference_resp")
@JsonInclude(Include.NON_NULL)
public class ReferenceResp {
	
	@JsonProperty("req_guid")
	private String reqGuid;
	
	@JsonProperty("part_guid")
	String partGuid;
	
	@JsonProperty("part_id")
	String partId;
	
	@JsonProperty("content_uri")
	String contentUri;
	
	@JsonProperty("mime_type")
	String mimeType;
	
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

	public String getPartGuid() {
		return partGuid;
	}

	public void setPartGuid(String partGuid) {
		this.partGuid = partGuid;
	}

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getContentUri() {
		return contentUri;
	}

	public void setContentUri(String contentUri) {
		this.contentUri = contentUri;
	}
	
	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
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

	@Override
	public String toString() {
		return "ReferenceResp [reqGuid=" + reqGuid + ", partGuid=" + partGuid + ", partId=" + partId + ", contentUri="
				+ contentUri + ", mimeType=" + mimeType + ", respCode=" + respCode + ", respMsg=" + respMsg + "]";
	}

}
