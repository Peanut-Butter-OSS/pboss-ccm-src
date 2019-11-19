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
@JsonTypeName("sms_deliver_spec")
@JsonInclude(Include.NON_NULL)
public class SmsDeliverSpec extends DeliverSpec {
	
	@JsonProperty("master_data_mobile_ref")
	private String masterDataMobileRef;
	
	@JsonProperty("mobile_number")
	private String mobileNumber;
	
	@JsonProperty("part_id_sms_body")
	private String partIdSmsBody;

	public String getMasterDataMobileRef() {
		return masterDataMobileRef;
	}

	public void setMasterDataMobileRef(String masterDataMobileRef) {
		this.masterDataMobileRef = masterDataMobileRef;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPartIdSmsBody() {
		return partIdSmsBody;
	}

	public void setPartIdSmsBody(String partIdSmsBody) {
		this.partIdSmsBody = partIdSmsBody;
	}
	
	
	
}
