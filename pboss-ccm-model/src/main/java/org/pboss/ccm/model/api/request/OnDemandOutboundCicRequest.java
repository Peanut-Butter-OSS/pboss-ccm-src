
package org.pboss.ccm.model.api.request;

import org.pboss.ccm.model.api.request.ReqHeader;
import org.pboss.ccm.model.api.request.Cic;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author gregf
 *
 */
@JsonTypeName("on_demand_outbound_cic_request")
@JsonInclude(Include.NON_NULL)
public class OnDemandOutboundCicRequest {
	
	@JsonProperty("req_header")
	private ReqHeader reqHeader;
	
	@JsonProperty("req_guid")
	private String reqGuid;
	
	@JsonProperty("cic")
	private Cic cic;
	
	public ReqHeader getReqHeader() {
		return reqHeader;
	}
	
	public void setReqHeader(ReqHeader reqHeader) {
		this.reqHeader = reqHeader;
	}
	
	public String getReqGuid() {
		return reqGuid;
	}
	
	public void setReqGuid(String reqGuid) {
		this.reqGuid = reqGuid;
	}
	
	public Cic getCic() {
		return cic;
	}
	
	public void setCic(Cic cic) {
		this.cic = cic;
	}

	@Override
	public String toString() {
		return "OnDemandOutboundCicRequest [reqHeader=" + reqHeader + ", reqGuid=" + reqGuid + ", cic=" + cic + "]";
	}
	
	

}
