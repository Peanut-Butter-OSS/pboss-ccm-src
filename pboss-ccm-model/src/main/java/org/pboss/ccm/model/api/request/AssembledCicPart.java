/**
 * 
 */
package org.pboss.ccm.model.api.request;

import org.pboss.ccm.model.creation.AssemblyResp;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author gregf
 *
 */
@JsonTypeName("assembled_cic_part")
public class AssembledCicPart extends CicPart {
	
	@JsonProperty("assembly_spec")
	AssemblySpec assemblySpec;

	@JsonProperty("assembly_resp")
	private AssemblyResp assemblyResp;

	public AssemblySpec getAssemblySpec() {
		return assemblySpec;
	}

	public void setAssemblySpec(AssemblySpec assemblySpec) {
		this.assemblySpec = assemblySpec;
	}

	public AssemblyResp getAssemblyResp() {
		return assemblyResp;
	}

	public void setAssemblyResp(AssemblyResp assemblyResp) {
		this.assemblyResp = assemblyResp;
	}
	
}
