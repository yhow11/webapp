package service.keymanagement.impl;

import java.util.List;

import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.keymanagement.KeyManagementService;
import service.keymanagement.model.KeyModel;

public class KeyManagementServiceImpl implements KeyManagementService {

	private PhoenixDaoImpl phoenixDaoImpl;
	
	public KeyManagementServiceImpl(PhoenixDaoImpl phoenixDaoImpl) {
		// TODO Auto-generated constructor stub
		this.phoenixDaoImpl = phoenixDaoImpl;
	}

	@Override
	public KeyModel save(KeyModel keyModel) throws Exception {
		// TODO Auto-generated method stub
		keyModel = phoenixDaoImpl.upsert(keyModel);
		return keyModel;
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		phoenixDaoImpl.createTable(KeyModel.class);
	}

	@Override
	public List<KeyModel> getAll(Long offset, Long limit) throws Exception {
		// TODO Auto-generated method stub
		KeyModel paramModel = new KeyModel();
		paramModel.settKey("%%");
		paramModel.setOffset(offset);
		paramModel.setLimit(limit);
		return phoenixDaoImpl.search(KeyModel.class, paramModel);
	}

	@Override
	public KeyModel get(String key) throws Exception {
		// TODO Auto-generated method stub
		KeyModel paramModel = new KeyModel();
		paramModel.settKey(key);
		List<KeyModel> results = phoenixDaoImpl.search(KeyModel.class, paramModel);
		return results.size() > 0? results.get(0):null;
	}

	@Override
	public void delete(String key) throws Exception {
		// TODO Auto-generated method stub
		KeyModel paramModel = new KeyModel();
		paramModel.settKey("%"+key);
		List<KeyModel> results = phoenixDaoImpl.search(KeyModel.class, paramModel);
		KeyModel keyModel = results.get(0);
		phoenixDaoImpl.delete(keyModel);
	}

	@Override
	public List<KeyModel> getAll(String key) throws Exception {
		// TODO Auto-generated method stub
		KeyModel paramModel = new KeyModel();
		paramModel.settKey("%"+key);
		List<KeyModel> keyModels = phoenixDaoImpl.search(KeyModel.class, paramModel);
		return keyModels;
	}

}
