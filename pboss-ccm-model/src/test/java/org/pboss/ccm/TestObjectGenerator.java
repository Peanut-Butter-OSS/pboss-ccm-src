/**
 * 
 */
package org.pboss.ccm;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pboss.ccm.model.api.request.ApiCredentials;
import org.pboss.ccm.model.api.request.ReqHeader;
import org.pboss.ccm.model.api.request.AssembledCicPart;
import org.pboss.ccm.model.api.request.Cic;
import org.pboss.ccm.model.api.request.CicLifeCycleDetail;
import org.pboss.ccm.model.api.request.CicPart;
import org.pboss.ccm.model.api.request.CicSubscriptionDetail;
import org.pboss.ccm.model.api.request.CustomAssemblySpec;
import org.pboss.ccm.model.api.request.DdxAssemblySpec;
import org.pboss.ccm.model.api.request.DeliverSpec;
import org.pboss.ccm.model.api.request.EcrionRenderSpec;
import org.pboss.ccm.model.api.request.EmailDeliverSpec;
import org.pboss.ccm.model.api.request.FopRenderSpec;
import org.pboss.ccm.model.api.request.FreemarkerRenderSpec;
import org.pboss.ccm.model.api.request.IncludeSpec;
import org.pboss.ccm.model.api.request.IncludedCicPart;
import org.pboss.ccm.model.api.request.ItextRenderSpec;
import org.pboss.ccm.model.api.request.LinuxFsReferenceSpec;
import org.pboss.ccm.model.api.request.LinuxFsStoreSpec;
import org.pboss.ccm.model.api.request.PayloadType;
import org.pboss.ccm.model.api.request.ReferencedCicPart;
import org.pboss.ccm.model.api.request.RenderSpec;
import org.pboss.ccm.model.api.request.RenderedCicPart;
import org.pboss.ccm.model.api.request.SmsDeliverSpec;
import org.pboss.ccm.model.api.request.StoreSpec;
import org.pboss.ccm.model.api.request.WinFsReferenceSpec;
import org.pboss.ccm.model.api.request.WinFsStoreSpec;
import org.pboss.ccm.model.api.request.XsltRenderSpec;
import org.pboss.ccm.model.api.request.OnDemandOutboundCicRequest;

/**
 * This is a utility class for generating objects used in the unit tests
 * 
 * @author gregf
 *
 */
public class TestObjectGenerator {

	public static ApiCredentials createApiCredentials() {
		ApiCredentials apiCred = new ApiCredentials();
		
		apiCred.setAccountName("Test Account");
		apiCred.setApiKey("XXXKEYZZZ");
		
		return apiCred;
	}
	
	public static ReqHeader createC3mReqHeader() {
		ReqHeader head = new ReqHeader();
	
		head.setApiCredentials(createApiCredentials());
		head.setApiVersion("0.1");
		head.setCorrId("COR1");
		head.setRequestTimestamp(OffsetDateTime.now());
		head.setTestLevel(1);
		
		return head;
	}
	
	public static Map<String, Object> createCustomMetadata() {
		Map<String, Object> map = new HashMap();
		
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", 3);
		
		return map;
	}
	
	public static Cic creatCic(String flavour) {
		Cic cic = new Cic();
		
		if (flavour.equalsIgnoreCase("COMPLEX")) {
			cic.setCicDateTime(OffsetDateTime.now());
			cic.setCicTypeCode("CICTYPE1");
			cic.setCicLifeCycleDetail(createCicLifeCycleDetail());
			cic.setCicSubsDetail(createCicSubscriptionDetail());
			cic.setCustomMetadata(createCustomMetadata());
			cic.setCicPartList(createCicPartList(flavour));
			cic.setStoreSpecList(createStoreSpecList(flavour));
			cic.setDeliverSpecList(createDeliverSpecList(flavour));			
		} else if (flavour.equalsIgnoreCase("SIMPLE")) {
			cic.setCicDateTime(OffsetDateTime.now());
			cic.setCicTypeCode("CICTYPE1");
			cic.setCicPartList(createCicPartList(flavour));
			cic.setStoreSpecList(createStoreSpecList(flavour));
			cic.setDeliverSpecList(createDeliverSpecList(flavour));				
		}
			
		return cic;
	}
	
