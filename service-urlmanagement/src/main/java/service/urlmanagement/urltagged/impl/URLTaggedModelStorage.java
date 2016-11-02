package service.urlmanagement.urltagged.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.urlmanagement.urltagged.model.URLTaggedModel;

@Service("URLTaggedModelStorage")
@Transactional
public class URLTaggedModelStorage extends PhoenixDaoImpl implements Storage<URLTaggedModel> {

	@Autowired
	public URLTaggedModelStorage(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<URLTaggedModel> get(Param<URLTaggedModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
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
	public void create() throws Exception {
		// TODO Auto-generated method stub
		super.createTable(URLTaggedModel.class);
	}

}
