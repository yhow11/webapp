package service.metricmanagement.metrics.pagecount.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.metricmanagement.metrics.pagecount.model.PageCountModel;

@Service("PageCountModelPhoenixStorage")
@Transactional	
public class PageCountModelPhoenixStorage  extends PhoenixDaoImpl implements Storage<PageCountModel>{

	@Autowired
	public PageCountModelPhoenixStorage(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PageCountModel> get(Param<PageCountModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public PageCountModel save(PageCountModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}

	@Override
	public void remove(PageCountModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}

	@Override
	public void create() throws Exception {
		// TODO Auto-generated method stub
		super.createTable(PageCountModel.class);
	}

}
