package usertracker.browser.model;

import java.io.Serializable;

import helper.phoenix.annotation.PhoenixFieldAnnotation;
import helper.phoenix.annotation.PhoenixTableAnnotation;

@PhoenixTableAnnotation(table="sessions")
public class SessionModel  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PhoenixFieldAnnotation(type="VARCHAR(255)" , primary=true)
	private String id;
	
	@PhoenixFieldAnnotation(type="VARCHAR(255)")
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
