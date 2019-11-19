/**
 * 
 */
package org.pboss.ccm.model.api.request;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;

/**
 * @author gregf
 *
 */
@JsonTypeName("cic")
@JsonInclude(Include.NON_NULL)
public class Cic {
	
	@JsonProperty("cic_status")
	private CicStatus cicStatus;
	
	//@JsonProperty("cic_date_time")
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ssX", timezone="GMT+2")
    //private Date cicDateTime;
	
	@JsonProperty("cic_date_time")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ssX", timezone="GMT+2")
	@JsonSerialize(using = OffsetDateTimeSerializer.class)
    private OffsetDateTime cicDateTime;
	
	@JsonProperty("cic_type_code")
    private String cicTypeCode;
	
	@JsonProperty("cic_life_cycle_detail")
	private CicLifeCycleDetail cicLifeCycleDetail;
	
	@JsonProperty("cic_subs_detail")
	private CicSubscriptionDetail cicSubsDetail;
    
	@JsonProperty("custom_metadata")
	private Map<String, Object> customMetadata;
	
	@JsonProperty("cic_part_list")
	private List<CicPart> cicPartList;
	
	@JsonProperty("store_spec_list")
	private List<StoreSpec> storeSpecList;
	
	@JsonProperty("deliver_spec_list")
	private List<DeliverSpec> deliverSpecList;
	
	//public Date getCicDateTime() {
	//	return cicDateTime;
	//}
	
	//public void setCicDateTime(Date cicDateTime) {
	//	this.cicDateTime = cicDateTime;
	//}
	
	public CicStatus getCicStatus() {
		return cicStatus;
	}

	public void setCicStatus(CicStatus cicStatus) {
		this.cicStatus = cicStatus;
	}

	public OffsetDateTime getCicDateTime() {
		return cicDateTime;
	}

	public void setCicDateTime(OffsetDateTime cicDateTime) {
		this.cicDateTime = cicDateTime;
	}

	public String getCicTypeCode() {
		return cicTypeCode;
	}
	
	public void setCicTypeCode(String cicTypeCode) {
		this.cicTypeCode = cicTypeCode;
	}

	public CicLifeCycleDetail getCicLifeCycleDetail() {
		return cicLifeCycleDetail;
	}

	public void setCicLifeCycleDetail(CicLifeCycleDetail cicLifeCycleDetail) {
		this.cicLifeCycleDetail = cicLifeCycleDetail;
	}

	public CicSubscriptionDetail getCicSubsDetail() {
		return cicSubsDetail;
	}

	public void setCicSubsDetail(CicSubscriptionDetail cicSubsDetail) {
		this.cicSubsDetail = cicSubsDetail;
	}

	public Map<String, Object> getCustomMetadata() {
		return customMetadata;
	}

	public void setCustomMetadata(Map<String, Object> customMetadata) {
		this.customMetadata = customMetadata;
	}

	public List<CicPart> getCicPartList() {
		return cicPartList;
	}
	
	public List<StoreSpec> getStoreSpecList() {
		return storeSpecList;
	}

	public void setStoreSpecList(List<StoreSpec> storeSpecList) {
		this.storeSpecList = storeSpecList;
	}

	public List<DeliverSpec> getDeliverSpecList() {
		return deliverSpecList;
	}

	public void setDeliverSpecList(List<DeliverSpec> deliverSpecList) {
		this.deliverSpecList = deliverSpecList;
	}

	@Override
	public String toString() {
		return "Cic \n[cicDateTime=" + cicDateTime + ", cicTypeCode=" + cicTypeCode + ", cicLifeCycleDetail="
				+ cicLifeCycleDetail + ", cicSubsDetail=" + cicSubsDetail + ", customMetadata=" + customMetadata
				+ ", cicPartList=" + cicPartList + "]";
	}

	public void setCicPartList(List<CicPart> cicPartList) {
		this.cicPartList = cicPartList;
	}

	

}
