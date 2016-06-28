package usertracker.browser.service;

import java.util.List;

import usertracker.browser.model.VisitorLogModel;

public interface VisitorLogService {
	public List<VisitorLogModel> getAll(Integer limit)  throws Exception;
	public List<VisitorLogModel> getAll()  throws Exception;
	public List<VisitorLogModel> getAll(Integer limit, String startRow, String lastRow)  throws Exception;
	public List<VisitorLogModel> find(String word, String column) throws Exception;
}
