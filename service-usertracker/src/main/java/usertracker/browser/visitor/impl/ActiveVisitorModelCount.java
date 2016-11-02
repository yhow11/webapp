package usertracker.browser.visitor.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.visitor.model.ActiveVisitorModel;

@Service("ActiveVisitorModelCount")
@Transactional
public class ActiveVisitorModelCount extends PhoenixDaoImpl implements Count<ActiveVisitorModel> {
	
	@Autowired
	public ActiveVisitorModelCount(SessionFactory sessionFactory) throws Exception {
		super(sessionFactory);
	}
	

	@Override
	public Long count(Param<ActiveVisitorModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.getCount(param);
	}


	

}
