package usertracker.browser.service;

import java.util.List;

import usertracker.browser.model.WebEventModel;

public interface VisitorLogService {
	public <T> List<T> getAll(Class<T> clazz, String column, String start, String last) throws Exception;

	public <T> T getOne(Class<T> clazz, String id) throws Exception;

	public <T> List<T> find(Class<T> clazz, String column, String word) throws Exception;

	public String getAV(String session, String browserFP) throws Exception;

	public List<WebEventModel> findWebEvents(String av,  String start, String last, String orderby) throws Exception;

	public <T> List<T> getAll(Class<T> clazz, String column,  String start, String last, String orderby) throws Exception;
	
	public void creatTable(Class<?> clazz) throws Exception;

	public void creatTable(Class<?>... clazzes) throws Exception;
	
	public <T> T save(Class<T> clazz, Object object) throws Exception;
	
	public List<String> findColumnValues(Class<?> clazz, String columnReturn, String column, String word) throws Exception;
}
