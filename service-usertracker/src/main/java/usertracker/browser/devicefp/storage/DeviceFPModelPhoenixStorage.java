package usertracker.browser.devicefp.storage;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.devicefp.model.DeviceFPModel;

@Service("DeviceFPModelPhoenixStorage")
@Transactional
public class DeviceFPModelPhoenixStorage extends PhoenixDaoImpl implements Storage<DeviceFPModel> {
	
	@Autowired
	public DeviceFPModelPhoenixStorage(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<DeviceFPModel> get(Param<DeviceFPModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public DeviceFPModel save(DeviceFPModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}

	@Override
	public void remove(DeviceFPModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		super.createTable(DeviceFPModel.class);
	}

	
	
}