	public static OnDemandOutboundCicRequest createOnDemandOutboundCicRequest(String flavour) {
		OnDemandOutboundCicRequest odocr = new OnDemandOutboundCicRequest();
		
		odocr.setReqHeader(createC3mReqHeader());
		odocr.setReqGuid("GUID");
		odocr.setCic(creatCic(flavour));

		return odocr;
	}
	
	public static CicLifeCycleDetail createCicLifeCycleDetail() {
		CicLifeCycleDetail lc = new CicLifeCycleDetail();
		
		lc.setLifeSpanMinutes(60);
		
		return lc;
	}
	
	public static CicSubscriptionDetail createCicSubscriptionDetail() {
		CicSubscriptionDetail subs = new CicSubscriptionDetail();
		
		subs.setOverrideSubs(true);
		subs.setSubsOverrideKey("KEY");
		
		return subs;
	}
	
	public static List<CicPart> createCicPartList(String flavour) {
		List<CicPart> cicPartList = new ArrayList<CicPart>();
	
		if (flavour.equalsIgnoreCase("COMPLEX")) {
			cicPartList.add(createCicPart(1, "RENDER", 1));
			cicPartList.add(createCicPart(2, "RENDER", 2));
			cicPartList.add(createCicPart(3, "RENDER", 3));
			cicPartList.add(createCicPart(4, "RENDER", 4));
			cicPartList.add(createCicPart(5, "RENDER", 5));
			cicPartList.add(createCicPart(6, "RENDER", 6));
			cicPartList.add(createCicPart(7, "RENDER", 7));
			cicPartList.add(createCicPart(8, "INCLUDED_TEXT", 1));
			cicPartList.add(createCicPart(9, "INCLUDED_TEXT", 2));
			cicPartList.add(createCicPart(10, "INCLUDED_TEXT", 3));
			cicPartList.add(createCicPart(11, "ASSEMBLE", 1));
			cicPartList.add(createCicPart(12, "ASSEMBLE", 2));
			cicPartList.add(createCicPart(13, "REFERENCE", 1));			
		} else if (flavour.equalsIgnoreCase("SIMPLE")) {
			cicPartList.add(createCicPart(1, "RENDER", 1));
		}
		
		return cicPartList;
	}
	
	public static CicPart createCicPart(int uniqueId, String createType, 
										int createFlavour) {
		
		CicPart cicPart = null;
		
		switch (createType){
		case "RENDER": 	cicPart = createRenderedCicPart(uniqueId, createFlavour);
						break;
		case "INCLUDED_TEXT": 	
							cicPart = createIncludedTextCicPart(uniqueId, createFlavour);
							break;		
		case "ASSEMBLE":	String fileName = "assembled".concat(Integer.toString(createFlavour)).concat("pdf");  
							cicPart = createAssembledCicPart(uniqueId, fileName, createFlavour);
							break;		
		case "REFERENCE":	fileName = "referenced".concat(Integer.toString(createFlavour)).concat("pdf"); 
							cicPart = createReferencedCicPart(uniqueId, fileName, createFlavour);
							break;								
		default : 	
			break;
		}
	
		
		return cicPart;
	}
	
