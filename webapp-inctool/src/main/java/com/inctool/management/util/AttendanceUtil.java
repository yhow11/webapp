package com.inctool.management.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.inctool.management.service.AttendanceService;

import inc.member.enums.WorshipServiceEnum;
import inc.member.form.SummaryForm;
import inc.member.form.AttendanceForm;

public class AttendanceUtil {

	
	public static final Integer R310_LIMIT = 25;
	public static final String SIMPLE_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	public static String getCompletionDate(String completionDate, String absent) throws Exception {
		return getCompletionDate(DateUtils.parseDate(completionDate, SIMPLE_DATE_PATTERN), absent);
	}

	public static String getCompletionDate(Date completionDate, String absent) {
		Calendar c = Calendar.getInstance();
		c.setTime(completionDate);
		for (int ctr = 1; ctr <= Integer.valueOf(absent); ctr++) {
			c.setTime(DateUtils.addWeeks(c.getTime(), 1));
		}
		while (c.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
			c.add(Calendar.DATE, 1);
		}

		return DateFormatUtils.format(c.getTime(), SIMPLE_DATE_PATTERN);
	}

	public static List<AttendanceForm> fillSchedule(AttendanceService attendanceService, List<AttendanceForm> dates)
			throws Exception {
		return attendanceService.fillSchedule(dates);
	}
	
	public static SummaryForm fillAttendance(AttendanceService attendanceService, SummaryForm attendanceForm, List<AttendanceForm> dates)
			throws Exception  {
		return attendanceService.fillAttendance(attendanceForm, dates);
	}
	
	public static Date getStartDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		if(c.get(Calendar.DAY_OF_WEEK) >= Calendar.MONDAY && c.get(Calendar.DAY_OF_WEEK) <= Calendar.THURSDAY){
			while (c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
		        c.add(Calendar.DATE, -1);
		    }
		} else {
			while (c.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
		        c.add(Calendar.DATE, -1);
		    }
		}
		return c.getTime();
	}
	
	public static AttendanceForm getLastPresentDate(Integer position, List<AttendanceForm> dates) throws Exception{
		for(Integer x = position; x >= 0; x--){
			AttendanceForm dateForm = dates.get(x);
			if(dateForm.getStatus().equals(WorshipServiceEnum.PRESENT.toString())){
				return dateForm;
			}
		}
		return null;
	}
	
	public static AttendanceForm supplyCutOffDates(Date date, AttendanceForm dateForm){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		if(c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
			dateForm.setStartDate(DateFormatUtils.format(c.getTime(),SIMPLE_DATE_PATTERN));
			dateForm.setEndDate(DateFormatUtils.format(DateUtils.addDays(c.getTime(), 4),SIMPLE_DATE_PATTERN));
		} else {
			dateForm.setStartDate(DateFormatUtils.format(c.getTime(),SIMPLE_DATE_PATTERN));
			dateForm.setEndDate(DateFormatUtils.format(DateUtils.addDays(c.getTime(), 3),SIMPLE_DATE_PATTERN));
		}
		return dateForm;
	}
	
	public static Date nextCutOff(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		if(c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
			c.add(Calendar.DATE, 4); 
		} else {
			c.add(Calendar.DATE, 3); 
		}
		return c.getTime();
	}
	
	public static Date previousCutOff(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		if(c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
			c.add(Calendar.DATE, -3); 
		} else {
			c.add(Calendar.DATE, -4); 
		}
		return c.getTime();
	}
}
