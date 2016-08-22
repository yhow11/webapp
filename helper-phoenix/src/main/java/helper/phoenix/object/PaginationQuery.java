package helper.phoenix.object;

import helper.phoenix.annotation.query.PhoenixPaginated;

@PhoenixPaginated
public abstract class PaginationQuery {

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
	
}
