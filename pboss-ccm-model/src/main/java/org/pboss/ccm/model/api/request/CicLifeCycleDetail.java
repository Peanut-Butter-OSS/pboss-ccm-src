/**
 * 
 */
package org.pboss.ccm.model.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gregf
 *
 */
public class CicLifeCycleDetail {
	
	@JsonProperty("life_span_minutes")
	private double lifeSpanMinutes;

	public double getLifeSpanMinutes() {
		return lifeSpanMinutes;
	}

	public void setLifeSpanMinutes(double lifeSpanMinutes) {
		this.lifeSpanMinutes = lifeSpanMinutes;
	}

	@Override
	public String toString() {
		return "CicLifeCycleDetail \n[lifeSpanMinutes=" + lifeSpanMinutes + "]";
	}

	
}