	public static CicPart createRenderedCicPart(int uniqueId, int createFlavour) {
		//CicPart cicPart = new CicPart();
		RenderedCicPart cicPart = new RenderedCicPart();
		
		String fileExtension;
		String payload = "";
		String description = null;
		
		switch (createFlavour){
		case 1: fileExtension = ".txt";
				// FREEMARKER XML CONFIGURED TEMPLATE
				description = "RENDERED :: FREEMARKER XML CONFIGURED TEMPLATE";
				payload = "<letter><name>Greg</name><surname>Fullard</surname></letter>";
				FreemarkerRenderSpec fmRenderSpec1 = new FreemarkerRenderSpec();
				fmRenderSpec1.setPayloadType(PayloadType.XML);
				fmRenderSpec1.setRenderPayload(payload);
				cicPart.setRenderSpec(fmRenderSpec1);
				break;
		case 2: fileExtension = ".txt";
				// FREEMARKER JSON CONFIGURED TEMPLATE
				description = "RENDERED :: FREEMARKER JSON CONFIGURED TEMPLATE";
				payload = "{ \"name\" : \"Greg\", \"surname\" : \"Fullard\" }";
				FreemarkerRenderSpec fmRenderSpec2 = new FreemarkerRenderSpec();
				fmRenderSpec2.setPayloadType(PayloadType.JSON);
				fmRenderSpec2.setRenderPayload(payload);
				cicPart.setRenderSpec(fmRenderSpec2);
				break;			
		case 3: fileExtension = ".txt";
				// FREEMARKER JSON RUNTIME TEMPLATE
				description = "RENDERED :: FREEMARKER JSON RUNTIME TEMPLATE";
				payload = "{ \"name\" : \"Greg\", \"surname\" : \"Fullard\" }";
				FreemarkerRenderSpec fmRenderSpec3 = new FreemarkerRenderSpec();
				fmRenderSpec3.setPayloadType(PayloadType.JSON);
				fmRenderSpec3.setRenderPayload(payload);
				String runtimeTemplate = "";
				runtimeTemplate += "This is a runtime template \n";
				runtimeTemplate += "- Name:    ${body.name} \n";
				runtimeTemplate += "- Surname: ${body.surname} \n";
				fmRenderSpec3.setRuntimeTemplate(runtimeTemplate);
				cicPart.setRenderSpec(fmRenderSpec3);
				break;					
		case 4: fileExtension = ".pdf";
				// FOP
				description = "RENDERED :: FOP";
				payload = "<letter><name>Greg</name><surname>Fullard</surname></letter>";
				FopRenderSpec fopRenderSpec = new FopRenderSpec();
				fopRenderSpec.setRenderPayload(payload);
				cicPart.setRenderSpec(fopRenderSpec);		
				break;
		case 5:	fileExtension = ".pdf";
				// ECRION
				description = "RENDERED :: ECRION";
				payload = "<letter><name>Greg</name><surname>Fullard</surname></letter>";
				EcrionRenderSpec ecrionRenderSpec = new EcrionRenderSpec();
				ecrionRenderSpec.setRenderPayload(payload);
				cicPart.setRenderSpec(ecrionRenderSpec);	
				break;	
		case 6: fileExtension = ".html";
				// XSLT
				description = "RENDERED :: XSLT";
				payload = "<letter><name>Greg</name><surname>Fullard</surname></letter>";
				XsltRenderSpec xsltRenderSpec = new XsltRenderSpec();
				xsltRenderSpec.setRenderPayload(payload);
				cicPart.setRenderSpec(xsltRenderSpec);	
				break;	
		case 7:	fileExtension = ".pdf";
				// ITEXT
				description = "RENDERED :: ITEXT";
				payload = "<letter><name>Greg</name><surname>Fullard</surname></letter>";
				ItextRenderSpec itextRenderSpec = new ItextRenderSpec();
				itextRenderSpec.setRenderPayload(payload);
				cicPart.setRenderSpec(itextRenderSpec);	
				break;
		default :	fileExtension = ".pdf";
			break;
		}
		
		String partType = "PT" + uniqueId;
		String fileName = "FileName" + uniqueId + fileExtension;
		String partId = Integer.toString(uniqueId);
		
		cicPart.setCicPartTypeCode(partType);
		cicPart.setFileName(fileName);
		cicPart.setPartId(partId);
		cicPart.setDescription(description);
		
		return cicPart;
	}	
	
