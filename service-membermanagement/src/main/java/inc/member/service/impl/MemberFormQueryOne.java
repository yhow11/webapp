package inc.member.service.impl;

import common.orm.query.QueryOne;
import common.orm.query.param.Param;
import inc.member.form.MemberForm;
import inc.member.mapper.MemberMapper;
import inc.member.model.MemberModel;

public class MemberFormQueryOne implements QueryOne<MemberForm>{

	private QueryOne<MemberModel> query;
	private MemberMapper mapper;
	
	public MemberFormQueryOne(QueryOne<MemberModel> query, MemberMapper mapper) {
		// TODO Auto-generated constructor stub
		this.query = query;
		this.mapper = mapper;
	}

	@Override
	public MemberForm query(Param<MemberForm> param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(query.query(mapper.marshall(param)));
	}

}
