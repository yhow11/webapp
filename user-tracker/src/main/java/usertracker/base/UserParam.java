package usertracker.base;

import java.util.ArrayList;
import java.util.List;

public class UserParam<T> {

	private String type;
	private List<T> data  = new ArrayList<T>();
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	
}
