package inc.member.service.impl;

import org.springframework.data.mongodb.core.MongoTemplate;

import common.orm.command.RemoveCommand;
import helper.mongodb.dao.HelperMongodbDao;
import inc.member.model.MemberModel;

public class MemberModelRemoveCommand extends HelperMongodbDao implements RemoveCommand<MemberModel>{

	public MemberModelRemoveCommand(MongoTemplate template) {
		super(template);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(MemberModel model) throws Exception {
		// TODO Auto-generated method stub
		template.remove(model);
	}


}
