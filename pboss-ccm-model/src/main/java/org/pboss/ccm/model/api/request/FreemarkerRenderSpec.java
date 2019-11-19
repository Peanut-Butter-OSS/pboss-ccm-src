/**
 * 
 */
package org.pboss.ccm.model.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author gregf
 *
 */
@JsonTypeName("freemarker_render_spec")
@JsonInclude(Include.NON_NULL)
public class FreemarkerRenderSpec extends RenderSpec {
	
	@JsonProperty("runtime_template")
	private String runtimeTemplate;
	
	@JsonProperty("runtime_template_content_type")
	private String runtimeTemplateContentType;	
	
	@JsonProperty("runtime_template_target_content_type")
	private String runtimeTemplateTargetContentType;	
	
	@JsonProperty("runtime_template_encoding")
	private String runtimeTemplateEncoding;	
	
	@JsonProperty("locale")
	private String locale;		
	
	@JsonProperty("template_name")
	private String templateName;
	
	@JsonProperty("template_version")
	private String templateVersion;
	
	@JsonProperty("payload_type")
	private PayloadType payloadType;

	public String getRuntimeTemplate() {
		return runtimeTemplate;
	}

	public void setRuntimeTemplate(String runtimeTemplate) {
		this.runtimeTemplate = runtimeTemplate;
	}
	
	public String getRuntimeTemplateContentType() {
		return runtimeTemplateContentType;
	}

	public void setRuntimeTemplateContentType(String runtimeTemplateContentType) {
		this.runtimeTemplateContentType = runtimeTemplateContentType;
	}
	
	public String getRuntimeTemplateTargetContentType() {
		return runtimeTemplateTargetContentType;
	}

	public void setRuntimeTemplateTargetContentType(String runtimeTemplateTargetContentType) {
		this.runtimeTemplateTargetContentType = runtimeTemplateTargetContentType;
	}

	public String getRuntimeTemplateEncoding() {
		return runtimeTemplateEncoding;
	}

	public void setRuntimeTemplateEncoding(String runtimeTemplateEncoding) {
		this.runtimeTemplateEncoding = runtimeTemplateEncoding;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateVersion() {
		return templateVersion;
	}

	public void setTemplateVersion(String templateVersion) {
		this.templateVersion = templateVersion;
	}

	public PayloadType getPayloadType() {
		return payloadType;
	}

	public void setPayloadType(PayloadType payloadType) {
		this.payloadType = payloadType;
	}
	
}
