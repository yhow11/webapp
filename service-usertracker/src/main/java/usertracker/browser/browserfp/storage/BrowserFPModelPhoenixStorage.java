package usertracker.browser.browserfp.storage;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.browserfp.model.BrowserFPModel;

@Service("BrowserFPModelPhoenixStorage")
@Transactional
public class BrowserFPModelPhoenixStorage extends PhoenixDaoImpl implements Storage<BrowserFPModel> {
	
	@Autowired
	public BrowserFPModelPhoenixStorage(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<BrowserFPModel> get(Param<BrowserFPModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
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
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		super.createTable(BrowserFPModel.class);
	}

	
	
}
