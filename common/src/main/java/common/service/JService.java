package common.service;

import java.util.List;

import common.query.QueryParam;

public interface JService<T, E> {
	public List<T> getAll(QueryParam<E> param) throws Exception;
	public Long getCount(QueryParam<E> param) throws Exception;
	public T get(String id) throws Exception;
	public T save(E model) throws Exception;
	public void remove(E model) throws Exception;
}
