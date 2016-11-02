package common.orm.query.param;

import java.util.ArrayList;
import java.util.List;

import common.CommonInterface;
import common.orm.query.enums.QuerySortEnum;

public abstract class Param<T> implements CommonInterface<T>{

	protected List<String> conditions = new ArrayList<>();
	protected Long offset;
	protected Long limit;
	protected T model;
	protected QuerySortEnum sort = QuerySortEnum.ASC;
	protected String sortBy;
	protected Class<T> modelClass;
	protected Class<T> paramClass;
	
	public Param(Class<T> clazz) {
		// TODO Auto-generated constructor stub
		this.modelClass = clazz;
		this.paramClass = clazz;
		try {
			this.model = modelClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public Param(T model, Long offset, Long limit) throws InstantiationException, IllegalAccessException {
//		this.model = model;
//		this.offset = offset;
//		this.limit = limit;
//	}
//
//	public Param(Long offset, Long limit) throws InstantiationException, IllegalAccessException {
//		this(null, offset, limit);
//	}
//
//	public Param(T model, String page, String limit)
//			throws Exception, InstantiationException, IllegalAccessException {
//		ObjectUtil.isPresent(page, value -> this.offset = QueryUtil.getOffset(page, limit));
//		ObjectUtil.isPresent(limit, value -> this.limit = Long.valueOf(value));
//		this.model = model;
//	}
//
//	public Param(String page, String limit) throws Exception, InstantiationException, IllegalAccessException {
//		this(null, page, limit);
//	}
	public List<String> getConditions() {
		return conditions;
	}
	public void setConditions(List<String> conditions) {
		this.conditions = conditions;
	}
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
	public T getModel() {
		return model;
	}
	public void setModel(T model) {
		this.model = model;
	}
	public QuerySortEnum getSort() {
		return sort;
	}
	public void setSort(QuerySortEnum sort) {
		this.sort = sort;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public Class<T> getModelClass() {
		return modelClass;
	}
	public Class<T> getParamClass() {
		return paramClass;
	}
	
}
