package usertracker.browser.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import common.query.QueryParam;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.model.SessionModel;
import usertracker.browser.service.SessionService;

public class SessionPhoenixServiceImpl extends PhoenixDaoImpl implements SessionService {
	

	public SessionPhoenixServiceImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
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
	public List<SessionModel> getAll(QueryParam<SessionModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public Long getCount(QueryParam<SessionModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.count(param);
	}

	@Override
	public void remove(SessionModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}


	@Override
	public SessionModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<SessionModel> param = new QueryParam<SessionModel>(SessionModel.class);
		param.getModel().setId("%"+id);
		return super.searchOne(param);
	}


	@Override
	public SessionModel save(SessionModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}


	@Override
	public List<SessionModel> getAll(String avID) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<SessionModel> param = new QueryParam<SessionModel>(SessionModel.class);
		param.getModel().setAnonymousVisitorID(avID);
		return super.search(param);
	}

	
	
}
