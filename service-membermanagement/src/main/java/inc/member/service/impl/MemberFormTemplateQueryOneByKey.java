package inc.member.service.impl;

import java.util.ArrayList;
import java.util.List;

import common.orm.query.QueryOneByID;
import inc.member.form.AttendanceForm;
import inc.member.form.MemberForm;
import inc.member.form.OptionForm;

public class MemberFormTemplateQueryOneByKey implements QueryOneByID<MemberForm>{

	@Override
	public MemberForm query(String id) throws Exception {
		// TODO Auto-generated method stub
		List<AttendanceForm> dkAttendance = new ArrayList<AttendanceForm>();
		AttendanceForm dateForm = new AttendanceForm();
		for (int counter = 0; counter < 25; counter++) {
			dateForm = new AttendanceForm();
			dateForm.setId(String.valueOf(counter));
			dkAttendance.add(dateForm);
		}
		List<AttendanceForm> skAttendance = new ArrayList<AttendanceForm>();
		for (int counter = 0; counter < 15; counter++) {
			dateForm = new AttendanceForm();
			dateForm.setId(String.valueOf(counter));
			skAttendance.add(dateForm);
		}
		List<AttendanceForm> wsAttendance = new ArrayList<AttendanceForm>();
		for (int counter = 0; counter < 48; counter++) {
			dateForm = new AttendanceForm();
			dateForm.setId(String.valueOf(counter));
			wsAttendance.add(dateForm);
		}
		MemberForm memberForm = new MemberForm();
		memberForm.getDkAttendance().addAll(dkAttendance);
		memberForm.getSkAttendance().addAll(skAttendance);
		memberForm.getWsAttendance().addAll(wsAttendance);

		List<OptionForm> actions = new ArrayList<OptionForm>();
		OptionForm action = new OptionForm();
		action.setName("Edit");
		actions.add(action);
		action = new OptionForm();
		action.setName("Delete");
		actions.add(action);
		memberForm.setOptions(actions);
		return memberForm;
	}

}
