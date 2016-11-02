package common.orm.query.param;

import common.ObjectUtil;

public class DefaultParam<T> extends Param<T>{

	public DefaultParam(Class<T> clazz) {
		super(clazz);
	}
	
	public DefaultParam(Class<T> clazz, T data) {
		super(clazz);
		this.model = data;
	}
	
	public DefaultParam(Class<T> clazz, Long offset, Long limit) {
		// TODO Auto-generated constructor stub
		super(clazz);
		this.offset = offset;
		this.limit = limit;
		
	}
	public DefaultParam(Class<T> clazz, String offset, String limit) throws Exception {
		// TODO Auto-generated constructor stub
		super(clazz);
		ObjectUtil.isPresent(offset, value -> this.offset = Long.valueOf(value));
		ObjectUtil.isPresent(limit, value -> this.limit = Long.valueOf(value));
	}
}
