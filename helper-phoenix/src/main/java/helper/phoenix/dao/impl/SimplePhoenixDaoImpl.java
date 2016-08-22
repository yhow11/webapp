package helper.phoenix.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public  class SimplePhoenixDaoImpl extends PhoenixDaoImpl {
	
	public SimplePhoenixDaoImpl(SessionFactory sessionFactory) {
		// TODO Auto-generated constructor stub
		super(sessionFactory);
	}
}
