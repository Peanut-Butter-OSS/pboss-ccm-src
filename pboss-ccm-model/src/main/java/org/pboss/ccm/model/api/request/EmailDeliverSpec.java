/**
 * 
 */
package org.pboss.ccm.model.api.request;

import java.util.List;

import org.pboss.ccm.model.delivery.EmailResp;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * TODO: This spec is way too simplistic. In particular:
 *  - It assumes that the client will simply provide a list of email addresses.
 *    This is unlikely, what if C3M should derive the address from the payload
 *    Or what if C3M must derive the address from the unique CIC recipient code
 *  - It assumes that the Body and subject will necessarily be provided as CIC
 *    parts. What if the client simply wants to hard-code one or both
 * 
 * @author gregf
 *
 *
 */
@JsonTypeName("email_deliver_spec")
@JsonInclude(Include.NON_NULL)
public class EmailDeliverSpec extends DeliverSpec {
	
	@JsonProperty("from_address")
	private String fromAddress;
	
	@JsonProperty("reply_to_address")
	private String replyToAddress;
	
	@JsonProperty("to_address")
	private String toAddress;
	
	@JsonProperty("cc_address")
	private String ccAddress;
	
	@JsonProperty("bcc_address")
	private String bccAddress;
	
	@JsonProperty("master_data_to_ref")
	private String masterDataToRef;
	
	@JsonProperty("master_data_cc_ref")
	private String masterDataCcRef;
	
	@JsonProperty("master_data_bcc_ref")
	private String masterDataBccRef;
	
	@JsonProperty("email_priority")
	private String emailPriority;
	
	@JsonProperty("part_id_subject")
	private String partIdSubject;
	
	@JsonProperty("part_id_body")
	private String partIdBody;

	@JsonProperty("attachment_list")
	private List<String> attachmentList;

	@JsonProperty("email_resp")
	private EmailResp emailResp;
	
	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getReplyToAddress() {
		return replyToAddress;
	}

	public void setReplyToAddress(String replyToAddress) {
		this.replyToAddress = replyToAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getCcAddress() {
		return ccAddress;
	}

	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}

	public String getBccAddress() {
		return bccAddress;
	}

	public void setBccAddress(String bccAddress) {
		this.bccAddress = bccAddress;
	}

	public String getMasterDataToRef() {
		return masterDataToRef;
	}

	public void setMasterDataToRef(String masterDataToRef) {
		this.masterDataToRef = masterDataToRef;
	}

	public String getMasterDataCcRef() {
		return masterDataCcRef;
	}

	public void setMasterDataCcRef(String masterDataCcRef) {
		this.masterDataCcRef = masterDataCcRef;
	}

	public String getMasterDataBccRef() {
		return masterDataBccRef;
	}

	public void setMasterDataBccRef(String masterDataBccRef) {
		this.masterDataBccRef = masterDataBccRef;
	}

	public String getEmailPriority() {
		return emailPriority;
	}

	public void setEmailPriority(String emailPriority) {
		this.emailPriority = emailPriority;
	}

	public String getPartIdSubject() {
		return partIdSubject;
	}

	public void setPartIdSubject(String partIdSubject) {
		this.partIdSubject = partIdSubject;
	}

	public String getPartIdBody() {
		return partIdBody;
	}

	public void setPartIdBody(String partIdBody) {
		this.partIdBody = partIdBody;
	}

	public List<String> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<String> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public EmailResp getEmailResp() {
		return emailResp;
	}

	public void setEmailResp(EmailResp emailResp) {
		this.emailResp = emailResp;
	}
	
}
