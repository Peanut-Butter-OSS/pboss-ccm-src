/**
 * 
 */
package org.pboss.ccm.model.delivery;

/**
 * This class collects all the details needed to properly attach a file to an
 * esign package
 * 
 * @author gregf
 *
 */
public class EsignDocumentDetails {
	
	private String mimeType;
	private String fileName;
	
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	

}
