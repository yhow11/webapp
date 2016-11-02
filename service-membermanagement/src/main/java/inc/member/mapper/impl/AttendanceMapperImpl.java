package inc.member.mapper.impl;


import common.DateUtil;
import common.ObjectUtil;
import inc.member.form.SummaryForm;
import inc.member.mapper.AttendanceMapper;
import inc.member.model.SummaryModel;

public class AttendanceMapperImpl implements AttendanceMapper {

	@Override
	public SummaryModel marshall(SummaryForm source) throws Exception {
		// TODO Auto-generated method stub
		return marshall(source, new SummaryModel());
	}

	@Override
	public SummaryForm unmarshall(SummaryModel source) throws Exception {
		// TODO Auto-generated method stub
		return unmarshall(source, new SummaryForm());
	}

	@Override
	public SummaryModel marshall(SummaryForm source, SummaryModel target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getAbsent(), value -> target.setAbsent(value));
		ObjectUtil.isPresent(source.getCompletionDate(), value -> target.setCompletionDate(DateUtil.simpleParse(value)));
		ObjectUtil.isPresent(source.getLeft(), value -> target.setLeft(value));
		ObjectUtil.isPresent(source.getPresent(), value -> target.setPresent(value));
		return target;
	}

	@Override
	public SummaryForm unmarshall(SummaryModel source, SummaryForm target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getAbsent(), value -> target.setAbsent(value));
		ObjectUtil.isPresent(source.getCompletionDate(), value -> target.setCompletionDate(DateUtil.simpleFormat(value)));
		ObjectUtil.isPresent(source.getLeft(), value -> target.setLeft(value));
		ObjectUtil.isPresent(source.getPresent(), value -> target.setPresent(value));
		return target;
	}

}
