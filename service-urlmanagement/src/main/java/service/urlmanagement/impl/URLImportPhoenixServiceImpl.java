package service.urlmanagement.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import common.query.QueryParam;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.urlmanagement.URLImportService;
import service.urlmanagement.model.URLImportModel;

public class URLImportPhoenixServiceImpl extends PhoenixDaoImpl implements URLImportService {

	public URLImportPhoenixServiceImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Long getCountDistinctURL(String url) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<URLImportModel> param = new QueryParam<URLImportModel>(URLImportModel.class);
		param.getModel().setUrl(url);
		return super.count(param);
	}

	@Override
	public List<URLImportModel> getAll(QueryParam<URLImportModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public Long getCount(QueryParam<URLImportModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.count(param);
	}

	@Override
	public URLImportModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<URLImportModel> param = new QueryParam<URLImportModel>(URLImportModel.class);
		param.getModel().setUrl(id);
		return super.searchOne(param);
	}

	@Override
	public URLImportModel save(URLImportModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}

	@Override
	public void remove(URLImportModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}

	@Override
	public Long getCount(String url) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<URLImportModel> param = new QueryParam<URLImportModel>(URLImportModel.class);
		param.getModel().setUrl(url);
		return super.count(param);
	}

	@Override
	public List<URLImportModel> getAll(String url, Long offset, Long limit) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<URLImportModel> param = new QueryParam<URLImportModel>(URLImportModel.class);
		param.getModel().setUrl(url);
		param.setLimit(limit);
		param.setOffset(offset);
		return super.search(param);
	}

}
