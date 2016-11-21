package common.orm.query;

import java.util.List;

import common.orm.query.param.Param;

public interface Get<T> {
	public List<T> get(Param<T> param) throws Exception;
}
