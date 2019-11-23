package org.pboss.ccm.core.processors;

import java.util.Base64;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.jsonpath.JsonPath;

/**
 * This processor analyses the incoming message. It defines any headers that will be required 
 * for downstream processing
 * @author gregf
 *
 */
public class MessageAnalyser implements Processor {

  Logger logger = LoggerFactory.getLogger(MessageAnalyser.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		String strBody = exchange.getIn().getBody(String.class);
		
		// Count the number of CIC Parts in the message
		logger.info("C3M Core :: Get the number of CIC Parts in the original request");
		String exp = "$.cic.cic_part_list.length()";
		int count = 0;
		count = JsonPath.read(strBody, exp);
		exchange.getIn().setHeader("C3M_CicPartCount", count);
		logger.info("C3M Core :: Part count = " + count);
		
		// Count the number of Delivery Specs in the message
		logger.info("C3M Core :: Get the number of DeliverySpecs in the original request");
		String exp2 = "$.cic.deliver_spec_list.length()";
		int count2 = 0;
		count2 = JsonPath.read(strBody, exp2);
		exchange.getIn().setHeader("C3M_DeliverySpecCount", count2);
		logger.info("C3M Core :: Delivery Spec count = " + count2);
		
		// Convert the original message to Base64 and store it as a property
		//logger.info("Store the original request as a Base64 property");
		//byte[] encodedBytes = Base64.getEncoder().encode(strBody.getBytes());
		//exchange.getIn().setHeader("C3M_OriginalCicReqMsg", new String(encodedBytes));

	}
}
