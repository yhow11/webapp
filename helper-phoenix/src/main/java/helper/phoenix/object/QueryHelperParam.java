package helper.phoenix.object;

import helper.phoenix.annotation.query.PhoenixPaginated;

@PhoenixPaginated
public abstract class QueryHelperParam {

	public final static String ORDER_TYPE_DESC = "DESC";
	public final static String ORDER_TYPE_ASC = "ASC";
	
	private String orderBy;
	private String orderType = QueryHelperParam.ORDER_TYPE_ASC;
	private Long offset;
	private Long limit;
	
	public Long getOffset() {
		return offset;
	}
	public void setOffset(Long offset) {
		this.offset = offset;
	}
	public Long getLimit() {
		return limit;
	}
	public void setLimit(Long limit) {
		this.limit = limit;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
}
