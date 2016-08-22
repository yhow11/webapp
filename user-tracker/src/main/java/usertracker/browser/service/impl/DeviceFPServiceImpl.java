package usertracker.browser.service.impl;

import java.util.List;

import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.model.DeviceFPModel;
import usertracker.browser.service.DeviceFPService;

public class DeviceFPServiceImpl implements DeviceFPService {
	
	private PhoenixDaoImpl phoenixDaoImpl;
	
	public DeviceFPServiceImpl(PhoenixDaoImpl phoenixDaoImpl) {
		// TODO Auto-generated constructor stub
		this.phoenixDaoImpl = phoenixDaoImpl;
	}

	@Override
	public DeviceFPModel save(DeviceFPModel model) throws Exception {
		// TODO Auto-generated method stub
		return phoenixDaoImpl.upsert(model);
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		phoenixDaoImpl.delete(get(id));
	}

	@Override
	public DeviceFPModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		DeviceFPModel paramModel = new DeviceFPModel();
		paramModel.setId(id);
		List<DeviceFPModel> fp = phoenixDaoImpl.search(DeviceFPModel.class, paramModel);
		return fp.get(0);
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
	public List<DeviceFPModel> getAll(String avID) throws Exception {
		// TODO Auto-generated method stub
		DeviceFPModel paramModel = new DeviceFPModel();
		paramModel.setAnonymousVisitorID(avID);
		return phoenixDaoImpl.search(DeviceFPModel.class, paramModel);
	}

	
	
}
