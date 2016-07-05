package usertracker.browser.service;

import java.util.List;

public interface VisitorLogService {
	public <T> List<T> getAll(Class<T> clazz, Integer limit)  throws Exception;
	public <T> List<T> getAll(Class<T> clazz)  throws Exception;
	public <T> List<T> getAll(Class<T> clazz, Integer limit, String startRow, String lastRow)  throws Exception;
	public <T> List<T> find(Class<T> clazz, String word, String column) throws Exception;
	public <T> T creatTable(Class<T> clazz) throws Exception;
	public <T> T save(Class<T> clazz, Object object) throws Exception;
	public <T> T getOne (Class<T> clazz, String id)  throws Exception ;   
}
