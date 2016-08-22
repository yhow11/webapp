package usertracker.browser.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import usertracker.browser.model.SessionModel;

@Transactional
public interface SessionService {
	
	public SessionModel save(SessionModel model) throws Exception;

	public void delete(String id) throws Exception;

	public SessionModel get(String id) throws Exception;
	
	public SessionModel getOrCreate(String sessionID, String av) throws Exception;
	
	public List<SessionModel> getAll(String avID) throws Exception;
}
