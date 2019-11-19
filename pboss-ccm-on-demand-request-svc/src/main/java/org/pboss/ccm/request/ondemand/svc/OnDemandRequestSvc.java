package org.pboss.ccm.request.ondemand.svc;

import org.pboss.ccm.model.api.request.OnDemandOutboundCicRequest;
import org.pboss.ccm.model.api.request.OnDemandOutboundCicResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnDemandRequestSvc {
  
  @RequestMapping("/submit")
  public OnDemandOutboundCicResponse submitOnDemandOutboundCicRequest(OnDemandOutboundCicRequest req) {
    OnDemandOutboundCicResponse response = new OnDemandOutboundCicResponse();
    return response;
  }

}
