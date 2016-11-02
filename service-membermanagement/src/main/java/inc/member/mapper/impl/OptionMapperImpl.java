package inc.member.mapper.impl;


import common.ObjectUtil;
import inc.member.form.OptionForm;
import inc.member.mapper.OptionMapper;
import inc.member.model.OptionModel;

public class OptionMapperImpl implements OptionMapper {
	
	@Override
	public OptionModel marshall(OptionForm source) throws Exception {
		// TODO Auto-generated method stub
		return marshall(source, new OptionModel());
	}

	@Override
	public OptionForm unmarshall(OptionModel source) throws Exception {
		// TODO Auto-generated method stub
		return unmarshall(source, new OptionForm());
	}

	@Override
	public OptionModel marshall(OptionForm source, OptionModel target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getName(), value -> target.setName(value));
		ObjectUtil.isPresent(source.getValue(), value -> target.setValue(value));
		return target;
	}

	@Override
	public OptionForm unmarshall(OptionModel source, OptionForm target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getName(), value -> target.setName(value));
		ObjectUtil.isPresent(source.getValue(), value -> target.setValue(value));
		return target;
	}

}