	public static CicPart createIncludedTextCicPart(int uniqueId, int flavour) {
		IncludedCicPart cicPart = new IncludedCicPart();
		
		String fileExtension = null; 
		String content = null;
		String contentType = null;
		String description = null;
		
		switch (flavour) {
		case 1: fileExtension = ".txt";
				content = "Included text as part of flavour 1";
				contentType = "text/plain";
				description = "INCLUDED :: Simple Text";
				break;
		case 2: fileExtension = ".html";
				content = "<html><body><p>Included HTML as part of flavour 2</p><body></html>";
				contentType = "text/html";
				description = "INCLUDED :: HTML Text";
				break;
		case 3: fileExtension = ".xml";
				content = "<html><body><p>Included XML as part of flavour 2</p><body></html>";
				contentType = "text/xml";
				description = "INCLUDED :: HTML Text";
				break;
		default: fileExtension = "";
			break;			
		}
		
		String partType = "PT" + uniqueId;
		String fileName = "FileName" + uniqueId + fileExtension;
		String partId = Integer.toString(uniqueId);
		
		cicPart.setCicPartTypeCode(partType);
		cicPart.setFileName(fileName);
		cicPart.setPartId(partId);
		cicPart.setDescription(description);
		
		IncludeSpec includeSpec = new IncludeSpec();
		includeSpec.setContent(content);
		includeSpec.setContentType(contentType);
		
		cicPart.setIncludeSpec(includeSpec);
		
		return cicPart;
	}	
	
	public static CicPart createAssembledCicPart(int uniqueId, String fileName, int flavour) {

		AssembledCicPart cicPart = new AssembledCicPart();
		
		String partType = "PT" + uniqueId;
		String partId = Integer.toString(uniqueId);
		String description = null;
		
		cicPart.setCicPartTypeCode(partType);
		cicPart.setFileName(fileName);
		cicPart.setPartId(partId);
		
		if (flavour==1) {
			DdxAssemblySpec ddxAssemblySpec = new DdxAssemblySpec();
			ddxAssemblySpec.setRuntimeDdx("dummyDDX.xml");
			cicPart.setAssemblySpec(ddxAssemblySpec);
			description = "ASSEMBLED :: DDX";
		} else if (flavour==2) {
			CustomAssemblySpec custAssemblySpec = new CustomAssemblySpec();
			custAssemblySpec.setReqQueue("REQ.DUMMY");
			custAssemblySpec.setRespQueue("RESP.DUMMY");
			cicPart.setAssemblySpec(custAssemblySpec);
			description = "ASSEMBLED :: Custom";
		}
		
		cicPart.setDescription(description);
		return cicPart;
	}		
	
	public static CicPart createReferencedCicPart(int uniqueId, String fileName, int flavour) {
		ReferencedCicPart cicPart = new ReferencedCicPart();
		
		String partType = "PT" + uniqueId;
		String partId = Integer.toString(uniqueId);
		String description = null;
		
		cicPart.setCicPartTypeCode(partType);
		cicPart.setFileName(fileName);
		cicPart.setPartId(partId);
		
		if (flavour==1) {
			LinuxFsReferenceSpec spec = new LinuxFsReferenceSpec();
			spec.setFilePath("/home/user/test.pdf");
			cicPart.setRefSpec(spec);
			description = "REFERENCED :: FROM LINUX FILE PATH";
		} else if (flavour==2) {
			WinFsReferenceSpec spec = new WinFsReferenceSpec();
			spec.setFilePath("C:\test.pdf");
			cicPart.setRefSpec(spec);
			description = "REFERENCED :: FROM WINDOWS FILE PATH";
		}
		
		cicPart.setDescription(description);
		return cicPart;
	}
	
	public static List<StoreSpec> createStoreSpecList(String flavour) {
		List<StoreSpec> storeSpecList = new ArrayList<StoreSpec>();

		if (flavour.equalsIgnoreCase("COMPLEX")) {
			storeSpecList.add(createStoreSpec(1, "LINUXFS",1));
			storeSpecList.add(createStoreSpec(2, "WINFS",1));
		} else if (flavour.equalsIgnoreCase("SIMPLE")) {
			storeSpecList.add(createStoreSpec(1, "LINUXFS",1));
		}
		
		return storeSpecList;
	}	
	
