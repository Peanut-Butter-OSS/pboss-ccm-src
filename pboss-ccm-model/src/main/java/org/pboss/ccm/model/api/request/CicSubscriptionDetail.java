/**
 * 
 */
package org.pboss.ccm.model.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gregf
 *
 */
public class CicSubscriptionDetail {
	
	@JsonProperty("override_subs")
	private boolean overrideSubs;
	
	@JsonProperty("subs_override_key")
	private String subsOverrideKey;
	
	public boolean isOverrideSubs() {
		return overrideSubs;
	}
	
	public void setOverrideSubs(boolean overrideSubs) {
		this.overrideSubs = overrideSubs;
	}
	
	public String getSubsOverrideKey() {
		return subsOverrideKey;
	}
	
	public void setSubsOverrideKey(String subsOverrideKey) {
		this.subsOverrideKey = subsOverrideKey;
	}

	@Override
	public String toString() {
		return "CicSubscriptionDetail \n[overrideSubs=" + overrideSubs + ", subsOverrideKey=" + subsOverrideKey + "]";
	}
	
	

}
