package org.pboss.ccm.core.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.pboss.ccm.core.aggregators.CreateResultAggregationStrategy;
import org.pboss.ccm.core.processors.MessageAnalyser;
import org.pboss.ccm.core.processors.PostCreateAggregateCleanup;
import org.pboss.ccm.core.processors.PreCreateAggregationValidator;
import org.pboss.ccm.core.processors.StatusUpdater;
import org.springframework.stereotype.Component;

@Component
public class CoreRouter extends RouteBuilder {

    @Override
    public void configure() {
      
      errorHandler(defaultErrorHandler());
      
//      // Test route, just to double check that we're actually receiving messages
//      from("rabbitmq:pboss-ccm-exchange?exchangeType=topic&autoDelete=false&routingKey=pboss.ccm.master")
//      .routeId("core-create-start")
//      .log("Message received: ${body}");
      
      // ==============================
      // This is the core creation start route for C3M
      // It does the following:
      //  - Sends the original request to an Audit queue
      //  - Does some preliminary analysis of the request and sets header values
      //  - Splits the request into the separate cic parts and sends them individually to the creation subsystem
      //  - TODO: Updates the CIC Status
      // ==============================
      
      from("activemq:queue:activemq/queue/c3mMasterQueue")
      .errorHandler(deadLetterChannel("activemq:queue:C3M.CoreStartDLQ")
              .maximumRedeliveries(3)
              .redeliveryDelay(500)
              .retryAttemptedLogLevel(LoggingLevel.WARN)
              .useOriginalMessage())
      .routeId("core-create-start")
      .log("C3M Core :: Message picked up from master queue")
      .wireTap("activemq:queue:C3M.MasterQueueAudit")
      .to("mock:createstart")
      .setHeader("C3M_SystemInstanceId").jsonpath("$.req_header.system_instance_id")
      .setHeader("C3M_ReqGuid").jsonpath("$.req_guid")
      .setHeader("C3M_TestLevel").jsonpath("$.req_header.test_level")
      .setHeader("C3M_Status").constant("RECEIVED")
      .process(new StatusUpdater())
      .process(new MessageAnalyser())
      .to("activemq:queue:C3M.SUBSYS.TRACKER.NewIndexReq")
      .log("C3M Core :: Splitting out cic parts according to creation types")
      .split().method("requestSplitter", "splitByCicPart")
          .process(new ExchangeLogger())
          .setHeader("C3M_PartGuid").jsonpath("$.cic.cic_part_list[0].part_guid")
          .setHeader("C3M_PartId").jsonpath("$.cic.cic_part_list[0].part_id")
          .log("C3M Core :: Sending CIC Part to Creation Subsystem")
          .to("activemq:queue:C3M.SUBSYS.CREATION.CreateManagerReq")
          .to("mock:createreqqueue")
      .end()
      .setHeader("C3M_Status").constant("SENT_FOR_CREATION")
      .to("activemq:queue:C3M.SUBSYS.TRACKER.StatusUpdateReq")
      .to("mock:createend");

      
      // ==============================
      // This is the core creation end route for C3M
      // It does the following:
      //  - Aggregates all created cic parts that form part of the CIC
      //  - Send the aggregated CIC to the next gate
      //  - TODO: Updates the CIC Status
      // ==============================
      from("activemq:queue:C3M.SUBSYS.CREATION.CreateManagerResp")
      .errorHandler(deadLetterChannel("activemq:queue:C3M.CoreCreateAggregationDLQ")
              .maximumRedeliveries(3)
              .redeliveryDelay(1000)
              .retryAttemptedLogLevel(LoggingLevel.WARN)
              .useOriginalMessage())
      .routeId("core-create-aggregrate")
      .log("C3M Core :: Create Resp picked up from Create Manager Resp queue. Expecting ${in.header.C3M_CicPartCount} messages")
      .wireTap("activemq:queue:C3M.CORE.PreCreateAggregationAuditQueue")
      .to("mock:input")
      .process(new PreCreateAggregationValidator())
      .aggregate(header("C3M_ReqGuid"), new CreateResultAggregationStrategy())
          .completionSize(simple("${in.header.C3M_CicPartCount}"))
          .completionTimeout(5000)
      .log("C3M Core :: Aggregation Completed by ${property.CamelAggregatedCompletedBy}")
      //.log("C3M Core :: Aggregated Body is: ${body}")
      .choice()
          .when().simple("${property.CamelAggregatedCompletedBy} == 'size'")
              .log("C3M Core :: Received all Cic Parts and ready to proceed")
              .process(new PostCreateAggregateCleanup())
              .to("mock:created")
              .setHeader("C3M_Status").constant("CREATED")
              .to("activemq:queue:C3M.SUBSYS.TRACKER.StatusUpdateReq")
              .to("activemq:queue:C3M.CORE.Created")
              .endChoice()
          .when().simple("${property.CamelAggregatedCompletedBy} == 'timeout'")
              .log("C3M Core :: Not all CIC parts received for aggregation")
              .to("mock:aggregationtimeout")
              .log("C3M Core :: Mock Sent")
              .setHeader("C3M_Status").constant("CREATION_TIMEOUT")
              .to("activemq:queue:C3M.SUBSYS.TRACKER.StatusUpdateReq")
              .to("activemq:queue:C3M.CORE.ErrorIncompleteCicPartAggregation")
              .log("C3M Core :: The part that was received is sent to Incomplete queue")
              .endChoice()
          .otherwise()
              .setHeader("C3M_Status").constant("SYSTEM_ERROR")
              .to("activemq:queue:C3M.SUBSYS.TRACKER.StatusUpdateReq")
              .log("C3M Core  :: Error: Unexpected completion of CIC Part creation aggregation")
              .to("activemq:queue:C3M.CORE.ErrorUnexpectedCicPartAggregration")
              .to("mock:unexpectedaggregation")
              .endChoice()
      .end()

      .process(new ExchangeLogger());
      
    }

}
