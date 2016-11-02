package inc.member.service.impl;

import common.orm.query.QueryOne;
import common.orm.query.QueryOneByID;
import inc.member.form.MemberForm;
import inc.member.param.MemberFormParam;

public class MemberFormQueryOneByKey implements QueryOneByID<MemberForm>{

	private QueryOne<MemberForm> query;
	
	public MemberFormQueryOneByKey(QueryOne<MemberForm> query) {
		// TODO Auto-generated constructor stub
		this.query = query;
	}

	@Override
	public MemberForm query(String id) throws Exception {
		// TODO Auto-generated method stub
		return query.query(new MemberFormParam(id));
	}

}
