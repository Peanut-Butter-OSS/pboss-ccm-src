/**
 * 
 */
package org.pboss.ccm.model.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author gregf
 *
 */
@JsonTypeName("store_spec")
@JsonInclude(Include.NON_NULL)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include=JsonTypeInfo.As.PROPERTY,property="store_spec_type")
@JsonSubTypes({
@JsonSubTypes.Type(value=LinuxFsStoreSpec.class, name="linux_fs_store_spec"),
@JsonSubTypes.Type(value=WinFsStoreSpec.class, name="win_fs_store_spec"),
})
public class StoreSpec {

	@JsonProperty("store_spec_guid")
	private String storeSpecGuid;
	
	@JsonProperty("part_id")
	private String partId;

	public String getStoreSpecGuid() {
		return storeSpecGuid;
	}

	public void setStoreSpecGuid(String storeSpecGuid) {
		this.storeSpecGuid = storeSpecGuid;
	}

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}
	
}
