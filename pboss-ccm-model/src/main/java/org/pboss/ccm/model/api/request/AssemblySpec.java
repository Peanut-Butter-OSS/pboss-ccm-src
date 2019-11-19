/**
 * 
 */
package org.pboss.ccm.model.api.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author gregf
 *
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include=JsonTypeInfo.As.PROPERTY,property="assembly_spec_type")
@JsonSubTypes({
@JsonSubTypes.Type(value=DdxAssemblySpec.class, name="ddx_assembly_spec"),
@JsonSubTypes.Type(value=CustomAssemblySpec.class, name="custom_assembly_spec"),
})
public class AssemblySpec {

	
}
