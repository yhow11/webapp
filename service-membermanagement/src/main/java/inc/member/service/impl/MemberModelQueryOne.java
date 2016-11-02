package inc.member.service.impl;

import common.ObjectUtil;
import common.orm.query.Storage;
import common.orm.query.QueryOne;
import common.orm.query.param.Param;
import inc.member.model.MemberModel;

public class MemberModelQueryOne implements QueryOne<MemberModel>{

	private Storage<MemberModel> query;
	
	public MemberModelQueryOne(Storage<MemberModel> query) {
		// TODO Auto-generated constructor stub
		this.query = query;
	}

	@Override
	public MemberModel query(Param<MemberModel> param) throws Exception {
		// TODO Auto-generated method stub
		return ObjectUtil.isPresentWithReturnOne(query.query(param), values ->{
			return values.get(0);
		});
	}



}
