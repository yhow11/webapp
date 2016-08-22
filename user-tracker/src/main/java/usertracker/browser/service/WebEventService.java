package usertracker.browser.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import usertracker.browser.model.WebEventModel;

@Transactional
public interface WebEventService {
	public WebEventModel save(WebEventModel model)  throws Exception; 
	public void delete(String id)  throws Exception; 
	public WebEventModel get(String id)  throws Exception; 
	public List<WebEventModel> getAll(String avID, Long offset, Long limit) throws Exception;
	public List<WebEventModel> getAllDistinctByURL(String url, Long offset, Long limit) throws Exception;
	public Long countDistincyByURL(String url) throws Exception;
	public Long count(String url) throws Exception;
}
