/**
 * 
 */
package org.pboss.ccm.model.api.request;

import org.pboss.ccm.model.creation.ReferenceResp;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author gregf
 *
 */
@JsonTypeName("referenced_cic_part")
public class ReferencedCicPart extends CicPart {
	
	@JsonProperty("reference_spec")
	private ReferenceSpec refSpec;

	@JsonProperty("reference_resp")
	private ReferenceResp refResp;
	
	public ReferenceSpec getRefSpec() {
		return refSpec;
	}

	public void setRefSpec(ReferenceSpec refSpec) {
		this.refSpec = refSpec;
	}

	public ReferenceResp getRefResp() {
		return refResp;
	}

	public void setRefResp(ReferenceResp refResp) {
		this.refResp = refResp;
	}
	
}
