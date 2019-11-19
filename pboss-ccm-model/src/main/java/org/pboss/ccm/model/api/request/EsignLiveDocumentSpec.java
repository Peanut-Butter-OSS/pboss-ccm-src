/**
 * 
 */
package org.pboss.ccm.model.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Defines a document to be signed using eSignLive
 * 
 * @author gregf
 *
 */
@JsonTypeName("esignlive_document_spec")
@JsonInclude(Include.NON_NULL)
public class EsignLiveDocumentSpec {

	@JsonProperty("cic_part_id")
	private String cicPartId;
	
	@JsonProperty("field_height")
	private int fieldHeight;
	
	@JsonProperty("field_width")
	private int fieldWidth;
	
	@JsonProperty("field_top")
	private int fieldTop;
	
	@JsonProperty("field_left")
	private int fieldLeft;

	public String getCicPartId() {
		return cicPartId;
	}

	public void setCicPartId(String cicPartId) {
		this.cicPartId = cicPartId;
	}

	public int getFieldHeight() {
		return fieldHeight;
	}

	public void setFieldHeight(int fieldHeight) {
		this.fieldHeight = fieldHeight;
	}

	public int getFieldWidth() {
		return fieldWidth;
	}

	public void setFieldWidth(int fieldWidth) {
		this.fieldWidth = fieldWidth;
	}

	public int getFieldTop() {
		return fieldTop;
	}

	public void setFieldTop(int fieldTop) {
		this.fieldTop = fieldTop;
	}

	public int getFieldLeft() {
		return fieldLeft;
	}

	public void setFieldLeft(int fieldLeft) {
		this.fieldLeft = fieldLeft;
	}

	@Override
	public String toString() {
		return "EsignLiveDocumentSpec [cicPartId=" + cicPartId + ", fieldHeight=" + fieldHeight + ", fieldWidth="
				+ fieldWidth + ", fieldTop=" + fieldTop + ", fieldLeft=" + fieldLeft + "]";
	}

}
