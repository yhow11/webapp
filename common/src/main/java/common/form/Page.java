package common.form;

import java.util.List;

import common.form.ResponseForm;

public class Page<T> extends ResponseForm<T> {

	private Long count;

	public Page() {
		// TODO Auto-generated constructor stub
	}
	public Page(List<T> data, Long count) {
		// TODO Auto-generated constructor stub
		this.data = data;
		this.count = count;
	}
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	 
	
}
