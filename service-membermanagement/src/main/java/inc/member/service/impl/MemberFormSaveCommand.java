package inc.member.service.impl;

import common.orm.command.SaveCommand;
import inc.member.form.MemberForm;
import inc.member.mapper.MemberMapper;
import inc.member.model.MemberModel;

public class MemberFormSaveCommand implements SaveCommand<MemberForm>{

	private SaveCommand<MemberModel> command;
	private MemberMapper mapper;
	
	public MemberFormSaveCommand(SaveCommand<MemberModel> command, MemberMapper mapper) {
		this.command = command;
		this.mapper = mapper;
	}

	@Override
	public MemberForm execute(MemberForm model) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(command.execute(mapper.marshall(model)));
	}


}
