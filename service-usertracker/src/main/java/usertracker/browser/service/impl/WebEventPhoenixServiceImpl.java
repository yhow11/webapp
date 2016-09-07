package usertracker.browser.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import common.query.QueryParam;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.model.WebEventModel;
import usertracker.browser.service.WebEventService;

public class WebEventPhoenixServiceImpl extends PhoenixDaoImpl implements WebEventService {
	

	public WebEventPhoenixServiceImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<WebEventModel> getAll(QueryParam<WebEventModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public Long getCount(QueryParam<WebEventModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.count(param);
	}

	@Override
	public WebEventModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<WebEventModel> param = new QueryParam<WebEventModel>(WebEventModel.class);
		param.getModel().setId(id);
		return super.searchOne(param);
	}

	@Override
	public WebEventModel save(WebEventModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}

	@Override
	public void remove(WebEventModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}

	@Override
	public List<WebEventModel> getAll(String avID, Long offset, Long limit) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<WebEventModel> param = new QueryParam<WebEventModel>(WebEventModel.class);
		param.getModel().setAnonymousVisitorID(avID);
		param.setOffset(offset);
		param.setLimit(limit);
		return super.search(param);
	}

	
}
