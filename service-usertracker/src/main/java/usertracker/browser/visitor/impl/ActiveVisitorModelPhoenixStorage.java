package usertracker.browser.visitor.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.visitor.model.ActiveVisitorModel;

@Service("ActiveVisitorModelPhoenixStorage")
@Transactional
public class ActiveVisitorModelPhoenixStorage extends PhoenixDaoImpl implements Storage<ActiveVisitorModel> {
	
	
	@Autowired
	public ActiveVisitorModelPhoenixStorage(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ActiveVisitorModel> get(Param<ActiveVisitorModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public ActiveVisitorModel save(ActiveVisitorModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}

	@Override
	public void remove(ActiveVisitorModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}

	@Override
	public void create() throws Exception {
		// TODO Auto-generated method stub
		super.createTable(ActiveVisitorModel.class);
	}
	

}
