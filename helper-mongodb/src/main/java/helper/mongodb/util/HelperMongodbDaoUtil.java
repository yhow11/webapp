package helper.mongodb.util;

import java.util.List;
import java.util.Map;

import helper.mongodb.base.HelperMongodbDao;

public class HelperMongodbDaoUtil {

	public static <T> List<T> findAll(Map<String, String> parameters, HelperMongodbDao<T> dao) throws Exception{
		return dao.findAll(parameters);
	}
}
