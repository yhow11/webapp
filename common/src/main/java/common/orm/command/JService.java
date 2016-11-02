package common.orm.command;

import java.util.List;

import common.orm.query.param.Param;

public interface JService<T> {
	public List<T> getAll(Param<T> param) throws Exception;
	public Long count(Param<T> param) throws Exception;
	public T get(String id) throws Exception;
	public T save(T model) throws Exception;
	public void remove(T model) throws Exception;
}
