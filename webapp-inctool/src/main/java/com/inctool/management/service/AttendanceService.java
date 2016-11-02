package com.inctool.management.service;

import java.util.List;


import inc.member.form.SummaryForm;
import inc.member.form.AttendanceForm;

public abstract class AttendanceService {

	public static final String SIMPLE_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public abstract List<AttendanceForm> fillSchedule(List<AttendanceForm> dates) throws Exception;
	public abstract SummaryForm fillAttendance(SummaryForm attendanceForm, List<AttendanceForm> dates) throws Exception;
}
