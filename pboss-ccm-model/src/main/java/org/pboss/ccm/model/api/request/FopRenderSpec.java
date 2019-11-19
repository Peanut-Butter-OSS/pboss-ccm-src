/**
 * 
 */
package org.pboss.ccm.model.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author gregf
 *
 */
@JsonTypeName("fop_render_spec")
@JsonInclude(Include.NON_NULL)
public class FopRenderSpec extends RenderSpec {
	
	@JsonProperty("runtime_template")
	private String runtimeTemplate;
	
	@JsonProperty("payload_type")
	private PayloadType payloadType;

	public String getRuntimeTemplate() {
		return runtimeTemplate;
	}

	public void setRuntimeTemplate(String runtimeTemplate) {
		this.runtimeTemplate = runtimeTemplate;
	}
	
	public PayloadType getPayloadType() {
		return payloadType;
	}

	public void setPayloadType(PayloadType payloadType) {
		this.payloadType = payloadType;
	}
}
