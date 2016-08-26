package service.urlmanagement.impl;

import java.util.List;

import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.urlmanagement.model.URLTaggedModel;

public class URLTaggedServiceImpl extends URLServiceImpl<URLTaggedModel> {

	private PhoenixDaoImpl phoenixDaoImpl;
	
	public URLTaggedServiceImpl(PhoenixDaoImpl phoenixDaoImpl) {
		// TODO Auto-generated constructor stub
		this.phoenixDaoImpl = phoenixDaoImpl;
	}

	@Override
	public URLTaggedModel save(URLTaggedModel model) throws Exception {
		// TODO Auto-generated method stub
		return phoenixDaoImpl.upsert(model);
	}

	@Override
	public List<URLTaggedModel> getAll(String url) throws Exception {
		// TODO Auto-generated method stub
		URLTaggedModel param = new URLTaggedModel();
		param.setUrl(url);
		return phoenixDaoImpl.search(URLTaggedModel.class, param);
	}

	@Override
	public void delete(String url) throws Exception {
		// TODO Auto-generated method stub
		List<URLTaggedModel> urlModels = getAll(url);
		for(URLTaggedModel urlModel:urlModels){
			phoenixDaoImpl.delete(urlModel);
		}
	}

}
