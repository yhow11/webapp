package common.orm.query.param;

import java.util.List;

import common.ObjectUtil;
import common.orm.query.util.QueryUtil;

public class DefaultParam<T> extends Param<T>{

	public DefaultParam(Class<T> clazz) {
		super(clazz);
	}
	
	public DefaultParam(Class<T> clazz, T data) {
		super(clazz);
		this.model = data;
	}
	public DefaultParam(Class<T> clazz, T data, List<String> conditions) {
		super(clazz);
		this.model = data;
		this.conditions = conditions;
	}
	public DefaultParam(Class<T> clazz, Long offset, Long limit) {
		// TODO Auto-generated constructor stub
		super(clazz);
		this.offset = offset;
		this.limit = limit;
		
	}
	public DefaultParam(Class<T> clazz, String page, String limit) throws Exception {
		// TODO Auto-generated constructor stub
		super(clazz);
		ObjectUtil.isPresent(page, value -> this.offset = Long.valueOf(QueryUtil.getOffset(page, limit)));
		ObjectUtil.isPresent(limit, value -> this.limit = Long.valueOf(limit));
	}
}
