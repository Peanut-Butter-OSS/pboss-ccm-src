package org.pboss.ccm.request.ondemand.service;

import java.time.OffsetDateTime;
import java.util.List;
import org.pboss.ccm.model.api.request.CicPart;
import org.pboss.ccm.model.api.request.DeliverSpec;
import org.pboss.ccm.model.api.request.OnDemandOutboundCicRequest;
import org.pboss.ccm.model.api.request.OnDemandOutboundCicResponse;
import org.pboss.ccm.model.api.request.ReqHeader;
import org.pboss.ccm.model.api.request.RespHeader;
import org.pboss.ccm.model.api.request.StoreSpec;
import org.pboss.ccm.model.api.request.ValidationResult;
import org.pboss.ccm.request.ondemand.validation.CicRequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;

@Service
@RefreshScope
public class OnDemandReqSvc {

  Logger logger = LoggerFactory.getLogger(OnDemandReqSvc.class);
  
  private final RabbitTemplate rabbitTemplate;
  private static final String EXCHANGE_NAME = "pboss-ccm-exchange";
  private static final String QUEUE_NAME = "pboss.ccm.master";
  private static final String ROUTING_KEY = "pboss.ccm.master";

  @Value("${api.version}")
  private String apiVersion;

  @Autowired
  private CicRequestValidator validator;

  @Bean
  Queue queue() {
      return new Queue(QUEUE_NAME, true);
  }

  @Bean
  TopicExchange exchange() {
      return new TopicExchange(EXCHANGE_NAME);
  }

  @Bean
  Binding binding(Queue queue, TopicExchange exchange) {
      return BindingBuilder.bind(queue).to(exchange).with("pboss.ccm.#");
  }

  public OnDemandReqSvc(RabbitTemplate rabbitTemplate) {
      this.rabbitTemplate = rabbitTemplate;
  }
  
  // TODO: Add proper exception handling
  public OnDemandOutboundCicResponse submitOnDemandOutboundCicRequest(
      OnDemandOutboundCicRequest req) throws RuntimeException {
    logger.debug(
        "EJB Invoked. Method: submitOnDemandOutboundCicRequest(OnDemandOutboundCicRequest req)");
    RespHeader respHeader = new RespHeader();

    // Generate GUID
    String reqGuid = java.util.UUID.randomUUID().toString();
    req.setReqGuid(reqGuid);
    logger.debug("Assigning GUID to CIC Request. GUID: " + reqGuid);

    // Validate the request
    ValidationResult validationResult = validator.validateCicRequest(req);

    if (validationResult.isValid()) {
      // Derive system instance id using the provided credentials
      // TODO: This is temporarily hard-coded until the authentication is finalised
      String systemInstanceId = "default";

      ReqHeader reqHeader = req.getReqHeader();

      // Convert request to JSON and send to RabbitMQ
      ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
      String jsonPrettyString = null;
      String jsonString = null;
      try {
        // Add System instance Id
        reqHeader.setSystemInstanceId(systemInstanceId);

        // Calculate (and add) the request timestamp
        OffsetDateTime requestTimestamp = OffsetDateTime.now();
        reqHeader.setRequestTimestamp(requestTimestamp);

        // If cic_date_time wasn't supplied, set it
        if (req.getCic().getCicDateTime() == null) {
          OffsetDateTime cicDateTime = OffsetDateTime.now();
          req.getCic().setCicDateTime(cicDateTime);
        }

        // Handle the API Version
        // If it was not supplied, initialize with the default system property
        // If it was supplied but is incorrect, throw an exception and notify user
        if (reqHeader.getApiVersion() == null) {
          reqHeader.setApiVersion(apiVersion);
        } else if (!reqHeader.getApiVersion().equalsIgnoreCase(apiVersion)) {
          String msg =
              "The supplied API Version is not available on the server. To use the latest version of the API, please do not supply API version as input";
          throw new RuntimeException(msg);
        }

        // If no correlation id was supplied, initialize it with the value of the GUID
        if (reqHeader.getCorrId() == null) {
          reqHeader.setCorrId(reqGuid);
        }

        // Add Guids for each Part
        List<CicPart> parts = req.getCic().getCicPartList();
        String partGuid = null;
        for (CicPart p : parts) {
          partGuid = java.util.UUID.randomUUID().toString();
          logger.debug("Assigning GUID to CIC Part. Part Id (Supplied): " + p.getPartId()
              + " :: GUID: " + partGuid);
          p.setPartGuid(partGuid);
        }

        // Add Guids for each DeliverSpec
        List<DeliverSpec> deliverSpecList = req.getCic().getDeliverSpecList();
        String specGuid = null;
        if (deliverSpecList != null) {
          for (DeliverSpec d : deliverSpecList) {
            specGuid = java.util.UUID.randomUUID().toString();
            logger.debug("Assigning GUID to DeliverSpec. GUID: " + specGuid);
            d.setDeliverSpecGuid(specGuid);
          }
        }

        // Add Guids for each StoreSpecSpec
        List<StoreSpec> storeSpecList = req.getCic().getStoreSpecList();
        String storeSpecGuid = null;
        if (storeSpecList != null) {
          for (StoreSpec s : storeSpecList) {
            storeSpecGuid = java.util.UUID.randomUUID().toString();
            logger.debug("Assigning GUID to StoreSpec. GUID: " + storeSpecGuid);
            s.setStoreSpecGuid(storeSpecGuid);
          }
        }

        jsonPrettyString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(req);
        jsonString = mapper.writeValueAsString(req);
        logger.debug(jsonString);
        
        // Send message to RabbitMQ
        // Exchange, routing key, message
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, jsonString);
        
        respHeader.setRespCode(0);
        respHeader.setRespMsg("Successfully submitted CIC Request to PBoss CCM Core");
        respHeader.setReqGuid(reqGuid);

      } catch (JsonProcessingException e) {
        // TODO Auto-generated catch block
        // TODO: Get the correct error id
        e.printStackTrace();
        respHeader.setRespCode(1);
        respHeader
            .setRespMsg("An exception occurred while submitting CIC Request to PBoss CCM Core. Msg: "
                + e.getMessage());
        respHeader.setErrorId(1);
      } catch (AmqpException e) {
        // TODO Check that the mqProducer class throws proper exceptions that can be caught here
        // TODO: Get the correct error id
        e.printStackTrace();
        respHeader.setRespCode(1);
        respHeader
            .setRespMsg("An exception occurred while submitting CIC Request to PBoss CCM Core. Msg: "
                + e.getMessage());
        respHeader.setErrorId(1);
      }
    } else {
      respHeader.setRespCode(2);
      respHeader
          .setRespMsg("The supplied request is invalid. Please see detailed validation results");
      respHeader.setErrorId(2);
    }

    // Calculate the response timestamp
    OffsetDateTime responseTimestamp = OffsetDateTime.now();

    // Prepare Response
    respHeader.setApiVersion(apiVersion);
    respHeader.setResponseTimestamp(responseTimestamp);
    respHeader.setCorrId(req.getReqHeader().getCorrId());
    OnDemandOutboundCicResponse resp = new OnDemandOutboundCicResponse();
    resp.setRespHeader(respHeader);
    resp.setValidationResult(validationResult);
    return resp;
  }

}
