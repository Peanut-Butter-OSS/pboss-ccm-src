/**
 * 
 */
package org.pboss.ccm.model.api.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author gregf
 *
 */
@JsonTypeName("reference_spec")
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include=JsonTypeInfo.As.PROPERTY,property="ref_spec_type")
@JsonSubTypes({
@JsonSubTypes.Type(value=LinuxFsReferenceSpec.class, name="linux_fs_reference_spec"),
@JsonSubTypes.Type(value=WinFsReferenceSpec.class, name="win_fs_reference_spec"),
})
public class ReferenceSpec {

}
