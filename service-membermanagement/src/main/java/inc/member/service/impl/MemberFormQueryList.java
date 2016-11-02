package inc.member.service.impl;

import java.util.List;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import inc.member.form.MemberForm;
import inc.member.mapper.MemberMapper;
import inc.member.model.MemberModel;

public class MemberFormQueryList implements Storage<MemberForm>{

	private Storage<MemberModel> query;
	private MemberMapper mapper;
	
	public MemberFormQueryList(Storage<MemberModel> query, MemberMapper mapper) {
		// TODO Auto-generated constructor stub
		this.query = query;
		this.mapper = mapper;
	}


	@Override
	public List<MemberForm> query(Param<MemberForm> param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(query.query(mapper.marshall(param)));
	}

}
