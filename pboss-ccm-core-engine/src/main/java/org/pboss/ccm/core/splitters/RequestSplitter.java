/**
 * 
 */
package org.pboss.ccm.core.splitters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.pboss.ccm.model.api.request.CicPart;
import org.pboss.ccm.model.api.request.DeliverSpec;
import org.pboss.ccm.model.api.request.EmailDeliverSpec;
import org.pboss.ccm.model.api.request.OnDemandOutboundCicRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * This class contains methods that allow us to split the CIC request message.
 * It was built specifically so the the split result contains the entire original
 * message, but only one element of a repeating section. For example: If a message
 * comes in with many Delivery Specs, then we may split it into multiple messages,
 * each containing only a single delivery spec.
 * 
 * @author gregf
 *
 */
public class RequestSplitter {
	
  Logger logger = LoggerFactory.getLogger(RequestSplitter.class);

	/**
	 * Splits an OnDemandOutboundCicRequest into a List of requests, each containing
	 * exactly one CicPart. 
	 * 
	 * NOTE: This approach to splitting requires the creation of multiple clones of a 
	 * message. I've chosen to use Jackson to easily create clones instead of coding 
	 * a clone method in the OnDemandOutboundCicRequest bean.
	 * 
	 * @param body
	 * @param headers
	 * @return
	 */
    public List<String> splitByCicPart(String origBody) throws RuntimeException {
    	logger.info("C3M Core :: Splitting message by CicPart");
    	
    	List<String> msgList = new ArrayList<String>();
 
    	try {
        	// Unmarshal the delivery spec to Java so that we can use it properly
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        	OnDemandOutboundCicRequest cicReq = mapper.readValue(origBody, OnDemandOutboundCicRequest.class);    	
        	
        	// First count how many cic parts we have.
        	// If we only have one Cic Part in the original msg, then nothing further is required
        	int count = cicReq.getCic().getCicPartList().size();
        	logger.info("C3M Core :: Number of CIC Parts in the original message: " + count);
        	if (count == 1) {
        		logger.info("C3M Core :: Just one CIC Part, so we simply return the original body");
        		msgList.add(origBody);
        	} else {
        		// Iterate over each of the CIC Parts, building a new message for each.
        		// The new message will look exactly like the original, but will have exactly one
        		// CIC Part
        		List<CicPart> cicPartList = cicReq.getCic().getCicPartList();
    			for(int i=0; i<count; i++){

    				logger.info("Iterating through the CicParts");
    				logger.info("i = " + i);

    				// Create a new clone of the message body
    				OnDemandOutboundCicRequest cicReqClone = mapper.readValue(origBody, OnDemandOutboundCicRequest.class); 
 
    				// Remove all Cic Parts except the current one from the clone
    				String cicPartGuid = cicReqClone.getCic().getCicPartList().get(i).getPartGuid();
    				int left = cicReqClone.getCic().getCicPartList().size();
    				int n = 0;
    				while (left > 1) {
    					String compareGuid = cicReqClone.getCic().getCicPartList().get(n).getPartGuid();
    					if (cicPartGuid.equals(compareGuid)) {
        					n++;    						
    					} else {
    						cicReqClone.getCic().getCicPartList().remove(n); 
    					}
    					left = cicReqClone.getCic().getCicPartList().size();
    				}
 
    				// Add the final object to the List
    				String newMsg =  mapper.writeValueAsString(cicReqClone);
    				msgList.add(newMsg);
    			}
    			
        	} 
    	}
       	catch (JsonParseException e) {
       		String msg = "JsonParseException occurred while trying to split OnDemandOutboundCicRequest by CicPart. ";
       		msg += "Exception message: " + e.getMessage(); 
       		logger.error(msg,e);
       		throw new RuntimeException(msg);
		} catch (JsonMappingException e) {
       		String msg = "JsonMappingException occurred while trying to split OnDemandOutboundCicRequest by CicPart. ";
       		msg += "Exception message: " + e.getMessage();  
       		logger.error(msg,e);
       		throw new RuntimeException(msg);
		} catch (IOException e) {
       		String msg = "IOException occurred while trying to split OnDemandOutboundCicRequest by CicPart. ";
       		msg += "Exception message: " + e.getMessage();  
       		logger.error(msg,e);
       		throw new RuntimeException(msg);
		}

   		return msgList;
    }	
	
