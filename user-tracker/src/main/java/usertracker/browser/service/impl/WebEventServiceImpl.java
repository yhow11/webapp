package usertracker.browser.service.impl;

import java.util.List;

import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.model.WebEventGroupByURLModel;
import usertracker.browser.model.WebEventModel;
import usertracker.browser.service.WebEventService;

public class WebEventServiceImpl implements WebEventService {
	
	private PhoenixDaoImpl phoenixDaoImpl;
	
	public WebEventServiceImpl(PhoenixDaoImpl phoenixDaoImpl) {
		// TODO Auto-generated constructor stub
		this.phoenixDaoImpl = phoenixDaoImpl;
	}

	@Override
	public WebEventModel save(WebEventModel model) throws Exception {
		// TODO Auto-generated method stub
		return phoenixDaoImpl.upsert(model);
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		phoenixDaoImpl.delete(get(id));
	}

	@Override
	public WebEventModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		WebEventModel paramModel = new WebEventModel();
		paramModel.setId(id);
		List<WebEventModel> webEvents = phoenixDaoImpl.search(WebEventModel.class, paramModel);
		if(webEvents.size() > 0){
			return webEvents.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public List<WebEventModel> getAll(String avID, Long offset, Long limit) throws Exception {
		// TODO Auto-generated method stub
		WebEventModel paramModel = new WebEventModel();
		paramModel.setAnonymousVisitorID(avID);
		paramModel.setOffset(offset);
		paramModel.setLimit(limit);
		List<WebEventModel> webEvents = phoenixDaoImpl.search(WebEventModel.class, paramModel);
		return webEvents;
	}


	@Override
	public List<WebEventModel> getAllDistinctByURL(String url, Long offset, Long limit) throws Exception {
		// TODO Auto-generated method stub
		WebEventGroupByURLModel paramModel = new WebEventGroupByURLModel();
		paramModel.setUrl(url);
		paramModel.setOffset(offset);
		paramModel.setLimit(limit);
		return phoenixDaoImpl.search(WebEventModel.class, paramModel);
	}
	@Override
	public Long countDistincyByURL(String url) throws Exception {
		// TODO Auto-generated method stub
		WebEventGroupByURLModel paramModel = new WebEventGroupByURLModel();
		paramModel.setUrl(url);
		return phoenixDaoImpl.count(paramModel);
	}
	@Override
	public Long count(String url) throws Exception {
		// TODO Auto-generated method stub
		WebEventModel paramModel = new WebEventModel();
		paramModel.setUrl(url);
		return phoenixDaoImpl.count(paramModel);
	}

	
	
}
