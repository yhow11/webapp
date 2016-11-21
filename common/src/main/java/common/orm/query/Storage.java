package common.orm.query;

import java.util.List;

import common.LogMetaData;
import common.Loggable;
import common.orm.query.param.Param;

public interface Storage<T> extends Get<T>, Remove<T>, Save<T>, Table<T>{
	
	@Loggable
	default T getOrCreate(Param<T> param, LogMetaData lmd) throws Exception{
		List<T> result = get(param);
		if(result.size() > 0){
			lmd.getTransactions().add("Found "+param.getModelClass().getSimpleName());
			return result.get(0);
		} else {
			lmd.getTransactions().add("Create New "+param.getModelClass().getSimpleName());
			return save(param.getModel());
		}
	}
	default List<T> save(List<T> models) throws Exception{
		models.forEach(model -> {
			try {
				save(model);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return models;
	}
	
}
