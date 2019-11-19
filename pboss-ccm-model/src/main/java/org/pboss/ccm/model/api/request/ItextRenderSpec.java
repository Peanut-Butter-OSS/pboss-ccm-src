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
@JsonTypeName("itext_render_spec")
@JsonInclude(Include.NON_NULL)
public class ItextRenderSpec extends RenderSpec {
	
	@JsonProperty("runtime_template")
	private String runtimeTemplate;

	public String getRuntimeTemplate() {
		return runtimeTemplate;
	}

	public void setRuntimeTemplate(String runtimeTemplate) {
		this.runtimeTemplate = runtimeTemplate;
	}
	
}
