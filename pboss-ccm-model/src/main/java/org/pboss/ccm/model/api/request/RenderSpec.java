/**
 * 
 */
package org.pboss.ccm.model.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author gregf
 *
 */
@JsonTypeName("render_spec")
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include=JsonTypeInfo.As.PROPERTY,property="render_type")
@JsonSubTypes({
@JsonSubTypes.Type(value=FreemarkerRenderSpec.class, name="freemarker_render_spec"),
@JsonSubTypes.Type(value=FopRenderSpec.class, name="fop_render_spec"),
@JsonSubTypes.Type(value=XsltRenderSpec.class, name="xslt_render_spec"),
@JsonSubTypes.Type(value=ItextRenderSpec.class, name="itext_render_spec"),
@JsonSubTypes.Type(value=EcrionRenderSpec.class, name="ecrion_render_spec"),
})
public class RenderSpec {

	@JsonProperty("render_payload")
	private String renderPayload;

	public String getRenderPayload() {
		return renderPayload;
	}

	public void setRenderPayload(String renderPayload) {
		this.renderPayload = renderPayload;
	}
	
	
	
}
