package service.urlmanagement.urlimport.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.urlmanagement.urlimport.model.URLImportModel;

@Service("URLImportModelCount")
@Transactional
public class URLImportModelCount extends PhoenixDaoImpl implements Count<URLImportModel>{

	@Autowired
	public URLImportModelCount(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long count(Param<URLImportModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.getCount(param);
	}

}
