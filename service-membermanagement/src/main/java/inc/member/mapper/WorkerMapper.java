package inc.member.mapper;

import common.ObjectUtil;
import common.mapper.ListMapper;
import common.orm.query.param.Param;
import inc.member.form.WorkerForm;
import inc.member.model.WorkerModel;
import inc.member.param.WorkerModelParam;

public interface WorkerMapper extends ListMapper<WorkerModel, WorkerForm> {

	default Param<WorkerModel> marshall(Param<WorkerForm> source) throws Exception {
		Param<WorkerModel> ret = new WorkerModelParam();
		ObjectUtil.isPresent(source.getLimit(), value -> ret.setLimit(value));
		ObjectUtil.isPresent(source.getOffset(), value -> ret.setOffset(value));
		ObjectUtil.isPresent(source.getModel(), value -> ret.setModel(this.marshall(source.getModel())));
		ObjectUtil.isPresent(source.getSort(), value -> ret.setSort(value));
		ObjectUtil.isPresent(source.getSortBy(), value -> ret.setSortBy(value));
		return ret;
	}
}
