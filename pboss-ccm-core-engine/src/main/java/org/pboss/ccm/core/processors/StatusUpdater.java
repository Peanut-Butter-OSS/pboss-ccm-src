package org.pboss.ccm.core.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.pboss.ccm.model.api.request.CicStatus;
import org.pboss.ccm.model.api.request.OnDemandOutboundCicRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * 
 * This processor updated the status of current message.
 * 
 * @author gregf
 *
 */
public class StatusUpdater implements Processor {

  Logger logger = LoggerFactory.getLogger(StatusUpdater.class);

  public void process(Exchange exchange) throws Exception {

    logger.info("CCM Core :: Setting Initial CIC status");

    String strBody = exchange.getIn().getBody(String.class);
    String strStatus = (String) exchange.getIn().getHeader("CCM_Status");

    // Convert Message body to Java Object
    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    OnDemandOutboundCicRequest cicReq = mapper.readValue(strBody, OnDemandOutboundCicRequest.class);

    // Set status inside message
    cicReq.getCic().setCicStatus(CicStatus.valueOf(strStatus));

    // Convert message back to string
    String jsonString = mapper.writeValueAsString(cicReq);
    exchange.getIn().setBody(jsonString);

    logger.info("CCM Core :: CIC status set to " + strStatus);
  }
}
