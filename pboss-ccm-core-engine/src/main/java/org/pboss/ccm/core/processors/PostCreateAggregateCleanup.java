/**
 * 
 */
package org.pboss.ccm.core.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gregf
 *
 */
public class PostCreateAggregateCleanup implements Processor {
	
  Logger logger = LoggerFactory.getLogger(MessageAnalyser.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		
		// Remove CicPart-specific headers
		exchange.getIn().removeHeader("C3M_PartGuid");
		exchange.getIn().removeHeader("C3M_PartId");
		
	}

}
