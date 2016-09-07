package service.membermanagement.service.impl;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import common.query.QueryParam;
import helper.mongodb.dao.HelperMongodbDao;
import service.membermanagement.model.INCMemberModel;
import service.membermanagement.service.INCMemberService;

public class INCMemberMongoServiceImpl extends HelperMongodbDao implements INCMemberService{

	public INCMemberMongoServiceImpl(MongoTemplate template) {
		// TODO Auto-generated constructor stub
		super(template);
	}

	public INCMemberModel save(INCMemberModel e) throws Exception {
		// TODO Auto-generated method stub
		super.save(INCMemberModel.class, e); return e;
	}

	public INCMemberModel getOne(QueryParam<INCMemberModel> e) throws Exception {
		// TODO Auto-generated method stub
		List<INCMemberModel> results = super.get(e);
		if(results.size() > 0) {
			return results.get(0);
		}
		return null;
	}

	public INCMemberModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<INCMemberModel> param = new QueryParam<INCMemberModel>(INCMemberModel.class);
		param.getModel().setId(id);
		List<INCMemberModel> results = super.get(param);
		if(results.size() > 0) {
			return results.get(0);
		}
		return null;
	}

	public List<INCMemberModel> getAll(QueryParam<INCMemberModel> e) throws Exception {
		// TODO Auto-generated method stub
		return super.get(e);
	}

	public void remove(INCMemberModel e) throws Exception {
		// TODO Auto-generated method stub
		template.remove(e);
	}

	public Long getCount(QueryParam<INCMemberModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.count(param);
	}


}
