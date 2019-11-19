/**
 * 
 */
package org.pboss.ccm.model.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author gregf
 * 
 * Issue Code Ranges: 
 * 1XXXXX: Issue with account
 * 2XXXXX: A required element is not supplied
 * 3XXXXX: An element was supplied that should NOT have been supplied
 * 4XXXXX: Problem with a supplied element
 * 5XXXXX: The request is inconsistent 
 * 6XXXXX: An optional (but important element was not supplied
 * 7XXXXX: Unsupported component
 * 
 * Issue Codes:
 * 
 * 300001: INFO,   	The correct API version was supplied in the request. Please note, it is not required to supply this value. The system will initialise with a default value
 *
 *
 * 400001: ERROR,  	The supplied API version is not available. Either supply a valid API version or exclude the element altogether.
 *
 * 500001: ERROR,	Not all referenced parts were found in the request
 *
 * 600001: INFO,	No Delivery Spec was supplied in the request. Although this is a valid request, the CIC will not be delivered anywhere.
 * 
 * 
 * 700001: WARN,	Support for SMS Delivery is not currently available. 
 * 700002: ERROR,	The specified delivery channel was not recognised.  
 */
@JsonTypeName("validation_issue")
@JsonInclude(Include.NON_NULL)
public class ValidationIssue {

	@JsonProperty("issue_code")
	private String issueCode;
	
	@JsonProperty("issue_level")
	private ValidationIssueLevel issueLevel;
	
	@JsonProperty("issue_msg")
	private String issueMsg;

	public String getIssueCode() {
		return issueCode;
	}

	public void setIssueCode(String issueCode) {
		this.issueCode = issueCode;
	}

	public ValidationIssueLevel getIssueLevel() {
		return issueLevel;
	}

	public void setIssueLevel(ValidationIssueLevel issueLevel) {
		this.issueLevel = issueLevel;
	}

	public String getIssueMsg() {
		return issueMsg;
	}

	public void setIssueMsg(String issueMsg) {
		this.issueMsg = issueMsg;
	}
	
}
