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
@JsonTypeName("deliver_spec")
@JsonInclude(Include.NON_NULL)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include=JsonTypeInfo.As.PROPERTY,property="deliver_type")
@JsonSubTypes({
@JsonSubTypes.Type(value=SmsDeliverSpec.class, name="sms_deliver_spec"),
@JsonSubTypes.Type(value=EmailDeliverSpec.class, name="email_deliver_spec"),
@JsonSubTypes.Type(value=EsignLiveDeliverSpec.class, name="esignlive_deliver_spec"),
})
public class DeliverSpec {
	
	@JsonProperty("contact_id")
	private String contactId;
	
	@JsonProperty("contact_type")
	private ContactType contactType;
	
	@JsonProperty("deliver_spec_guid")
	private String deliverSpecGuid;

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	
	public ContactType getContactType() {
		return contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

	public String getDeliverSpecGuid() {
		return deliverSpecGuid;
	}

	public void setDeliverSpecGuid(String deliverSpecGuid) {
		this.deliverSpecGuid = deliverSpecGuid;
	}
	
}
