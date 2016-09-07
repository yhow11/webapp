package usertracker.browser.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import common.query.QueryParam;
import common.query.enums.QuerySortEnum;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.service.VisitorLogService;

public class VisitorLogPhoenixServiceImpl extends PhoenixDaoImpl implements VisitorLogService {
	
	public VisitorLogPhoenixServiceImpl(SessionFactory sessionFactory) {
		// TODO Auto-generated constructor stub
		super(sessionFactory);
	}

	@Override
	public VisitorLogModel save(VisitorLogModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}

	@Override
	public List<VisitorLogModel> getAll(QueryParam<VisitorLogModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public Long getCount(QueryParam<VisitorLogModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.count(param);
	}

	@Override
	public VisitorLogModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<VisitorLogModel> param = new QueryParam<VisitorLogModel>(VisitorLogModel.class);
		param.getModel().setId(Long.valueOf(id));
		return super.searchOne(param);
	}

	@Override
	public void remove(VisitorLogModel param) throws Exception {
		// TODO Auto-generated method stub
		super.delete(param);
	}

	@Override
	public List<VisitorLogModel> getAll(Long offset, Long limit) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<VisitorLogModel> param = new QueryParam<VisitorLogModel>(VisitorLogModel.class);
		param.setSort(QuerySortEnum.DESC);
		param.setSortBy("id");
		param.setLimit(limit);
		param.setOffset(offset);
		return super.search(param);
	}

	
	
}
