package inc.member.service.impl;

import common.ObjectUtil;
import common.orm.query.QueryOneByID;
import inc.member.form.MemberForm;

public class MemberFormQueryOneAdapter  implements QueryOneByID<MemberForm>  {

	private QueryOneByID<MemberForm> query;
	private QueryOneByID<MemberForm> template;
	
	public MemberFormQueryOneAdapter(QueryOneByID<MemberForm> query, QueryOneByID<MemberForm> template) {
		// TODO Auto-generated constructor stub
		this.query = query;
		this.template = template;
	}
	@Override
	public MemberForm query(String id) throws Exception {
		// TODO Auto-generated method stub
		MemberForm form = null;
		form =  ObjectUtil.isPresentWithReturn(id, (value) -> {
			return query.query(value);
		});
		form =  ObjectUtil.isNotPresentWithReturn(id, (value) -> {
			return template.query(value);
		});
		return form;
	}

}
