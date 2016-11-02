package inc.member.service.impl;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import helper.mongodb.dao.HelperMongodbDao;
import inc.member.model.MemberModel;

public class MemberModelQueryList extends HelperMongodbDao implements Storage<MemberModel>{

	public MemberModelQueryList(MongoTemplate template) {
		// TODO Auto-generated constructor stub
		super(template);
	}

	@Override
	public List<MemberModel> query(Param<MemberModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.get(param);
	}



}
