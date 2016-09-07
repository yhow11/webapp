package common.query;

public class QueryParam<T> {

	public final static String SORT_DESC = "desc";
	public final static String SORT_ASC = "asc";
	private Long limit;
	private Long offset;
	private String sort = QueryParam.SORT_ASC;
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

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Class<T> getModelClass() {
		return modelClass;
	}

}
