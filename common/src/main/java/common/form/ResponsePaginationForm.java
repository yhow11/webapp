package common.form;

import common.form.ResponseForm;

public class ResponsePaginationForm<T> extends ResponseForm<T> {

	private Long count;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	 
	
}