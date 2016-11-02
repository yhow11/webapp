package common.orm.query;

import common.form.Page;
import common.orm.aggregator.Count;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;

public interface Book<T> {
	public Storage<T> storage() throws Exception;
	public Count<T> aggregator() throws Exception;
	public Class<T> clazz();
	
	default Page<T> getPage(Param<T> param) throws Exception {
		// TODO Auto-generated method stub
		return new Page<>(storage().get(param), aggregator().count(param));
	}

	default Long count(T param) throws Exception {
		// TODO Auto-generated method stub
		return aggregator().count(new DefaultParam<>(clazz(), param));
	}
}
