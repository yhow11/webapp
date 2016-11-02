package inc.member.service.impl;

import common.orm.aggregator.Count;
import common.orm.query.param.Param;
import inc.member.form.MemberForm;
import inc.member.mapper.MemberMapper;
import inc.member.model.MemberModel;

public class MemberFormCount implements Count<MemberForm> {

	private Count<MemberModel> aggregator;
	private MemberMapper mapper;
	
	public MemberFormCount(Count<MemberModel> aggregator, MemberMapper mapper) {
		// TODO Auto-generated constructor stub
		this.aggregator = aggregator;
		this.mapper = mapper;
	}

	@Override
	public Long aggregate(Param<MemberForm> param) throws Exception {
		// TODO Auto-generated method stub
		return aggregator.aggregate(mapper.marshall(param));
	}

}
