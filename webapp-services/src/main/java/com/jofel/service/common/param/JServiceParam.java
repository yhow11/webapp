package com.jofel.service.common.param;

public class JServiceParam {
	
	public enum OrderType {
		ASC,
		DESC
	}

	private OrderType orderType = OrderType.ASC;
	private int limit;
	public OrderType getOrderType() {
		return orderType;
	}
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
}
