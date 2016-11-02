package inc.member.service.impl;

import org.springframework.data.mongodb.core.MongoTemplate;

import common.orm.command.SaveCommand;
import helper.mongodb.dao.HelperMongodbDao;
import inc.member.model.MemberModel;

public class MemberModelSaveCommand extends HelperMongodbDao implements SaveCommand<MemberModel>{

	public MemberModelSaveCommand(MongoTemplate template) {
		super(template);
		// TODO Auto-generated constructor stub
	}

	@Override
	public MemberModel execute(MemberModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.save(MemberModel.class, model);
	}


}
