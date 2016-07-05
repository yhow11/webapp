package usertracker.browser.dao;

import java.util.List;

import usertracker.browser.model.VisitorLogModel;

public interface VisitorLogDao {

	public <T> List<T> getAll (Class<T> clazz, Integer limit, String startRow, String lastRow)  throws Exception ;
	
	public <T> T getOne (Class<T> clazz, String id)  throws Exception ;   
	
	public <T> List<T> find(Class<T> clazz, String word, String column) throws Exception;
	
	public void creatTable(String tableName) throws Exception;
	
	public <T> T save(Class<T> clazz, Object object )  throws Exception; 
}
