package common.form;

import java.util.ArrayList;
import java.util.List;

public class ResponseForm<T> {

	public static final String NO_DATA = "No data found.";
	private boolean status = true;
	private String message = "SUCCESS";
	protected List<T> data = new ArrayList<T>();
	
	public ResponseForm() {
		// TODO Auto-generated constructor stub
	}
	public ResponseForm(boolean status, String message) {
		// TODO Auto-generated constructor stub
		this.status  = status;
		this.message = message;
	}
	public ResponseForm(List<T> data) {
		// TODO Auto-generated constructor stub
		this.data = data;
	}
	public ResponseForm(T data) {
		// TODO Auto-generated constructor stub
		this.data = new ArrayList<T>();
		this.data.add(data);
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	
	
}
