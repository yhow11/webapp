package usertracker.browser.model;

import java.io.Serializable;

import hbase.annotation.HBaseColumnAnnotation;
import hbase.annotation.HBaseTableAnnotation;

@HBaseTableAnnotation(tablename="anonymousVisitor")
public class AnonymousVisitorModel  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@HBaseColumnAnnotation(name="id", family="metadata")
	private String id;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
