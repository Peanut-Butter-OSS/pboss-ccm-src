package org.pboss.ccm.request.ondemand.validation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import org.pboss.ccm.model.api.request.CicPart;
import org.pboss.ccm.model.api.request.DeliverSpec;
import org.pboss.ccm.model.api.request.EmailDeliverSpec;
import org.pboss.ccm.model.api.request.OnDemandOutboundCicRequest;
import org.pboss.ccm.model.api.request.ValidationIssue;
import org.pboss.ccm.model.api.request.ValidationIssueLevel;
import org.pboss.ccm.model.api.request.ValidationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class CicRequestValidator {

  Logger logger = LoggerFactory.getLogger(CicRequestValidator.class);
  
  @Value("${api.version}")
  private String apiVersion;
  
  @PostConstruct
  public void init() {
  }
  
  public ValidationResult validateCicRequest(OnDemandOutboundCicRequest req) {
    
    logger.info("C3M Request Subsystem :: Validating CIC Request");
    
    ValidationResult result = new ValidationResult();
    List<ValidationIssue> issueList = new ArrayList<ValidationIssue>();
    
    // Validate that user did not supply the GUID in the request
    // TODO:
    
    // Validate API Version
    ValidationIssue apiIssue = this.validateApiVersion(req);
    if (apiIssue!=null) {
        issueList.add(apiIssue);
    }
    
    // Validate Delivery Specs
    List<ValidationIssue> deliveryIssues = this.validateDeliverySpecs(req);
    if (deliveryIssues!=null) {
        issueList.addAll(deliveryIssues);
    }
        
    // Add the final issue list to the result object
    result.setValidationIssueList(issueList);
    
    // Asses the overall validation result
    result = checkIfRequestIsAcceptable(result);
    
    return result;  
}

/** 
 * If the API was supplied
 *  - If it is different from the current system API, register a failure
 *  - If it is correct, register an INFO issue notifying user that it is not required to supply the version
 * @param req
 * @return
 */
private ValidationIssue validateApiVersion(OnDemandOutboundCicRequest req) {
    ValidationIssue issue = null;
    
    logger.info("System configured API version is: "+apiVersion);
    
    // Perform validations
    if (req.getReqHeader().getApiVersion()!=null) {
        
        issue = new ValidationIssue();
        
        if (req.getReqHeader().getApiVersion().equalsIgnoreCase(apiVersion)) {
            issue.setIssueCode("300001");
            issue.setIssueLevel(ValidationIssueLevel.INFO);
            issue.setIssueMsg("The correct API version was supplied in the request. Please note, it is not required to supply this value. The system will initialise with a default value");
        } else {
            issue.setIssueCode("400001");
            issue.setIssueLevel(ValidationIssueLevel.ERROR);
            issue.setIssueMsg("The supplied API version is not available. Either supply a valid API version or exclude the element altogether.");
        }           
    }
    
    return issue;
}


/**
 * Validate all aspects related to the delivery specs
 * 
 * @param req
 * @return
 */
private List<ValidationIssue> validateDeliverySpecs(OnDemandOutboundCicRequest req) {
    List<ValidationIssue> issueList = null;
    
    List<DeliverSpec> deliverSpecList = req.getCic().getDeliverSpecList();
    
    if (deliverSpecList!=null) {
        int i = 0;
        for (Iterator<DeliverSpec> iterator = deliverSpecList.iterator(); iterator.hasNext();) {
            DeliverSpec deliverSpec = (DeliverSpec) iterator.next();
            i++; 
            
            // Get the type of delivery spec. We will separately validate each
            String specClass = deliverSpec.getClass().getName();
            
            logger.info("C3M Request Subsystem :: Validating Delivery Spec. Spec #" + i + " Spec Type: " + specClass);
            
            switch (specClass) {
            case "org.pboss.ccm.model.api.request.EmailDeliverSpec":      
                List<ValidationIssue> emailIssueList = this.validateEmailDeliverySpec(deliverSpec, req);
                if (emailIssueList!=null) {
                    if (issueList==null) {
                        issueList = new ArrayList<ValidationIssue>();
                    }
                    issueList.addAll(emailIssueList);
                }
                break;
            case "org.pboss.ccm.model.api.request.EsignLiveDeliverSpec":      
                List<ValidationIssue> esignLiveIssueList = this.validateEsignLiveDeliverySpec(deliverSpec, req);
                if (esignLiveIssueList!=null) {
                    if (issueList==null) {
                        issueList = new ArrayList<ValidationIssue>();
                    }
                    issueList.addAll(esignLiveIssueList);
                }
                break;  
            case "org.pboss.ccm.model.api.request.SmsDeliverSpec":    
                List<ValidationIssue> smsIssueList = this.validateSmsDeliverySpec(deliverSpec, req);
                if (smsIssueList!=null) {
                    if (issueList==null) {
                        issueList = new ArrayList<ValidationIssue>();
                    }
                    issueList.addAll(smsIssueList);
                }
                break;                  
            default:                    
                if (issueList==null) {
                    issueList = new ArrayList<ValidationIssue>();
                }
                ValidationIssue issue = new ValidationIssue();
                issue.setIssueCode("600001");
                issue.setIssueLevel(ValidationIssueLevel.ERROR);
                issue.setIssueMsg("The specified delivery channel was not recognised.");
                issueList.add(issue);
                break;  
            }
        }
    } else {
        issueList = new ArrayList<ValidationIssue>();
        
        ValidationIssue issue = new ValidationIssue();
        issue.setIssueCode("600001");
        issue.setIssueLevel(ValidationIssueLevel.INFO);
        issue.setIssueMsg("No Delivery Spec was supplied in the request. Although this is a valid request, the CIC will not be delivered anywhere.");
        
        issueList.add(issue);
    }
    
    return issueList;
}

/**
 * Perform validations on email delivery spec
 * 
 * @param spec
 * @param req
 * @return
 */
private List<ValidationIssue> validateEmailDeliverySpec(DeliverSpec spec, OnDemandOutboundCicRequest req) {
    List<ValidationIssue> emailIssueList = null;
    
    EmailDeliverSpec emailSpec = (EmailDeliverSpec) spec;
    
    // Confirm that the parts referenced in the spec are actually defined in the CIC
    List<String> refPartsList = new ArrayList<String>();
    if (emailSpec.getPartIdSubject()!=null) {
        refPartsList.add(emailSpec.getPartIdSubject());
    }
    if (emailSpec.getPartIdBody()!=null) {
        refPartsList.add(emailSpec.getPartIdBody());
    }
    if (emailSpec.getAttachmentList()!=null) {
        refPartsList.addAll(emailSpec.getAttachmentList());
    }

    List<String> availablePartsList = new ArrayList<String>();
    for(CicPart p : req.getCic().getCicPartList()){
        availablePartsList.add(p.getPartId());
    }
    
    logger.info("Parts referenced by the delivery spec: " + refPartsList.toString());
    logger.info("Parts found in the request: " + availablePartsList.toString());
    
    refPartsList.removeAll(availablePartsList);
    
    if (refPartsList.size() > 0) {
        // Register an issue
        if (emailIssueList==null) {
            emailIssueList = new ArrayList<ValidationIssue>();
        }
        ValidationIssue issue = new ValidationIssue();
        issue.setIssueCode("500001");
        issue.setIssueLevel(ValidationIssueLevel.ERROR);
        issue.setIssueMsg("Not all referenced parts were found in the request. The following parts are missing: " + refPartsList.toString());
        emailIssueList.add(issue);
    }
    
    // TODO: Other Email Validations
    
    return emailIssueList;
}

private List<ValidationIssue> validateEsignLiveDeliverySpec(DeliverSpec spec, OnDemandOutboundCicRequest req) {
    List<ValidationIssue> esignLiveIssueList = null;
        
    return esignLiveIssueList;
}
    
private List<ValidationIssue> validateSmsDeliverySpec(DeliverSpec spec, OnDemandOutboundCicRequest req) {
    List<ValidationIssue> smsIssueList = null;
        
    smsIssueList = new ArrayList<ValidationIssue>();
    
    ValidationIssue issue = new ValidationIssue();
    issue.setIssueCode("700001");
    issue.setIssueLevel(ValidationIssueLevel.WARNING);
    issue.setIssueMsg("Support for SMS Delivery is not currently available.");  
    smsIssueList.add(issue);
    
    return smsIssueList;
}   

/**
 * Analyse the list of reported issues to determine if the request is acceptable.
 * 
 * At the moment this analysis really just checks if any ERROR level issues were reported
 * 
 * @param validationIssueList
 * @return
 */
private ValidationResult checkIfRequestIsAcceptable (ValidationResult validationResult) {

    int infoCount = 0;
    int warnCount = 0;  
    int errorCount = 0;
    
    List<ValidationIssue> validationIssueList = validationResult.getValidationIssueList();
    
    for (Iterator<ValidationIssue> iterator = validationIssueList.iterator(); iterator.hasNext();) {
        ValidationIssue validationIssue = (ValidationIssue) iterator.next();
        
        if (validationIssue.getIssueLevel()==ValidationIssueLevel.ERROR) {
            errorCount++;
        } else if (validationIssue.getIssueLevel()==ValidationIssueLevel.WARNING) {
            warnCount++;
        } else if (validationIssue.getIssueLevel()==ValidationIssueLevel.INFO) {
            infoCount++;
        } 
    }
    
    validationResult.setErrorCount(errorCount);
    validationResult.setWarnCount(warnCount);
    validationResult.setInfoCount(infoCount);
    
    if (errorCount>0) {
        validationResult.setValid(false);
    } else {
        validationResult.setValid(true);
    }
    
    return validationResult;
}
  
}
