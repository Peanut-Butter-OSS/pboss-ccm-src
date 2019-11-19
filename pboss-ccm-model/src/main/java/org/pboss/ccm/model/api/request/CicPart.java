/**
 * 
 */
package org.pboss.ccm.model.api.request;

import java.util.List;

import org.pboss.ccm.model.creation.CreateResp;
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
@JsonTypeName("cic_part")
@JsonInclude(Include.NON_NULL)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include=JsonTypeInfo.As.PROPERTY,property="creation_type")
@JsonSubTypes({
@JsonSubTypes.Type(value=RenderedCicPart.class, name="rendered_cic_part"),
@JsonSubTypes.Type(value=IncludedCicPart.class, name="included_cic_part"),
@JsonSubTypes.Type(value=AssembledCicPart.class, name="assembled_cic_part"),
@JsonSubTypes.Type(value=ReferencedCicPart.class, name="referenced_cic_part"),
})
public abstract class CicPart {
	
	@JsonProperty("cic_part_type_code")
	private String cicPartTypeCode;
	
	@JsonProperty("file_name")
	private String fileName;
	
	@JsonProperty("part_id")
	private String partId;
	
	@JsonProperty("part_guid")
	private String partGuid;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("content_uri")
	private String contentUri;

	@JsonProperty("mime_type")
	private String mimeType;
	
	//@JsonProperty("store_spec_list")
	//private List<StoreSpec> storeSpecList;
	
	@JsonProperty("create_resp")
	private CreateResp createResp;
	
	public String getCicPartTypeCode() {
		return cicPartTypeCode;
	}
	
	public void setCicPartTypeCode(String cicPartTypeCode) {
		this.cicPartTypeCode = cicPartTypeCode;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getPartId() {
		return partId;
	}
	
	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getPartGuid() {
		return partGuid;
	}

	public void setPartGuid(String partGuid) {
		this.partGuid = partGuid;
	}

	//public List<StoreSpec> getStoreSpecList() {
	//	return storeSpecList;
	//}

	//public void setStoreSpecList(List<StoreSpec> storeSpecList) {
	//	this.storeSpecList = storeSpecList;
	//}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getContentUri() {
		return contentUri;
	}

	public void setContentUri(String contentUri) {
		this.contentUri = contentUri;
	}
	
	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	public CreateResp getCreateResp() {
		return createResp;
	}

	public void setCreateResp(CreateResp createResp) {
		this.createResp = createResp;
	}

	@Override
	public String toString() {
		return "CicPart [cicPartTypeCode=" + cicPartTypeCode + ", fileName=" + fileName + ", partId=" + partId
				+ ", partGuid=" + partGuid + ", description=" + description + ", contentUri=" + contentUri
				+ ", mimeType=" + mimeType + "]";
	}
}
