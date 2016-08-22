package usertracker.browser.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import usertracker.browser.model.BrowserFPModel;

@Transactional
public interface BrowserFPService {
	
	public BrowserFPModel save(BrowserFPModel model) throws Exception;

	public void delete(String id) throws Exception;

	public BrowserFPModel get(String id) throws Exception;
	
	public BrowserFPModel getOrCreate(String id, String av) throws Exception;
	
	public List<BrowserFPModel> getAll(String avID) throws Exception;
}
