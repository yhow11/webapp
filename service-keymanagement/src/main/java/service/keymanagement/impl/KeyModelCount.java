package service.keymanagement.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.keymanagement.model.KeyModel;

@Service("KeyModelCount")
@Transactional
public class KeyModelCount extends PhoenixDaoImpl implements Count<KeyModel> {

	@Autowired
	public KeyModelCount(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Long count(Param<KeyModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.getCount(param);
	}


}
