package usertracker.browser.visitor.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.visitor.model.VisitorModel;

@Service("AnonymousVisitorModelPhoenixStorage")
@Transactional
public class AnonymousVisitorModelPhoenixStorage extends PhoenixDaoImpl implements Storage<VisitorModel> {
	
	@Autowired
	public AnonymousVisitorModelPhoenixStorage(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public List<VisitorModel> get(Param<VisitorModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public VisitorModel save(VisitorModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}

	@Override
	public void remove(VisitorModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		super.createTable(VisitorModel.class);
	}

	

}
