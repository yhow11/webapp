package inc.member.service.impl;

import org.springframework.data.mongodb.core.MongoTemplate;

import common.orm.aggregator.Count;
import common.orm.query.param.Param;
import helper.mongodb.dao.HelperMongodbDao;
import inc.member.model.MemberModel;

public class MemberModelCount extends HelperMongodbDao implements Count<MemberModel>{


	public MemberModelCount(MongoTemplate template) {
		super(template);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long aggregate(Param<MemberModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.getCount(param);
	}


}
