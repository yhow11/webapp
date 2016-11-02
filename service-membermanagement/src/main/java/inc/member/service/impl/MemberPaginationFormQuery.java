package inc.member.service.impl;

import common.form.Page;
import common.orm.aggregator.Count;
import common.orm.query.Storage;
import common.orm.query.Storage;
import common.orm.query.param.Param;
import inc.member.form.MemberForm;

public class MemberPaginationFormQuery implements QueryPaginate<MemberForm>{

	private Storage<MemberForm> query;
	private Count<MemberForm> aggregator;
	
	public MemberPaginationFormQuery(Storage<MemberForm> query, Count<MemberForm> aggregator) {
		// TODO Auto-generated constructor stub
		this.query = query;
		this.aggregator = aggregator;
	}

	@Override
	public Page<MemberForm> query(Param<MemberForm> param) throws Exception {
		// TODO Auto-generated method stub
		return new Page<>(query.query(param), aggregator.aggregate(param));
	}

}
