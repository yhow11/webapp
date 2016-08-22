package usertracker.browser.service.impl;

import java.util.List;

import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.model.SessionModel;
import usertracker.browser.service.SessionService;

public class SessionServiceImpl implements SessionService {
	
	private PhoenixDaoImpl phoenixDaoImpl;
	
	public SessionServiceImpl(PhoenixDaoImpl phoenixDaoImpl) {
		// TODO Auto-generated constructor stub
		this.phoenixDaoImpl = phoenixDaoImpl;
	}

	@Override
	public SessionModel save(SessionModel model) throws Exception {
		// TODO Auto-generated method stub
		return phoenixDaoImpl.upsert(model);
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		phoenixDaoImpl.delete(get(id));
	}

	@Override
	public SessionModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		SessionModel paramModel = new SessionModel();
		paramModel.setId(id);
		List<SessionModel> session = phoenixDaoImpl.search(SessionModel.class, paramModel);
		return session.get(0);
	}

	@Override
	public SessionModel getOrCreate(String sessionID, String av) throws Exception {
		// TODO Auto-generated method stub
		SessionModel sessionModel = get(sessionID);
		if (sessionModel == null) {
			sessionModel = new SessionModel();
			sessionModel.setId(sessionID);
			sessionModel.setAnonymousVisitorID(av);
			save(sessionModel);
			System.out.println("Created New Session " + sessionModel.getId());
		} else {
			System.out.println("Session Found. " + sessionModel.getId());
		}
		return sessionModel;
	}

	@Override
	public List<SessionModel> getAll(String avID) throws Exception {
		// TODO Auto-generated method stub
		SessionModel paramModel = new SessionModel();
		paramModel.setAnonymousVisitorID(avID);
		return phoenixDaoImpl.search(SessionModel.class, paramModel);
	}

	
	
}
