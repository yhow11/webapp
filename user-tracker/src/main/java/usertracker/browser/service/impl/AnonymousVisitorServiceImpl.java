package usertracker.browser.service.impl;

import java.util.List;
import java.util.UUID;

import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.model.AnonymousVisitorModel;
import usertracker.browser.model.BrowserFPModel;
import usertracker.browser.model.SessionModel;
import usertracker.browser.service.AnonymousVisitorService;
import usertracker.browser.service.BrowserFPService;
import usertracker.browser.service.SessionService;

public class AnonymousVisitorServiceImpl implements AnonymousVisitorService {
	
	private PhoenixDaoImpl phoenixDaoImpl;
	
	public AnonymousVisitorServiceImpl(PhoenixDaoImpl phoenixDaoImpl) {
		// TODO Auto-generated constructor stub
		this.phoenixDaoImpl = phoenixDaoImpl;
	}

	@Override
	public AnonymousVisitorModel save(AnonymousVisitorModel model) throws Exception {
		// TODO Auto-generated method stub
		return phoenixDaoImpl.upsert(model);
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		phoenixDaoImpl.delete(get(id));
	}

	@Override
	public AnonymousVisitorModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		AnonymousVisitorModel paramModel = new AnonymousVisitorModel();
		paramModel.setId(id);
		List<AnonymousVisitorModel> av = phoenixDaoImpl.search(AnonymousVisitorModel.class, paramModel);
		return av.get(0);
	}

	
}
