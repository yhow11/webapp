package service.urlmanagement.impl;

import java.util.List;

import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.urlmanagement.URLManagementService;
import service.urlmanagement.model.URLDistinctKeyModel;
import service.urlmanagement.model.URLModel;

public class URLManagementServiceImpl implements URLManagementService {

	private PhoenixDaoImpl phoenixDaoImpl;
	
	public URLManagementServiceImpl(PhoenixDaoImpl phoenixDaoImpl) {
		// TODO Auto-generated constructor stub
		this.phoenixDaoImpl = phoenixDaoImpl;
	}

	public URLModel save(URLModel model) throws Exception {
		// TODO Auto-generated method stub
		return phoenixDaoImpl.upsert(model);
	}

	public List<URLModel> getAll(String url) throws Exception {
		// TODO Auto-generated method stub
		URLModel param = new URLModel();
		param.setUrl(url);
		return phoenixDaoImpl.search(URLModel.class, param);
	}

	public void deleteByURL(String url) throws Exception {
		// TODO Auto-generated method stub
		List<URLModel> urlModels = getAll(url);
		for(URLModel urlModel:urlModels){
			phoenixDaoImpl.delete(urlModel);
		}
	}

}
