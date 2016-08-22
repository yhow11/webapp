package usertracker.browser.service;

import org.springframework.transaction.annotation.Transactional;

import usertracker.browser.model.AnonymousVisitorModel;

@Transactional
public interface AnonymousVisitorService {
	
	public AnonymousVisitorModel save(AnonymousVisitorModel model) throws Exception;

	public void delete(String id) throws Exception;

	public AnonymousVisitorModel get(String id) throws Exception;
	
}
