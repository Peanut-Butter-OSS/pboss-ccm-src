/**
 * 
 */
package org.pboss.ccm.model.api.request;

import java.util.List;

import org.pboss.ccm.model.delivery.EsignLiveResp;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * TODO: This spec is just a guess for now, until we do the end-to-end connection with eSign-Live
 * 
 * @author gregf
 *
 *
 */
@JsonTypeName("esignlive_deliver_spec")
@JsonInclude(Include.NON_NULL)
public class EsignLiveDeliverSpec extends DeliverSpec {
	
	@JsonProperty("package_name")
	private String packageName;	
	
	@JsonProperty("part_id_message")
	private String partIdMessage;
	
	@JsonProperty("sender_name")
	private String senderName;
	
	@JsonProperty("sender_surname")
	private String senderSurname;
	
	@JsonProperty("sender_email")
	private String senderEmail;
	
	@JsonProperty("sender_company")
	private String senderCompany;

	@JsonProperty("signer_list")
	private List<EsignLiveSigner> signerList;

	@JsonProperty("document_list")
	private List<EsignLiveDocumentSpec> documentList;

	@JsonProperty("esignlive_resp")
	private EsignLiveResp eSignLiveResp;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String getPartIdMessage() {
		return partIdMessage;
	}

	public void setPartIdMessage(String partIdMessage) {
		this.partIdMessage = partIdMessage;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderSurname() {
		return senderSurname;
	}

	public void setSenderSurname(String senderSurname) {
		this.senderSurname = senderSurname;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getSenderCompany() {
		return senderCompany;
	}

	public void setSenderCompany(String senderCompany) {
		this.senderCompany = senderCompany;
	}

	public List<EsignLiveSigner> getSignerList() {
		return signerList;
	}

	public void setSignerList(List<EsignLiveSigner> signerList) {
		this.signerList = signerList;
	}

	public List<EsignLiveDocumentSpec> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<EsignLiveDocumentSpec> documentList) {
		this.documentList = documentList;
	}

	public EsignLiveResp geteSignLiveResp() {
		return eSignLiveResp;
	}

	public void seteSignLiveResp(EsignLiveResp eSignLiveResp) {
		this.eSignLiveResp = eSignLiveResp;
	}

	@Override
	public String toString() {
		return "EsignLiveDeliverSpec [packageName=" + packageName + ", partIdMessage=" + partIdMessage + ", senderName="
				+ senderName + ", senderSurname=" + senderSurname + ", senderEmail=" + senderEmail + ", senderCompany="
				+ senderCompany + ", eSignLiveResp=" + eSignLiveResp + "]";
	}	
	
}
