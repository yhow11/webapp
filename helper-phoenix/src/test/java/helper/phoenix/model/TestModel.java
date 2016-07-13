package helper.phoenix.model;

import helper.phoenix.annotation.PhoenixFieldAnnotation;
import helper.phoenix.annotation.PhoenixTableAnnotation;

@PhoenixTableAnnotation(table="testTable")
public class TestModel {

	@PhoenixFieldAnnotation(type="VARCHAR(100)", primary=true)
	private String id;
	@PhoenixFieldAnnotation(type="INTEGER")
	private int count;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