	public static StoreSpec createStoreSpec(int uniqueId, String storeType, int storeFlavour ) {
		StoreSpec ss = new StoreSpec();	

		switch (storeType) {
		case "LINUXFS":	LinuxFsStoreSpec lss = createLinuxFsStoreSpec(storeFlavour);
						ss = lss;
						break;
		case "WINFS":	WinFsStoreSpec wss = createWinFsStoreSpec(storeFlavour);
						ss = wss;
						break;		
		default : 	
			break;			
		}
		
		ss.setPartId(Integer.toString(uniqueId));
		
		return ss;
	}
	
	public static LinuxFsStoreSpec createLinuxFsStoreSpec(int storeFlavour) {
		LinuxFsStoreSpec lss = new LinuxFsStoreSpec();
		
		if (storeFlavour==1) {
			lss.setFilePath("/home/gregf/test.pdf");
			lss.setMakeUnique(false);
			lss.setOverwrite(true);
		}
		
		return lss;
	}
	
	public static WinFsStoreSpec createWinFsStoreSpec(int storeFlavour) {
		WinFsStoreSpec lss = new WinFsStoreSpec();
		
		if (storeFlavour==1) {
			lss.setFilePath("C:\test.pdf");
			lss.setMakeUnique(false);
			lss.setOverwrite(true);
		}
		
		return lss;
	}	
	
	public static List<DeliverSpec> createDeliverSpecList(String flavour) {
		List<DeliverSpec> deliverSpecList = new ArrayList<DeliverSpec>();
	
		if (flavour.equalsIgnoreCase("COMPLEX")) {
			deliverSpecList.add(createSmsDeliverSpec("1"));
			deliverSpecList.add(createEmailDeliverSpecNoAttach("1","4"));
			
			List<String> attachPartIdList = new ArrayList<String>();
			attachPartIdList.add("2");
			attachPartIdList.add("3");
			deliverSpecList.add(createEmailDeliverSpecWithAttach("1","4",attachPartIdList));			
		} else if (flavour.equalsIgnoreCase("SIMPLE")) {
			deliverSpecList.add(createSmsDeliverSpec("1"));
		}
		
		return deliverSpecList;
	}	
	
	public static DeliverSpec createSmsDeliverSpec(String partIdSmsBody) {
		SmsDeliverSpec ds = new SmsDeliverSpec();
		
		ds.setMobileNumber("1234567890");
		ds.setPartIdSmsBody(partIdSmsBody);
		
		return ds;
	}
	
	public static DeliverSpec createEmailDeliverSpecNoAttach(String partIdSubject,
													 String partIdBody){
		EmailDeliverSpec ds = new EmailDeliverSpec();
		
		ds.setToAddress("test@test.com");
		ds.setFromAddress("testfrom@test.com");
		ds.setPartIdSubject(partIdSubject);
		ds.setPartIdBody(partIdBody);
		
		return ds;	
	}
	
	public static DeliverSpec createEmailDeliverSpecWithAttach(String partIdSubject,
			 String partIdBody,
			 List<String> attachmentPartIdList){
		EmailDeliverSpec ds = new EmailDeliverSpec();

		ds.setToAddress("test@test.com");
		ds.setFromAddress("testfrom@test.com");
		ds.setPartIdSubject(partIdSubject);
		ds.setPartIdBody(partIdBody);
		ds.setAttachmentList(attachmentPartIdList);

		return ds;	
	}
	
	/*
	public static RenderedCicPart zzzcreatePartRenderingSpec(int uniqueId, String renderer) {
		RenderedCicPart renderSpec = new RenderedCicPart();
		
		renderSpec.setRenderPayload("<letter><name>Greg</name><surname>Fullard</surname></letter>");
		
		String templateName = null;
		
		switch (renderer){
		case "FREEMARKER":  templateName = "frm_"+uniqueId;
			break;
		case "FOP": 		templateName = "fop_"+uniqueId;
			break;
		case "ECRION":  	templateName = "ecr_"+uniqueId;
			break;	
		case "XSLT": 		templateName = "xsl_"+uniqueId;
			break;	
		case "ITEXT": 		templateName = "itxt_"+uniqueId;
			break;
		default : 			
			break;
		}
		
		return renderSpec;
	}*/
}
