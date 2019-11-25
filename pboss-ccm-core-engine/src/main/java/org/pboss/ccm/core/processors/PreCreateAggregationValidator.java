package org.pboss.ccm.core.processors;

import java.util.Base64;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.jsonpath.JsonPath;

/**
 * This processor validates a created message that is received for aggregation This ensures that no
 * issues are experienced downstream for downstream processing
 * 
 * TODO: For now I"m just adding some basic validation. This must be extended over time to cover
 * everything.
 * 
 * @author gregf
 *
 */
public class PreCreateAggregationValidator implements Processor {

  Logger logger = LoggerFactory.getLogger(PreCreateAggregationValidator.class);

  public void process(Exchange exchange) throws Exception {

    String strBody = exchange.getIn().getBody(String.class);

    // Count the number of CIC Parts in the message
    logger.info("C3M Core :: Get the number of CIC Parts in the created cic response message");
    String exp = "$.cic.cic_part_list.length()";
    int count = 0;
    count = JsonPath.read(strBody, exp);
    // logger.info("C3M Core :: Part count = " + count);

    if (count <= 0) {
      throw new RuntimeException("Created CIC message cannot be aggregated. No CIC Parts found");
    }

  }
}
