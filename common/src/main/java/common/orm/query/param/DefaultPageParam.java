package common.orm.query.param;

import common.ObjectUtil;
import common.orm.query.util.QueryUtil;

public class DefaultPageParam<T> extends DefaultParam<T>{

	public DefaultPageParam(Class<T> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

	public DefaultPageParam(Class<T> clazz, String page, String limit) throws Exception {
		// TODO Auto-generated constructor stub
		super(clazz);
		ObjectUtil.isPresent(page, value -> this.offset = QueryUtil.getOffset(page, limit));
		ObjectUtil.isPresent(limit, value -> this.limit = Long.valueOf(value));
	}
}
