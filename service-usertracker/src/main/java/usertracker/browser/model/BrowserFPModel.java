package usertracker.browser.model;

import java.io.Serializable;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixTable;

@PhoenixTable(table="browserFP")
public class BrowserFPModel  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(255)")
	private String id;
	
	@PhoenixColumn(type="VARCHAR(255)")
	private String anonymousVisitorID;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAnonymousVisitorID() {
		return anonymousVisitorID;
	}
	public void setAnonymousVisitorID(String anonymousVisitorID) {
		this.anonymousVisitorID = anonymousVisitorID;
	}
}
