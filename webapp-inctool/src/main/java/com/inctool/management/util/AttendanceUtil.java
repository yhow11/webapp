package com.inctool.management.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.inctool.common.enums.WorshipServiceEnum;
import com.inctool.common.form.DateForm;
import com.inctool.management.form.AttendanceForm;
import com.inctool.management.service.AttendanceService;

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

	public static List<DateForm> fillSchedule(AttendanceService attendanceService, List<DateForm> dates)
			throws Exception {
		return attendanceService.fillSchedule(dates);
	}
	
	public static AttendanceForm fillAttendance(AttendanceService attendanceService, AttendanceForm attendanceForm, List<DateForm> dates)
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
	
	public static DateForm getLastPresentDate(Integer position, List<DateForm> dates) throws Exception{
		for(Integer x = position; x >= 0; x--){
			DateForm dateForm = dates.get(x);
			if(dateForm.getStatus().equals(WorshipServiceEnum.PRESENT.toString())){
				return dateForm;
			}
		}
		return null;
	}
	
	public static DateForm supplyCutOffDates(Date date, DateForm dateForm){
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
