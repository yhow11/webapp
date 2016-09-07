package common.query.form;

import java.util.ArrayList;
import java.util.List;

public class FormResponse<T> {

	private String totalCount;
	private List<T> data = new ArrayList<T>();
	private boolean status;
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}