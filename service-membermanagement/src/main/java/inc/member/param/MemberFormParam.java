package inc.member.param;

import common.orm.query.param.Param;
import inc.member.form.MemberForm;

public class MemberFormParam extends Param<MemberForm>{

	public MemberFormParam() {
		// TODO Auto-generated constructor stub
	}
	public MemberFormParam(String id) {
		// TODO Auto-generated constructor stub
		this.model = new MemberForm();
		model.setId(id);
	}
}
