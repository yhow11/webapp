package service.keymanagement.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.keymanagement.model.KeyModel;

@Service("KeyModelStorage")
@Transactional
public class KeyModelStorage extends PhoenixDaoImpl implements Storage<KeyModel> {

	@Autowired
	public KeyModelStorage(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public List<KeyModel> get(Param<KeyModel> param) throws Exception {
		// TO0..DO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public KeyModel save(KeyModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}

	@Override
	public void remove(KeyModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		super.createTable(KeyModel.class);
	}

}
