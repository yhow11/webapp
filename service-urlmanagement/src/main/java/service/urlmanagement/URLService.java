package service.urlmanagement;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface URLService<T> {

	public T save(T object) throws Exception;
	public List<T> getAll(String url) throws Exception;
	public List<T> getAll(String url, Long offset, Long limit) throws Exception;
	public List<T> getAllDistinctURL(String url, Long offset, Long limit) throws Exception;
	public Long getCount(String url) throws Exception;
	public Long getCountDistinctURL(String url) throws Exception;
	public void delete(String url) throws Exception;
}
