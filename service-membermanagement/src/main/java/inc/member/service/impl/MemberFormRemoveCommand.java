package inc.member.service.impl;

import common.orm.command.RemoveCommand;
import inc.member.form.MemberForm;
import inc.member.mapper.MemberMapper;
import inc.member.model.MemberModel;

public class MemberFormRemoveCommand implements RemoveCommand<MemberForm>{

	private RemoveCommand<MemberModel> command;
	private MemberMapper mapper;
	
	public MemberFormRemoveCommand(RemoveCommand<MemberModel> command, MemberMapper mapper) {
		this.command = command;
		this.mapper = mapper;
	}

	@Override
	public void execute(MemberForm model) throws Exception {
		// TODO Auto-generated method stub
		command.execute(mapper.marshall(model));
	}


}
