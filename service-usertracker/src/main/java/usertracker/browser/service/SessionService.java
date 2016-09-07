package usertracker.browser.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import common.service.JService;
import usertracker.browser.model.SessionModel;

@Transactional
public interface SessionService  extends JService<SessionModel, SessionModel>{
	public List<SessionModel> getAll(String avID) throws Exception;
	public SessionModel getOrCreate(String sessionID, String av) throws Exception;
}
