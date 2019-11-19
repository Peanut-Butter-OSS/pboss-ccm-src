/**
 * 
 */
package org.pboss.ccm.model.api.request;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.pboss.ccm.TestObjectGenerator;
import org.pboss.ccm.model.api.request.ApiCredentials;
import org.pboss.ccm.model.api.request.ReqHeader;
import org.pboss.ccm.model.api.request.Cic;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * @author gregf
 *
 */
public class OnDemandOutboundCicRequestTest {

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
	public void deserialiseTest1() {
		
	    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
	    //mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	    //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	    try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("test-files/OnDemandOutboundCicRequest1.json").getFile());
			
			// Convert JSON string from file to Object
			OnDemandOutboundCicRequest req = mapper.readValue(file, OnDemandOutboundCicRequest.class);
			System.out.println(req.toString());

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//fail("Not yet implemented");
	}
	
	@Test
	public void deserialiseTest2() {
		
	    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
	    //mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	    //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	    try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("test-files/OnDemandOutboundCicRequest2.json").getFile());
			
			// Convert JSON string from file to Object
			OnDemandOutboundCicRequest req = mapper.readValue(file, OnDemandOutboundCicRequest.class);
			System.out.println(req.toString());

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//fail("Not yet implemented");
	}	
	
	@Test
	public void createComplexRequestJson() throws JsonProcessingException {
		
		OnDemandOutboundCicRequest req = TestObjectGenerator.createOnDemandOutboundCicRequest("COMPLEX");
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
		
		String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(req);
		
		System.out.println(jsonInString);

		//fail("Not yet implemented");
	}
	
	@Test
	public void createSimpleRequestJson() throws JsonProcessingException {
		
		OnDemandOutboundCicRequest req = TestObjectGenerator.createOnDemandOutboundCicRequest("SIMPLE");
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
		
		String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(req);
		
		System.out.println(jsonInString);

		//fail("Not yet implemented");
	}	

}
