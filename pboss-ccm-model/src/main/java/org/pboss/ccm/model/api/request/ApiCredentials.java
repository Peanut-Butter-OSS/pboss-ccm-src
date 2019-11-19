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
@JsonTypeName("api_credentials")
@JsonInclude(Include.NON_NULL)
public class ApiCredentials {

	@JsonProperty("account_name")
	private String accountName;
	
	@JsonProperty("api_key")
	private String apiKey;
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	@Override
	public String toString() {
		return "ApiCredentials [accountName=" + accountName + ", apiKey=" + apiKey + "]";
	}

	
}
