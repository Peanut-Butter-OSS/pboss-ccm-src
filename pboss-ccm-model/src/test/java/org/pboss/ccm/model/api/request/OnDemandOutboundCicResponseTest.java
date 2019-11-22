package org.pboss.ccm.model.api.request;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;
import org.pboss.ccm.TestObjectGenerator;
import org.pboss.ccm.model.api.request.OnDemandOutboundCicRequest;
import org.pboss.ccm.model.api.request.OnDemandOutboundCicResponse;
import org.pboss.ccm.model.api.request.RespHeader;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
class OnDemandOutboundCicResponseTest {

  @Test
  void contextLoads() {}

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

          assertEquals("1.1.0", resp.getRespHeader().getApiVersion());
          assertEquals("COR1", resp.getRespHeader().getCorrId());
          assertEquals("942ad0c7-9e68-4102-9a00-893d5fa2749e", resp.getRespHeader().getReqGuid());
          assertEquals(0, resp.getRespHeader().getRespCode());
          assertEquals("Successfully submitted CIC Request to CCM Core", resp.getRespHeader().getRespMsg());
          assertEquals(2017, resp.getRespHeader().getResponseTimestamp().getYear());
          assertEquals(2, resp.getRespHeader().getResponseTimestamp().getMonthValue());
          assertEquals(9, resp.getRespHeader().getResponseTimestamp().getDayOfMonth());
          assertEquals(ZoneOffset.ofHours(0), resp.getRespHeader().getResponseTimestamp().getOffset());
          assertEquals(13, resp.getRespHeader().getResponseTimestamp().getHour());
          assertEquals(18, resp.getRespHeader().getResponseTimestamp().getMinute());
          assertEquals(22, resp.getRespHeader().getResponseTimestamp().getSecond());

          
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
      RespHeader respHeader = new RespHeader();
      
      respHeader.setResponseTimestamp(OffsetDateTime.now());
      respHeader.setApiVersion("1.0");
      respHeader.setCorrId("Test");
      respHeader.setReqGuid("XYZ");
      
      if (flavour.equalsIgnoreCase("SUCCESS")) { 
          respHeader.setRespCode(0);
          respHeader.setRespMsg("Some message");
      } else if (flavour.equalsIgnoreCase("FAIL")) {
          respHeader.setRespCode(1);
          respHeader.setRespMsg("Some message");
          respHeader.setErrorId(12345);
      }
      
      resp.setRespHeader(respHeader);
      
      return resp;
  }
}
