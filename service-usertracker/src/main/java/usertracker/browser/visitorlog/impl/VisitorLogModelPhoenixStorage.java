package usertracker.browser.visitorlog.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.visitorlog.model.VisitorLogModel;

@Service("VisitorLogModelPhoenixStorage")
@Transactional
public class VisitorLogModelPhoenixStorage extends PhoenixDaoImpl implements Storage<VisitorLogModel> {
	
	@Autowired
	public VisitorLogModelPhoenixStorage(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<VisitorLogModel> get(Param<VisitorLogModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public VisitorLogModel save(VisitorLogModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}

	@Override
	public void remove(VisitorLogModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}

	@Override
	public void create() throws Exception {
		// TODO Auto-generated method stub
		super.createTable(VisitorLogModel.class);
	}

	
	
}
