package usertracker.browser.model;

import java.io.Serializable;

import hbase.annotation.HBaseColumnAnnotation;
import hbase.annotation.HBaseTableAnnotation;

@HBaseTableAnnotation(tablename="browserFP")
public class BrowserFPModel  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@HBaseColumnAnnotation(name="id", family="metadata")
	private String id;
	

	@HBaseColumnAnnotation(name="anonymousVisitorID", family="metadata")
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
