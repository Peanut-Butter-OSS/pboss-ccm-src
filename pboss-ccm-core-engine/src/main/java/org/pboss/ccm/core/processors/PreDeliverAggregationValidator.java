package org.pboss.ccm.core.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.jsonpath.JsonPath;

/**
 * This processor validates a response message that is received for aggregation
 * after the deliver step is complete.
 * It ensures that no issues are experienced downstream
 * 
 * TODO: For now I'm just adding some basic validation. This must be extended over time 
 * to cover everything.
 * 
 * @author gregf
 *
 */
public class PreDeliverAggregationValidator implements Processor {

  Logger logger = LoggerFactory.getLogger(PreDeliverAggregationValidator.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		String strBody = exchange.getIn().getBody(String.class);
		
		// Count the number of Deliver Specs in the message (if it is not 1) then we have an issue
		logger.info("C3M Core :: Get the number of Deliver Specs in the deliver cic response message");
		String exp = "$.cic.deliver_spec_list.length()";
		int count = 0;
		count = JsonPath.read(strBody, exp);
		
		if (count<=0) {
			throw new RuntimeException("Delivered CIC message cannot be aggregated. No Deliver Specs found");
		}

	}
}
