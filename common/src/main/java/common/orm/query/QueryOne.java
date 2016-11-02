package common.orm.query;

import java.util.Optional;

import common.orm.query.param.Param;

public interface QueryOne<T> {
	public Optional<T> get(Param<T> param) throws Exception;
}
