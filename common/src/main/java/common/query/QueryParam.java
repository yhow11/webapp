package common.query;

import common.query.enums.QuerySortEnum;

public class QueryParam<T> {

	private Long limit;
	private Long offset;
	private QuerySortEnum sort = QuerySortEnum.ASC;
	private String sortBy;
	private Class<T> modelClass;
	private T model;

	private Class<?> paramClass;

	public QueryParam(Class<T> modelClass, Class<?> paramClass) throws InstantiationException, IllegalAccessException {
		this.paramClass = paramClass;
		this.modelClass = modelClass;
		model =  modelClass.newInstance();
	}

	public QueryParam(Class<T> paramClass) throws InstantiationException, IllegalAccessException {
		this.paramClass = paramClass;
		this.modelClass = paramClass;
		model =  paramClass.newInstance();
	}
	
	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	public Long getOffset() {
		return offset;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public Class<?> getParamClass() {
		return this.paramClass;
	}

	public QuerySortEnum getSort() {
		return sort;
	}

	public void setSort(QuerySortEnum sort) {
		this.sort = sort;
	}

	public Class<T> getModelClass() {
		return modelClass;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

}
