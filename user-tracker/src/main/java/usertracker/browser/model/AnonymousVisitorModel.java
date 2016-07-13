package usertracker.browser.model;

import java.io.Serializable;

import helper.phoenix.annotation.PhoenixFieldAnnotation;
import helper.phoenix.annotation.PhoenixTableAnnotation;

@PhoenixTableAnnotation(table="anonymousVisitor")
public class AnonymousVisitorModel  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PhoenixFieldAnnotation(type="VARCHAR(255)", primary=true)
	private String id;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
