package org.pboss.ccm.core.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.pboss.ccm.core.aggregators.CreateResultAggregationStrategy;
import org.pboss.ccm.core.processors.MessageAnalyser;
import org.pboss.ccm.core.processors.PostCreateAggregateCleanup;
import org.pboss.ccm.core.processors.PreCreateAggregationValidator;
import org.pboss.ccm.core.processors.StatusUpdater;
import org.pboss.ccm.core.splitters.RequestSplitter;
import org.pboss.ccm.core.utils.camel.ExchangeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoreRouter extends RouteBuilder {

  @Autowired
  private RequestSplitter requestSplitter;
  
  @Override
  public void configure() {

    // errorHandler(defaultErrorHandler());

    // // Test route, just to double check that we're actually receiving messages
    // from("rabbitmq:pboss-ccm-exchange?exchangeType=topic&autoDelete=false&routingKey=pboss.ccm.master")
    // .routeId("core-create-start")
    // .log("Message received: ${body}");

    // ==============================
    // This is the core creation start route for C3M
    // It does the following:
    // - Sends the original request to an Audit queue
    // - Does some preliminary analysis of the request and sets header values
    // - Splits the request into the separate cic parts and sends them individually to the creation
    // subsystem
    // - TODO: Updates the CIC Status
    // ==============================

    // from("activemq:queue:activemq/queue/c3mMasterQueue")
    from("rabbitmq:pboss-ccm-exchange?exchangeType=topic&autoDelete=false&routingKey=pboss.ccm.master")
    // .errorHandler(deadLetterChannel("activemq:queue:C3M.CoreStartDLQ")
    //    .maximumRedeliveries(3).redeliveryDelay(500)
    //    .retryAttemptedLogLevel(LoggingLevel.WARN).useOriginalMessage())
    .routeId("core-create-start")
    .log("CCM Core :: Message picked up from master queue")
    //.wireTap("rabbitmq:pboss-ccm-exchange?exchangeType=topic&autoDelete=false&routingKey=pboss.ccm.master.audit")
    .to("mock:createstart")
    .setHeader("CCM_SystemInstanceId").jsonpath("$.req_header.system_instance_id")
    .setHeader("CCM_ReqGuid").jsonpath("$.req_guid")
    .setHeader("CCM_TestLevel").jsonpath("$.req_header.test_level")
    .setHeader("CCM_Status").constant("{{default.initial.status}}")
    .process(new ExchangeLogger())
    .process(new StatusUpdater())
    .process(new MessageAnalyser())
    // .to("activemq:queue:C3M.SUBSYS.TRACKER.NewIndexReq")
    .log("CCM Core :: Splitting out cic parts according to creation types")
    .split()
      .method("requestSplitter", "splitByCicPart")
      .process(new ExchangeLogger())
      .setHeader("CCM_PartGuid").jsonpath("$.cic.cic_part_list[0].part_guid")
      .setHeader("CCM_PartId").jsonpath("$.cic.cic_part_list[0].part_id")
      .log("CCM Core :: Sending CIC Part to Creation Subsystem")
      // .to("activemq:queue:C3M.SUBSYS.CREATION.CreateManagerReq").to("mock:createreqqueue")
    .end()
    .setHeader("CCM_Status").constant("SENT_FOR_CREATION")
    //.to("activemq:queue:C3M.SUBSYS.TRACKER.StatusUpdateReq")
    .to("mock:createend");

  }
}
