package inc.member.mapper.impl;


import common.DateUtil;
import common.ObjectUtil;
import inc.member.form.AttendanceForm;
import inc.member.mapper.DateMapper;
import inc.member.model.AttendanceModel;

public class DateMapperImpl implements DateMapper {

	@Override
	public AttendanceModel marshall(AttendanceForm source) throws Exception {
		// TODO Auto-generated method stub
		return marshall(source , new  AttendanceModel());
	}

	@Override
	public AttendanceForm unmarshall(AttendanceModel source) throws Exception {
		// TODO Auto-generated method stub
		return unmarshall(source , new AttendanceForm());
	}

	@Override
	public AttendanceModel marshall(AttendanceForm source, AttendanceModel target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getDate(), value -> target.setDate(DateUtil.simpleParse(value)));
		ObjectUtil.isPresent(source.getStartDate(), value -> target.setStartDate(DateUtil.simpleParse(value)));
		ObjectUtil.isPresent(source.getEndDate(), value -> target.setEndDate(DateUtil.simpleParse(value)));
		ObjectUtil.isPresent(source.getStatus(), value -> target.setStatus(value));
		return target;
	}

	@Override
	public AttendanceForm unmarshall(AttendanceModel source, AttendanceForm target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getDate(), value -> target.setDate(DateUtil.simpleFormat(value)));
		ObjectUtil.isPresent(source.getStartDate(), value -> target.setStartDate(DateUtil.simpleFormat(value)));
		ObjectUtil.isPresent(source.getEndDate(), value -> target.setEndDate(DateUtil.simpleFormat(value)));
		ObjectUtil.isPresent(source.getStatus(), value -> target.setStatus(value));
		return target;
	}

}
