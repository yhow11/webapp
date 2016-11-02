package common.orm.validate;

import common.orm.query.param.Param;

public interface CheckExist<T> {
	public boolean test(Param<T> param) throws Exception;
}
