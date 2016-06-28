package usertracker.browser.dao;

import java.util.List;

import usertracker.browser.model.VisitorLogModel;

public interface VisitorLogDao {

	public List<VisitorLogModel> getAll (Class<VisitorLogModel> clazz, Integer limit, String startRow, String lastRow)  throws Exception ;
	   
	public List<VisitorLogModel> find(String word, String column) throws Exception;
	
	public void creatTable() throws Exception;
	
	public void save(VisitorLogModel model )  throws Exception; 
}