	/**
	 * Splits an OnDemandOutboundCicRequest into a List of requests, each containing
	 * exactly one DeliverySpec. 
	 * 
	 * NOTE: This approach to splitting requires the creation of multiple clones of a 
	 * message. I've chosen to use Jackson to easily create clones instead of coding 
	 * a clone method in the OnDemandOutboundCicRequest bean.
	 * 
	 * @param body
	 * @param headers
	 * @return
	 */
    public List<String> splitByDeliverySpec(String origBody) throws RuntimeException {
    	logger.info("C3M Core :: Splitting message by DeliverySpec");
    	
    	List<String> msgList = new ArrayList<String>();
 
    	try {
        	// Unmarshal the delivery spec to Java so that we can use it properly
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        	OnDemandOutboundCicRequest cicReq = mapper.readValue(origBody, OnDemandOutboundCicRequest.class);    	
        	
        	// First count how many delivery specs we have.
        	// If we only have one Delivery spec in the original msg, then nothing further is required
        	int count = 0;
        	List<DeliverSpec> deliverSpecList = cicReq.getCic().getDeliverSpecList();
        	if (deliverSpecList!=null) {
            	count = deliverSpecList.size();        		
        	}
        	logger.info("C3M Core :: Number of delivery specs in the original message: " + count);
        	if (count == 0) {
        		throw new RuntimeException("Cannot split by Delivery Specs. No delivery specs found in the message");
        	} else if (count == 1) {
        		logger.info("C3M Core :: Just one spec, so we simply return the original body");
        		msgList.add(origBody);
        	} else {
        		// Iterate over each of the delivery specs, building a new message for each.
        		// The new message will look exactly like the original, but will have exactly one
        		// DeliverySpec
    			for(int i=0; i<count; i++){

    				logger.info("Iterating through the DeliverSpecs");
    				logger.info("i = " + i);

    				// Create a new clone of the message body
    				OnDemandOutboundCicRequest cicReqClone = mapper.readValue(origBody, OnDemandOutboundCicRequest.class); 
 
    				// Remove all Deliver Specs except the current one from the clone
    				String deliverSpecGuid = cicReqClone.getCic().getDeliverSpecList().get(i).getDeliverSpecGuid();
    				int left = cicReqClone.getCic().getDeliverSpecList().size();
    				int n = 0;
    				while (left > 1) {
    					String compareGuid = cicReqClone.getCic().getDeliverSpecList().get(n).getDeliverSpecGuid();
    					if (deliverSpecGuid.equals(compareGuid)) {
        					n++;    						
    					} else {
    						cicReqClone.getCic().getDeliverSpecList().remove(n); 
    					}
    					left = cicReqClone.getCic().getDeliverSpecList().size();
    				}    				
 
    				// Add the final object to the List
    				String newMsg =  mapper.writeValueAsString(cicReqClone);
    				msgList.add(newMsg);
    			}
    			
        	} 
    	}
       	catch (JsonParseException e) {
       		String msg = "JsonParseException occurred while trying to split OnDemandOutboundCicRequest by DeliverSpec. ";
       		msg += "Exception message: " + e.getMessage(); 
       		logger.error(msg,e);
       		throw new RuntimeException(msg);
		} catch (JsonMappingException e) {
       		String msg = "JsonMappingException occurred while trying to split OnDemandOutboundCicRequest by DeliverSpec. ";
       		msg += "Exception message: " + e.getMessage();   
       		logger.error(msg,e);
       		throw new RuntimeException(msg);
		} catch (IOException e) {
       		String msg = "IOException occurred while trying to split OnDemandOutboundCicRequest by DeliverSpec. ";
       		msg += "Exception message: " + e.getMessage();  
       		logger.error(msg,e);
       		throw new RuntimeException(msg);
		} catch (RuntimeException e) {
       		String msg = "RuntimeException occurred while trying to split OnDemandOutboundCicRequest by DeliverSpec. ";
       		msg += "Exception message: " + e.getMessage();
       		logger.error(msg,e);
       		throw new RuntimeException(msg);
		}

   		return msgList;
    }
}
