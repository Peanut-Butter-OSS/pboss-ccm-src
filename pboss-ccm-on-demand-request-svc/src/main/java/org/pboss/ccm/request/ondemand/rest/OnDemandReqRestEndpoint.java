package org.pboss.ccm.request.ondemand.rest;

import org.pboss.ccm.model.api.request.OnDemandOutboundCicRequest;
import org.pboss.ccm.model.api.request.OnDemandOutboundCicResponse;
import org.pboss.ccm.request.ondemand.service.OnDemandReqSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/on-demand-cics")
public class OnDemandReqRestEndpoint {
  
  Logger logger = LoggerFactory.getLogger(OnDemandReqRestEndpoint.class);
  
  @Value("${api.version}")
  private String apiVersion;
  
  @Autowired
  private OnDemandReqSvc onDemandReqSvc;
  
  @PostMapping("/submit")
  public OnDemandOutboundCicResponse submitOnDemandOutboundCicRequest(@RequestBody OnDemandOutboundCicRequest request) {

    logger.info("Service invoked: on-demand-cics/submit");
    logger.info("Input: "+request.toString());
    logger.info("API Version: "+apiVersion);
    
    OnDemandOutboundCicResponse resp = null;
    
    if((request.getReqGuid()!=null)&&(!request.getReqGuid().isEmpty())) {
      String msg = "Uuid cannot be supplied when submitting a request.";
      logger.error(msg);
      // TODO: Change exception handling as defined in: https://www.toptal.com/java/spring-boot-rest-api-error-handling
      throw new RuntimeException(msg);
    }
    
    resp = onDemandReqSvc.submitOnDemandOutboundCicRequest(request);
    
    return resp;
  }

}
