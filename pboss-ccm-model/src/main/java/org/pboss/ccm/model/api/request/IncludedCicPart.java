/**
 * 
 */
package org.pboss.ccm.model.api.request;

import org.pboss.ccm.model.creation.IncludeResp;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author gregf
 *
 */
@JsonTypeName("included_cic_part")
@JsonInclude(Include.NON_NULL)
public class IncludedCicPart extends CicPart {
	
	@JsonProperty("include_spec")
	private IncludeSpec includeSpec;

	@JsonProperty("include_resp")
	private IncludeResp includeResp;
	
	public IncludeSpec getIncludeSpec() {
		return includeSpec;
	}

	public void setIncludeSpec(IncludeSpec includeSpec) {
		this.includeSpec = includeSpec;
	}

	public IncludeResp getIncludeResp() {
		return includeResp;
	}

	public void setIncludeResp(IncludeResp includeResp) {
		this.includeResp = includeResp;
	}
	
}
