package service.urlmanagement.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import common.query.QueryParam;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.urlmanagement.URLTaggedService;
import service.urlmanagement.model.URLTaggedModel;

public class URLTaggedPhoenixServiceImpl extends PhoenixDaoImpl implements URLTaggedService {


	public URLTaggedPhoenixServiceImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<URLTaggedModel> getAll(QueryParam<URLTaggedModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public Long getCount(QueryParam<URLTaggedModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.count(param);
	}

	@Override
	public URLTaggedModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<URLTaggedModel> param = new QueryParam<URLTaggedModel>(URLTaggedModel.class);
		param.getModel().setUrl(id);
		return super.searchOne(param);
	}

	@Override
	public URLTaggedModel save(URLTaggedModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}

	@Override
	public void remove(URLTaggedModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}

	@Override
	public void delete(String url) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<URLTaggedModel> param = new QueryParam<URLTaggedModel>(URLTaggedModel.class);
		param.getModel().setUrl(url);
		List<URLTaggedModel> urlModels = getAll(param);
		for(URLTaggedModel urlModel:urlModels){
			super.delete(urlModel);
		}
	}

	@Override
	public List<URLTaggedModel> getAll(String url) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<URLTaggedModel> param = new QueryParam<URLTaggedModel>(URLTaggedModel.class);
		param.getModel().setUrl(url);
		return getAll(param);
	}



}
