package helper.spark;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Dataset;

import common.orm.query.param.Param;

public interface DataSet<T> {
	public Dataset<T> get(Param<T> param) throws Exception;
	public DataFrame getDF(Param<T> param) throws Exception;
}
