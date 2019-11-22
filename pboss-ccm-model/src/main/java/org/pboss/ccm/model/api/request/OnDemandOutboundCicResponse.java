/**
 * 
 */
package org.pboss.ccm.model.api.request;

import org.pboss.ccm.model.api.request.ValidationResult;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gregf
 *
 */
public class OnDemandOutboundCicResponse {
	
	@JsonProperty("resp_header")
	private RespHeader respHeader;
	
	@JsonProperty("validation_result")
	private ValidationResult validationResult;

	public RespHeader getRespHeader() {
		return respHeader;
	}

	public void setRespHeader(RespHeader respHeader) {
		this.respHeader = respHeader;
	}

	public ValidationResult getValidationResult() {
		return validationResult;
	}

	public void setValidationResult(ValidationResult validationResult) {
		this.validationResult = validationResult;
	}

	@Override
	public String toString() {
		return "OnDemandOutboundCicResponse [respHeader=" + respHeader + "]";
	}
}
