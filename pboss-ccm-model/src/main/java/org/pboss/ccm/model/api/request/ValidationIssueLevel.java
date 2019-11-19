/**
 * 
 */
package org.pboss.ccm.model.api.request;

/**
 * Each validation issue has a level. The level will determine if the whole request
 * will be rejected or processed.
 * 
 * @author gregf
 *
 */
public enum ValidationIssueLevel {
	INFO,
	WARNING,
	ERROR;
}
