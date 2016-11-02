package inc.member.mapper;

import common.ObjectUtil;
import common.mapper.ListMapper;
import common.orm.query.param.Param;
import inc.member.form.MemberForm;
import inc.member.model.MemberModel;
import inc.member.param.MemberModelParam;

public interface MemberMapper extends ListMapper<MemberModel, MemberForm> {

	default Param<MemberModel> marshall(Param<MemberForm> source) throws Exception {
		Param<MemberModel> ret = new MemberModelParam();
		ObjectUtil.isPresent(source.getLimit(), value -> ret.setLimit(value));
		ObjectUtil.isPresent(source.getOffset(), value -> ret.setOffset(value));
		ObjectUtil.isPresent(source.getModel(), value -> ret.setModel(this.marshall(source.getModel())));
		ObjectUtil.isPresent(source.getSort(), value -> ret.setSort(value));
		ObjectUtil.isPresent(source.getSortBy(), value -> ret.setSortBy(value));
		return ret;
	}
}
