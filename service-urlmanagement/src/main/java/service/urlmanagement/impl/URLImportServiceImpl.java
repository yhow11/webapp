package service.urlmanagement.impl;

import java.util.List;

import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.urlmanagement.model.URLImportGroupByURLModel;
import service.urlmanagement.model.URLImportModel;

public class URLImportServiceImpl extends URLServiceImpl<URLImportModel> {

	private PhoenixDaoImpl phoenixDaoImpl;
	
	public URLImportServiceImpl(PhoenixDaoImpl phoenixDaoImpl) {
		// TODO Auto-generated constructor stub
		this.phoenixDaoImpl = phoenixDaoImpl;
	}

	@Override
	public URLImportModel save(URLImportModel object) throws Exception {
		// TODO Auto-generated method stub
		return phoenixDaoImpl.upsert(object);
	}

	@Override
	public List<URLImportModel> getAll(String url) throws Exception {
		// TODO Auto-generated method stub
		URLImportModel param = new URLImportModel();
		param.setUrl(url);
		return phoenixDaoImpl.search(URLImportModel.class, param);
	}

	@Override
	public List<URLImportModel> getAllDistinctURL(String url, Long offset, Long limit) throws Exception {
		// TODO Auto-generated method stub
		URLImportGroupByURLModel param = new URLImportGroupByURLModel();
		param.setUrl(url);
		param.setLimit(limit);
		param.setOffset(offset);
		return phoenixDaoImpl.search(URLImportModel.class, param);
	}

	@Override
	public Long getCountDistinctURL(String url) throws Exception {
		// TODO Auto-generated method stub
		URLImportGroupByURLModel param = new URLImportGroupByURLModel();
		param.setUrl(url);
		return phoenixDaoImpl.count(param);
	}

	@Override
	public void delete(String url) throws Exception {
		// TODO Auto-generated method stub
		List<URLImportModel> urlModels = getAll(url);
		for(URLImportModel urlModel:urlModels){
			phoenixDaoImpl.delete(urlModel);
		}
	}

	@Override
	public List<URLImportModel> getAll(String url, Long offset, Long limit) throws Exception {
		// TODO Auto-generated method stub
		URLImportModel param = new URLImportModel();
		param.setUrl(url);
		param.setLimit(limit);
		param.setOffset(offset);
		return phoenixDaoImpl.search(URLImportModel.class, param);
	}

	@Override
	public Long getCount(String url) throws Exception {
		// TODO Auto-generated method stub
		URLImportModel param = new URLImportModel();
		param.setUrl(url);
		return phoenixDaoImpl.count(param);
	}

}
