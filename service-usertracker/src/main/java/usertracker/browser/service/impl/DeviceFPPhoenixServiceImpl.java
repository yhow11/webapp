package usertracker.browser.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import common.query.QueryParam;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.model.DeviceFPModel;
import usertracker.browser.service.DeviceFPService;

public class DeviceFPPhoenixServiceImpl extends PhoenixDaoImpl implements DeviceFPService {
	
	public DeviceFPPhoenixServiceImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public DeviceFPModel getOrCreate(String id, String av) throws Exception {
		// TODO Auto-generated method stub
		DeviceFPModel deviceFP = get(id);
		if (deviceFP == null) {
			deviceFP = new DeviceFPModel();
			deviceFP.setAnonymousVisitorID(av);
			deviceFP.setId(id);
			save(deviceFP);
			System.out.println("Created Device FP " + deviceFP.getId());
		} else {
			System.out.println("Device FP Found. " + deviceFP.getId());
		}
		return deviceFP;
	}

	@Override
	public List<DeviceFPModel> getAll(QueryParam<DeviceFPModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public Long getCount(QueryParam<DeviceFPModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.count(param);
	}

	@Override
	public DeviceFPModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<DeviceFPModel> param = new QueryParam<DeviceFPModel>(DeviceFPModel.class);
		param.getModel().setId(id);
		return super.searchOne(param);
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
	public List<DeviceFPModel> getAll(String avID) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<DeviceFPModel> param = new QueryParam<DeviceFPModel>(DeviceFPModel.class);
		param.getModel().setId(avID);
		return super.search(param);
	}

	
}
