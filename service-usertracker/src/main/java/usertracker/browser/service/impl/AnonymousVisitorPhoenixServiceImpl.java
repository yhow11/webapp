package usertracker.browser.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import common.query.QueryParam;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.model.AnonymousVisitorModel;
import usertracker.browser.service.AnonymousVisitorService;

public class AnonymousVisitorPhoenixServiceImpl extends PhoenixDaoImpl implements AnonymousVisitorService {
	
	public AnonymousVisitorPhoenixServiceImpl(SessionFactory sessionFactory) {
		// TODO Auto-generated constructor stub
		super(sessionFactory);
	}

	@Override
	public List<AnonymousVisitorModel> getAll(QueryParam<AnonymousVisitorModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public Long getCount(QueryParam<AnonymousVisitorModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.count(param);
	}

	@Override
	public AnonymousVisitorModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<AnonymousVisitorModel> param = new QueryParam<AnonymousVisitorModel>(AnonymousVisitorModel.class);
		param.getModel().setId("%"+id);
		return super.searchOne(param);
	}

	@Override
	public AnonymousVisitorModel save(AnonymousVisitorModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}

	@Override
	public void remove(AnonymousVisitorModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}

}
