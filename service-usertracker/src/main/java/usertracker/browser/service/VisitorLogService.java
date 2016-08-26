package usertracker.browser.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import usertracker.browser.model.VisitorLogModel;

@Transactional
public interface VisitorLogService {
	
	public VisitorLogModel save(VisitorLogModel model)  throws Exception; 
	public void delete(Long id)  throws Exception; 
	public VisitorLogModel get(Long id)  throws Exception; 
	public List<VisitorLogModel> getAll(Long start, Long end)  throws Exception; 
	public List<VisitorLogModel> getAll(Long start, Long end, boolean desc)  throws Exception;
}
