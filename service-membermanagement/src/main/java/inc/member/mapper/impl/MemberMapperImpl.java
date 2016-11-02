package inc.member.mapper.impl;


import common.DateUtil;
import common.ObjectUtil;
import inc.member.form.MemberForm;
import inc.member.mapper.AttendanceMapper;
import inc.member.mapper.DateMapper;
import inc.member.mapper.MemberMapper;
import inc.member.mapper.OptionMapper;
import inc.member.model.MemberModel;

public class MemberMapperImpl implements MemberMapper {

	private DateMapper dateMapper;
	private OptionMapper optionMapper;
	private AttendanceMapper attendanceMapper;
	
	public MemberMapperImpl( DateMapper dateMapper, OptionMapper optionMapper, AttendanceMapper attendanceMapper) {
		// TODO Auto-generated constructor stub
		this.dateMapper = dateMapper;
		this.optionMapper = optionMapper;
		this.attendanceMapper =  attendanceMapper;
	}
	@Override
	public MemberModel marshall(MemberForm source) throws Exception {
		// TODO Auto-generated method stub
		return marshall(source, new MemberModel());
	}

	@Override
	public MemberForm unmarshall(MemberModel source)  throws Exception{
		// TODO Auto-generated method stub
		return unmarshall(source, new MemberForm());
	}

	@Override
	public MemberModel marshall(MemberForm source, MemberModel target) throws Exception{
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getArea(), value -> target.setArea(value));
		ObjectUtil.isPresent(source.getCreatedDate(), value -> target.setCreatedDate(DateUtil.simpleParse(value)));
		ObjectUtil.isPresent(source.getDcode(), value -> target.setDcode(value));
		ObjectUtil.isPresent(source.getGroup(), value -> target.setGroup(value));
		ObjectUtil.isPresent(source.getId(), value -> target.setId(value));
		ObjectUtil.isPresent(source.getLcode(), value -> target.setLcode(value));
		ObjectUtil.isPresent(source.getName(), value -> target.setName(value));
		target.getDkAttendance().addAll(dateMapper.marshall(source.getDkAttendance()));
		target.getSkAttendance().addAll(dateMapper.marshall(source.getSkAttendance()));
		target.getWsAttendance().addAll(dateMapper.marshall(source.getWsAttendance()));
		target.setOptions(optionMapper.marshall(source.getOptions()));
		ObjectUtil.isPresent(source.getDkSummary(), value -> target.setDkSummary(attendanceMapper.marshall(value)));
		ObjectUtil.isPresent(source.getWsSummary(), value -> target.setDkSummary(attendanceMapper.marshall(value)));
		ObjectUtil.isPresent(source.getSkSummary(), value -> target.setDkSummary(attendanceMapper.marshall(value)));

		return target;
	}

	@Override
	public MemberForm unmarshall(MemberModel source, MemberForm target)  throws Exception{
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getArea(), value -> target.setArea(value));
		ObjectUtil.isPresent(source.getCreatedDate(), value -> target.setCreatedDate(DateUtil.simpleFormat(value)));
		ObjectUtil.isPresent(source.getDcode(), value -> target.setDcode(value));
		ObjectUtil.isPresent(source.getGroup(), value -> target.setGroup(value));
		ObjectUtil.isPresent(source.getId(), value -> target.setId(value));
		ObjectUtil.isPresent(source.getLcode(), value -> target.setLcode(value));
		ObjectUtil.isPresent(source.getName(), value -> target.setName(value));
		target.getDkAttendance().addAll(dateMapper.unmarshall(source.getDkAttendance()));
		target.getWsAttendance().addAll(dateMapper.unmarshall(source.getSkAttendance()));
		target.getSkAttendance().addAll(dateMapper.unmarshall(source.getWsAttendance()));
		target.setOptions(optionMapper.unmarshall(source.getOptions()));
		target.setDkSummary(attendanceMapper.unmarshall(source.getDkSummary()));
		target.setSkSummary(attendanceMapper.unmarshall(source.getWsSummary()));
		target.setWsSummary(attendanceMapper.unmarshall(source.getSkSummary()));
		return target;
	}

}
