package service.membermanagement.service.impl;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import common.query.QueryParam;
import helper.mongodb.dao.HelperMongodbDao;
import service.membermanagement.model.INCWorkerModel;
import service.membermanagement.service.INCWorkerService;

public class INCWorkerMongoServiceImpl extends HelperMongodbDao implements INCWorkerService{

	public INCWorkerMongoServiceImpl(MongoTemplate template) {
		// TODO Auto-generated constructor stub
		super(template);
	}

	public INCWorkerModel save(INCWorkerModel e) throws Exception {
		// TODO Auto-generated method stub
		super.save(INCWorkerModel.class, e); return e;
	}
	
	public List<INCWorkerModel> getAll(QueryParam<INCWorkerModel> e) throws Exception {
		// TODO Auto-generated method stub
		return super.get(e);
	}

	public void remove(INCWorkerModel e) throws Exception {
		// TODO Auto-generated method stub
		template.remove(e);
	}

	public Long getCount(QueryParam<INCWorkerModel> e) throws Exception {
		// TODO Auto-generated method stub
		return super.count(e);
	}

	public INCWorkerModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<INCWorkerModel> param = new QueryParam<INCWorkerModel>(INCWorkerModel.class);
		param.getModel().setId(id);
		List<INCWorkerModel> results = super.get(param);
		if(results.size() > 0) {
			return results.get(0);
		}
		return null;
	}
	

}
