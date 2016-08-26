package service.urlmanagement.impl;

import java.util.List;

import service.urlmanagement.URLService;

public abstract class URLServiceImpl<T> implements URLService<T> {

	@Override
	public T save(T object) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getAll(String url) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getAllDistinctURL(String url, Long offset, Long limit) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String url) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getCountDistinctURL(String url) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getAll(String url, Long offset, Long limit) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getCount(String url) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
