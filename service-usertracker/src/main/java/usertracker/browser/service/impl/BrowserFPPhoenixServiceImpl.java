package usertracker.browser.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import common.query.QueryParam;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.model.BrowserFPModel;
import usertracker.browser.service.BrowserFPService;

public class BrowserFPPhoenixServiceImpl extends PhoenixDaoImpl implements BrowserFPService {
	
	public BrowserFPPhoenixServiceImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}
	@Override
	public BrowserFPModel getOrCreate(String id, String av) throws Exception {
		// TODO Auto-generated method stub
		BrowserFPModel browserFP = get(id);
		if (browserFP == null) {
			browserFP = new BrowserFPModel();
			browserFP.setAnonymousVisitorID(av);
			browserFP.setId(id);
			save(browserFP);
		} 
		return browserFP;
	}
	@Override
	public List<BrowserFPModel> getAll(QueryParam<BrowserFPModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}
	@Override
	public Long getCount(QueryParam<BrowserFPModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.count(param);
	}
	@Override
	public BrowserFPModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<BrowserFPModel> param = new QueryParam<BrowserFPModel>(BrowserFPModel.class);
		param.getModel().setId(id);
		return super.searchOne(param);
	}
	@Override
	public BrowserFPModel save(BrowserFPModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}
	@Override
	public void remove(BrowserFPModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}
	@Override
	public List<BrowserFPModel> getAll(String avID) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<BrowserFPModel> param = new QueryParam<BrowserFPModel>(BrowserFPModel.class);
		param.getModel().setAnonymousVisitorID(avID);
		return super.search(param);
	}
}
