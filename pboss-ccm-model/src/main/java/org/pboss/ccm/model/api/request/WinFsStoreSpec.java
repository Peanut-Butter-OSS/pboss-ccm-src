/**
 * 
 */
package org.pboss.ccm.model.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author gregf
 *
 */
@JsonTypeName("win_fs_store_spec")
public class WinFsStoreSpec extends StoreSpec {
	
	@JsonProperty("file_path")
	String filePath;
	
	@JsonProperty("make_unique")
	boolean makeUnique;
	
	@JsonProperty("overwrite")
	boolean overwrite;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean isMakeUnique() {
		return makeUnique;
	}

	public void setMakeUnique(boolean makeUnique) {
		this.makeUnique = makeUnique;
	}

	public boolean isOverwrite() {
		return overwrite;
	}

	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}

}
