package service.keymanagement.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import common.query.QueryParam;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.keymanagement.KeyManagementService;
import service.keymanagement.model.KeyModel;

public class KeyManagementPhoenixServiceImpl extends PhoenixDaoImpl implements KeyManagementService {


	public KeyManagementPhoenixServiceImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public KeyModel save(KeyModel keyModel) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(keyModel);
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		super.createTable(KeyModel.class);
	}


	@Override
	public KeyModel get(String key) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<KeyModel> param = new QueryParam<KeyModel>(KeyModel.class);
		param.getModel().settKey(key);
		param.setLimit(1L);
		return super.searchOne(param);
	}

	@Override
	public List<KeyModel> getAll(QueryParam<KeyModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public Long getCount(QueryParam<KeyModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.count(param);
	}

	@Override
	public void remove(KeyModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}

	@Override
	public List<KeyModel> getAll(String key, Long offset, Long limit) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<KeyModel> param = new QueryParam<KeyModel>(KeyModel.class);
		param.setLimit(limit);
		param.setOffset(offset);
		param.getModel().settKey("%"+key+"%");
		return super.search(param);
	}

	@Override
	public List<KeyModel> getAll(String key) throws Exception {
		// TODO Auto-generated method stub
		return getAll(key, null, null);
	}

	@Override
	public void remove(String key) throws Exception {
		// TODO Auto-generated method stub
		remove(get(key));
	}

}
