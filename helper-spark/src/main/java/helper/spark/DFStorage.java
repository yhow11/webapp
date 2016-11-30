package helper.spark;

import org.apache.spark.sql.DataFrame;

import common.orm.query.param.Param;

public interface DFStorage<T> {
	public DataFrame get(Param<T> param) throws Exception;
	public void save(DataFrame df) throws Exception;
}
