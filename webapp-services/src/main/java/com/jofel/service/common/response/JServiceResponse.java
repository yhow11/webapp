package com.jofel.service.common.response;

import java.util.ArrayList;
import java.util.List;

public abstract class JServiceResponse<T> {

	public enum ResponseType {
		SUCCESS,
		FAILED,
		ERROR
	}
	
	private ResponseType status = ResponseType.SUCCESS;
	private List<T> dataList = new ArrayList<T>();
	private T data;
	
	public ResponseType getStatus() {
		return status;
	}
	public void setStatus(ResponseType status) {
		this.status = status;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
	
}
