package sparkapp.collation.receiver.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import helper.phoenix.dao.impl.PhoenixDaoImpl;

@Service("SimplePhoenixDaoImpl")
@Transactional
public  class SimplePhoenixDaoImpl extends PhoenixDaoImpl {
	@Autowired
	public SimplePhoenixDaoImpl(SessionFactory sessionFactory) {
		// TODO Auto-generated constructor stub
		super(sessionFactory);
	}
}
