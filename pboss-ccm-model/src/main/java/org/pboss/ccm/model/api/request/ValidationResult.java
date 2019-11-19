/**
 * 
 */
package org.pboss.ccm.model.api.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This class contains the validation results after an API call has been validated
 * 
 * @author gregf
 *
 */
@JsonTypeName("validation_result")
@JsonInclude(Include.NON_NULL)
public class ValidationResult {

	@JsonProperty("valid")
	private boolean valid;
	
	@JsonProperty("info_issue_count")
	private int infoCount;
	
	@JsonProperty("warn_issue_count")
	private int warnCount;	
	
	@JsonProperty("error_issue_count")
	private int errorCount;
	
	@JsonProperty("validation_issue_list")
	private List<ValidationIssue> validationIssueList;

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public List<ValidationIssue> getValidationIssueList() {
		return validationIssueList;
	}

	public void setValidationIssueList(List<ValidationIssue> validationIssueList) {
		this.validationIssueList = validationIssueList;
	}

	public int getInfoCount() {
		return infoCount;
	}

	public void setInfoCount(int infoCount) {
		this.infoCount = infoCount;
	}

	public int getWarnCount() {
		return warnCount;
	}

	public void setWarnCount(int warnCount) {
		this.warnCount = warnCount;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	
}
