package org.pboss.ccm.core.aggregators;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.pboss.ccm.model.api.request.OnDemandOutboundCicRequest;
import org.pboss.ccm.model.api.request.DeliverSpec;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;


/**
 * This is the aggregation strategy which is java code for <i>aggregating</i> incoming messages with
 * the existing aggregated message. In other words you use this strategy to <i>merge</i> the
 * messages together.
 * 
 *
 * @version $Revision$
 */
public class DeliverResultAggregationStrategy implements AggregationStrategy {

  Logger logger = LoggerFactory.getLogger(DeliverResultAggregationStrategy.class);

  /**
   * Aggregates the messages.
   * 
   * TODO: Figure out how to handle failed creations TODO: Add exception handling
   * 
   * @param oldExchange the existing aggregated message. Is <tt>null</tt> the very first time as
   *        there are no existing message.
   * @param newExchange the incoming message. This is never <tt>null</tt>.
   * @return the aggregated message.
   */
  public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

    Exchange aggregateExchange = null;
    logger.info("Aggregating Deliver Result. Result received for Aggregation: "
        + newExchange.getIn().getBody(String.class));

    // The first time there are no existing message and therefore
    // the oldExchange is null. In these cases we use the newExchange as the base
    if (oldExchange == null) {
      aggregateExchange = newExchange;
      logger.info("Current message is: " + newExchange);
    } else {
      logger.info("Aggregate message already exists. Using the oldExchange as the base");
      aggregateExchange = oldExchange;

      try {
        // Convert OldExchange Message to Java Object
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        OnDemandOutboundCicRequest cicReq = mapper.readValue(
            aggregateExchange.getIn().getBody(String.class), OnDemandOutboundCicRequest.class);

        // Convert NewExchange Message to Java Object, and get the DeliverSpec from it
        OnDemandOutboundCicRequest cicReq2 = mapper
            .readValue(newExchange.getIn().getBody(String.class), OnDemandOutboundCicRequest.class);
        DeliverSpec deliverSpec = cicReq2.getCic().getDeliverSpecList().get(0);

        // Adding the deliver spec of New Exchange to the Aggregate
        cicReq.getCic().getDeliverSpecList().add(deliverSpec);

        // Now add the new content as the body of the aggregate message
        String newMsg = mapper.writeValueAsString(cicReq);
        aggregateExchange.getIn().setBody(newMsg, String.class);
        logger.info("Aggregation of Delivery results complete");


      } catch (JsonParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (JsonMappingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    return aggregateExchange;
  }
}
