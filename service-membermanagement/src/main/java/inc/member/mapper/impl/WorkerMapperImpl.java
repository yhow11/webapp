package inc.member.mapper.impl;


import common.ObjectUtil;
import inc.member.form.WorkerForm;
import inc.member.mapper.OptionMapper;
import inc.member.mapper.WorkerMapper;
import inc.member.model.WorkerModel;

public class WorkerMapperImpl implements WorkerMapper {

	private OptionMapper optionMapper;
	
	public WorkerMapperImpl(OptionMapper optionMapper) {
		// TODO Auto-generated constructor stub
		this.optionMapper = optionMapper;
	}
	@Override
	public WorkerModel marshall(WorkerForm source) throws Exception {
		// TODO Auto-generated method stub
		return marshall(source, new WorkerModel());
	}

	@Override
	public WorkerForm unmarshall(WorkerModel source) throws Exception {
		// TODO Auto-generated method stub
		return unmarshall(source, new WorkerForm());
	}

	@Override
	public WorkerModel marshall(WorkerForm source,WorkerModel target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getId(), value -> target.setId(value));
		ObjectUtil.isPresent(source.getName(), value -> target.setName(value));
		target.setOptions(optionMapper.marshall(source.getOptions()));

		return target;
	}

	@Override
	public WorkerForm unmarshall(WorkerModel source, WorkerForm target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getId(), value -> target.setId(value));
		ObjectUtil.isPresent(source.getName(), value -> target.setName(value));
		target.setOptions(optionMapper.unmarshall(source.getOptions()));

		return target;
	}

}
