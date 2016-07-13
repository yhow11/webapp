package usertracker.browser.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import usertracker.browser.model.WebEventModel;

@Transactional
public interface VisitorLogDao {

	public <T> List<T> getAll (Class<T> clazz, String column, String start, String last)  throws Exception ;
	
	public <T> T getOne (Class<T> clazz, String id)  throws Exception ;   
	
	public <T> List<T> find(Class<T> clazz, String column, String word) throws Exception;
	
	public List<WebEventModel> findWebEvents(String av,  String start, String last, String orderby) throws Exception;
	
	public List<String> findColumnValues(Class<?> clazz, String columnReturn, String column, String word) throws Exception;
	
	public <T> List<T> getAll(Class<T> clazz, String column,  String start, String last, String orderby) throws Exception;
	
	public void creatTable(Class<?> clazz) throws Exception;
	
	public <T> T save(Class<T> clazz, Object object )  throws Exception; 
	
	
}
