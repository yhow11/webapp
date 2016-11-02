package common.orm.aggregator;

import common.orm.query.param.Param;

public interface Sum<T> {
	public Long sum(Param<T> param) throws Exception;
}
