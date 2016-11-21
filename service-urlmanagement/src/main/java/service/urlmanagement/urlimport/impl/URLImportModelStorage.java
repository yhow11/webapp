package service.urlmanagement.urlimport.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.urlmanagement.urlimport.model.URLImportModel;

@Service("URLImportModelStorage")
@Transactional
public class URLImportModelStorage extends PhoenixDaoImpl implements Storage<URLImportModel> {

	@Autowired
	public URLImportModelStorage(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<URLImportModel> get(Param<URLImportModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
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
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		super.createTable(URLImportModel.class);
	}

}
