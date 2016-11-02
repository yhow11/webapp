package common.orm.aggregator;

import common.orm.query.param.Param;

public interface Count<T> {
	public Long count(Param<T> param) throws Exception;
}
