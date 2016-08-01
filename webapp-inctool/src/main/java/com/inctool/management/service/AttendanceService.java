package com.inctool.management.service;

import java.util.List;

import com.inctool.common.form.DateForm;
import com.inctool.management.form.AttendanceForm;

public abstract class AttendanceService {

	public static final String SIMPLE_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public abstract List<DateForm> fillSchedule(List<DateForm> dates) throws Exception;
	public abstract AttendanceForm fillAttendance(AttendanceForm attendanceForm, List<DateForm> dates) throws Exception;
}
