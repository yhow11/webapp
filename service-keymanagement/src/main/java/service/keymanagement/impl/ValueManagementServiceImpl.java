package service.keymanagement.impl;

import java.util.List;

import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.keymanagement.ValueManagementService;
import service.keymanagement.model.ValueModel;

public class ValueManagementServiceImpl implements ValueManagementService {

	private PhoenixDaoImpl phoenixDaoImpl;
	
	public ValueManagementServiceImpl(PhoenixDaoImpl phoenixDaoImpl) {
		// TODO Auto-generated constructor stub
		this.phoenixDaoImpl = phoenixDaoImpl;
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		phoenixDaoImpl.createTable(ValueModel.class);
	}

	@Override
	public ValueModel save(ValueModel valueModel) throws Exception {
		// TODO Auto-generated method stub
		return phoenixDaoImpl.upsert(valueModel);
	}

	@Override
	public List<ValueModel> getAll(String key) throws Exception {
		// TODO Auto-generated method stub
		ValueModel param = new ValueModel();
		param.settKey(key);
		return phoenixDaoImpl.search(ValueModel.class, param);
	}

	@Override
	public ValueModel get(Long id) throws Exception {
		// TODO Auto-generated method stub
		ValueModel param = new ValueModel();
		param.setId(id);
		List<ValueModel> results = phoenixDaoImpl.search(ValueModel.class, param);
		return results.size() > 0? results.get(0): null;
	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		phoenixDaoImpl.delete(get(id));
	}


}
