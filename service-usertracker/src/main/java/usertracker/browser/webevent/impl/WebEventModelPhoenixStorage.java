package usertracker.browser.webevent.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.webevent.model.WebEventModel;

@Service("WebEventModelPhoenixStorage")
@Transactional
public class WebEventModelPhoenixStorage extends PhoenixDaoImpl implements Storage<WebEventModel> {
	
	@Autowired
	public WebEventModelPhoenixStorage(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public List<WebEventModel> get(Param<WebEventModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
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
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		super.createTable(WebEventModel.class);
	}

	
}
