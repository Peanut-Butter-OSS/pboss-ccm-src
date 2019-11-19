/**
 * 
 */
package org.pboss.ccm.model.api.request;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.pboss.ccm.model.api.request.ApiCredentials;
import org.pboss.ccm.model.api.request.ReqHeader;
import org.pboss.ccm.model.api.request.RespHeader;
import org.pboss.ccm.model.api.request.Cic;
import org.pboss.ccm.TestObjectGenerator;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

/**
 * @author gregf
 *
 */
public class OnDemandOutboundCicResponseTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void serialiseSuccessRespTest() {
		
	    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

	    try {
	    	OnDemandOutboundCicResponse resp = this.buildResp("SUCCESS");
			
			// Convert object to JSON
	    	String jsonInString = mapper.writeValueAsString(resp);
			System.out.println(jsonInString);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void serialiseFailedRespTest() {
		
	    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

	    try {
	    	OnDemandOutboundCicResponse resp = this.buildResp("FAIL");
			
			// Convert object to JSON
	    	String jsonInString = mapper.writeValueAsString(resp);
			System.out.println(jsonInString);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void deserialiseTest1() {
		
	    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

	    try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("test-files/OnDemandOutboundCicResponse1.json").getFile());
			
			// Convert JSON string from file to Object
			OnDemandOutboundCicResponse resp = mapper.readValue(file, OnDemandOutboundCicResponse.class);
			System.out.println(resp.toString());

			assertEquals("1.1.0", resp.getC3mRespHeader().getApiVersion());
			assertEquals("COR1", resp.getC3mRespHeader().getCorrId());
			assertEquals("942ad0c7-9e68-4102-9a00-893d5fa2749e", resp.getC3mRespHeader().getReqGuid());
			assertEquals(0, resp.getC3mRespHeader().getRespCode());
			assertEquals("Successfully submitted CIC Request to C3M Core", resp.getC3mRespHeader().getRespMsg());
			assertEquals(2017, resp.getC3mRespHeader().getResponseTimestamp().getYear());
			assertEquals(2, resp.getC3mRespHeader().getResponseTimestamp().getMonthValue());
			assertEquals(9, resp.getC3mRespHeader().getResponseTimestamp().getDayOfMonth());
			assertEquals(ZoneOffset.ofHours(0), resp.getC3mRespHeader().getResponseTimestamp().getOffset());
			assertEquals(13, resp.getC3mRespHeader().getResponseTimestamp().getHour());
			assertEquals(18, resp.getC3mRespHeader().getResponseTimestamp().getMinute());
			assertEquals(22, resp.getC3mRespHeader().getResponseTimestamp().getSecond());

			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//fail("Not yet implemented");
	}
	
	private OnDemandOutboundCicResponse buildResp(String flavour) {
		OnDemandOutboundCicResponse resp = new OnDemandOutboundCicResponse();
		RespHeader c3mRespHeader = new RespHeader();
		
		c3mRespHeader.setResponseTimestamp(OffsetDateTime.now());
		c3mRespHeader.setApiVersion("1.0");
		c3mRespHeader.setCorrId("Test");
		c3mRespHeader.setReqGuid("XYZ");
		
		if (flavour.equalsIgnoreCase("SUCCESS")) { 
			c3mRespHeader.setRespCode(0);
			c3mRespHeader.setRespMsg("Some message");
		} else if (flavour.equalsIgnoreCase("FAIL")) {
			c3mRespHeader.setRespCode(1);
			c3mRespHeader.setRespMsg("Some message");
			c3mRespHeader.setErrorId(12345);
		}
		
		resp.setC3mRespHeader(c3mRespHeader);
		
		return resp;
	}
}
