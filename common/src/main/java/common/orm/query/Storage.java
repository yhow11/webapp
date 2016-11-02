package common.orm.query;

import java.util.List;

import common.orm.query.param.Param;

public interface Storage<T> {
	public List<T> get(Param<T> param) throws Exception;
	default T getOrCreate(Param<T> param) throws Exception{
		List<T> result = get(param);
		if(result.size() > 0){
			return result.get(0);
		} else {
			return save(param.getModel());
		}
	}
	public T save(T model) throws Exception;
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
	
	public void remove(T model) throws Exception;
	public void create() throws Exception;
}
