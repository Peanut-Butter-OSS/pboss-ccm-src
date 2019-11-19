/**
 * 
 */
package org.pboss.ccm.model.api.request;

/**
 * @author gregf
 *
 */
public enum CicStatus {
	RECEIVED,
	SENT_FOR_CREATION,
	CREATION_TIMEOUT,
	CREATED,
	SENT_FOR_DELIVERY,
	DELIVERY_TIMEOUT,
	DELIVERED,
	COMPLETE,
	FAILURE,
	SYSTEM_ERROR;
}
