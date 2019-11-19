/**
 * 
 */
package org.pboss.ccm.model.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author gregf
 *
 */
@JsonTypeName("ddx_assembly_spec")
public class DdxAssemblySpec extends AssemblySpec{
	
	@JsonProperty("runtime_ddx")
	String runtimeDdx;

	public String getRuntimeDdx() {
		return runtimeDdx;
	}

	public void setRuntimeDdx(String runtimeDdx) {
		this.runtimeDdx = runtimeDdx;
	}
	
}
