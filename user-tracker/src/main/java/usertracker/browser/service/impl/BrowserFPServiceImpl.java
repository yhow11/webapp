package usertracker.browser.service.impl;

import java.util.List;

import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.model.BrowserFPModel;
import usertracker.browser.service.BrowserFPService;

public class BrowserFPServiceImpl implements BrowserFPService {
	
	private PhoenixDaoImpl phoenixDaoImpl;
	
	public BrowserFPServiceImpl(PhoenixDaoImpl phoenixDaoImpl) {
		// TODO Auto-generated constructor stub
		this.phoenixDaoImpl = phoenixDaoImpl;
	}

	@Override
	public BrowserFPModel save(BrowserFPModel model) throws Exception {
		// TODO Auto-generated method stub
		return phoenixDaoImpl.upsert(model);
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		phoenixDaoImpl.delete(get(id));
	}

	@Override
	public BrowserFPModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		BrowserFPModel paramModel = new BrowserFPModel();
		paramModel.setId(id);
		List<BrowserFPModel> fp = phoenixDaoImpl.search(BrowserFPModel.class, paramModel);
		return fp.get(0);
	}

	@Override
	public BrowserFPModel getOrCreate(String id, String av) throws Exception {
		// TODO Auto-generated method stub
		BrowserFPModel browserFP = get(id);
		if (browserFP == null) {
			browserFP = new BrowserFPModel();
			browserFP.setAnonymousVisitorID(av);
			browserFP.setId(id);
			save(browserFP);
			System.out.println("Created Browser FP " + browserFP.getId());
		} else {
			System.out.println("Browser FP Found. " + browserFP.getId());
		}
		return browserFP;
	}

	@Override
	public List<BrowserFPModel> getAll(String avID) throws Exception {
		// TODO Auto-generated method stub
		BrowserFPModel paramModel = new BrowserFPModel();
		paramModel.setAnonymousVisitorID(avID);
		return phoenixDaoImpl.search(BrowserFPModel.class, paramModel);
	}

	
	
}
