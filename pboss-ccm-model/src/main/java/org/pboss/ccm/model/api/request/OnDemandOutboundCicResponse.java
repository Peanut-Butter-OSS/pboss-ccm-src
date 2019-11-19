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
	private RespHeader c3mRespHeader;
	
	@JsonProperty("validation_result")
	private ValidationResult validationResult;

	public RespHeader getC3mRespHeader() {
		return c3mRespHeader;
	}

	public void setC3mRespHeader(RespHeader c3mRespHeader) {
		this.c3mRespHeader = c3mRespHeader;
	}

	public ValidationResult getValidationResult() {
		return validationResult;
	}

	public void setValidationResult(ValidationResult validationResult) {
		this.validationResult = validationResult;
	}

	@Override
	public String toString() {
		return "OnDemandOutboundCicResponse [c3mRespHeader=" + c3mRespHeader + "]";
	}
}
