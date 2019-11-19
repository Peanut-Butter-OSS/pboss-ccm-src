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
@JsonTypeName("include_spec")
@JsonInclude(Include.NON_NULL)
public class IncludeSpec {
	
	@JsonProperty("content")
	private String content;

	@JsonProperty("content_type")
	private String contentType;
	
	@JsonProperty("include_style")
	private IncludeStyle includeStyle;
	
	@JsonProperty("char_encoding")
	private String charEncoding;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public IncludeStyle getIncludeStyle() {
		return includeStyle;
	}

	public void setIncludeStyle(IncludeStyle includeStyle) {
		this.includeStyle = includeStyle;
	}

	public String getCharEncoding() {
		return charEncoding;
	}

	public void setCharEncoding(String charEncoding) {
		this.charEncoding = charEncoding;
	}
	
}
