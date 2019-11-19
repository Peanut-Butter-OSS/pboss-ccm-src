/**
 * 
 */
package org.pboss.ccm.model.api.request;

import org.pboss.ccm.model.creation.RenderResp;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author gregf
 *
 */
@JsonTypeName("rendered_cic_part")
public class RenderedCicPart extends CicPart {
	
	@JsonProperty("render_spec")
	RenderSpec renderSpec;
	
	@JsonProperty("rendered_doc_uri")
	String renderedDocUri;
	
	@JsonProperty("render_resp")
	RenderResp renderResp;

	public RenderSpec getRenderSpec() {
		return renderSpec;
	}

	public void setRenderSpec(RenderSpec renderSpec) {
		this.renderSpec = renderSpec;
	}

	public String getRenderedDocUri() {
		return renderedDocUri;
	}

	public void setRenderedDocUri(String renderedDocUri) {
		this.renderedDocUri = renderedDocUri;
	}

	public RenderResp getRenderResp() {
		return renderResp;
	}

	public void setRenderResp(RenderResp renderResp) {
		this.renderResp = renderResp;
	}

}
