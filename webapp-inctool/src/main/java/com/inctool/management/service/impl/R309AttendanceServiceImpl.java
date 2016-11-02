package com.inctool.management.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.inctool.management.service.AttendanceService;
import com.inctool.management.util.AttendanceUtil;

import inc.member.enums.WorshipServiceEnum;
import inc.member.form.AttendanceForm;
import inc.member.form.SummaryForm;

public class R309AttendanceServiceImpl extends AttendanceService {

	@Override
	public List<AttendanceForm> fillSchedule(List<AttendanceForm> dates) throws Exception {
		
		dates = addDays(dates, 1);
		
		Date firstTimeAttended = DateUtils.parseDate(dates.get(0).getDate(), SIMPLE_DATE_PATTERN);
		
		Date currentDate = new Date();
		
		Calendar c = Calendar.getInstance();
		c.setTime(AttendanceUtil.getStartDate(firstTimeAttended));
		
	    
	    Date start = c.getTime();
	    Date end = null;
	    
	    System.out.println(DateFormatUtils.format(c, SIMPLE_DATE_PATTERN));
	    
		for(Integer counter = 0 ; counter < dates.size(); counter ++){
			AttendanceForm date = dates.get(counter);

			c.setTime(AttendanceUtil.nextCutOff(c.getTime()));
			
			end = c.getTime();
			
			date.setStartDate(DateFormatUtils.format(start, SIMPLE_DATE_PATTERN));
			date.setEndDate(DateFormatUtils.format(end, SIMPLE_DATE_PATTERN));
			
			if(date.getDate() != null) {
				Date correctedDate = DateUtils.parseDate(date.getDate(), SIMPLE_DATE_PATTERN);
				if((start.before(correctedDate)|| start.equals(correctedDate)) &&
						(end.after(correctedDate))	){
					date.setStatus(WorshipServiceEnum.PRESENT.toString());
				} else if(currentDate.before(end) || currentDate.equals(end)){
					date.setStatus(WorshipServiceEnum.NA.toString());
				} else {
					
					
					start = AttendanceUtil.getStartDate(correctedDate);
					
					c.setTime(AttendanceUtil.nextCutOff(c.getTime()));
					
					end = c.getTime();
					date.setStatus(WorshipServiceEnum.ABSENT.toString());
					date.setStartDate(DateFormatUtils.format(start, SIMPLE_DATE_PATTERN));
					date.setEndDate(DateFormatUtils.format(end, SIMPLE_DATE_PATTERN));
					date.setAbsents(getDateOfAbsence(counter, dates));
					date.setStatus(WorshipServiceEnum.PRESENT.toString());
				}
			} else {
				if(currentDate.before(end)){
					date.setStatus(WorshipServiceEnum.NA.toString());
				} else {
					date.setStatus(WorshipServiceEnum.ABSENT.toString());
				}
			}
			
			System.out.println("Start:" +DateFormatUtils.format(start, SIMPLE_DATE_PATTERN));
			System.out.println("End:" +DateFormatUtils.format(end, SIMPLE_DATE_PATTERN));
			
			start = end;
			
		}
		dates = addDays(dates, -1);
		return dates;
	}

	@Override
	public SummaryForm fillAttendance(SummaryForm attendanceForm, List<AttendanceForm> dates) throws Exception {
		// TODO Auto-generated method stub
		Integer present = 0;
		Integer absent = 0;
		Integer na = 0;
		
		for(AttendanceForm date: dates) {
			if(date.getStatus().equals(WorshipServiceEnum.PRESENT.toString())) {
				present++;
			} else if(date.getStatus().equals(WorshipServiceEnum.ABSENT.toString())) {
				absent++;
			} else {
				na++;
			}
			
			absent += date.getAbsents().size();
		}
		attendanceForm.setLeft(na.toString());
		attendanceForm.setAbsent(absent.toString());
		attendanceForm.setPresent(present.toString());
		attendanceForm.setCompletionDate(AttendanceUtil.getCompletionDate(dates.get(dates.size()-1).getEndDate(), absent.toString()));
		return attendanceForm;
	}

	private List<AttendanceForm> addDays(List<AttendanceForm> dates, Integer add) throws Exception {
		for(AttendanceForm form: dates){
			if(form.getDate() != null){
				form.setDate(DateFormatUtils.format(DateUtils.addDays(DateUtils.parseDate(form.getDate(), SIMPLE_DATE_PATTERN), add), SIMPLE_DATE_PATTERN));	
			}
		}
		return dates;
	}
	
	private List<AttendanceForm> getDateOfAbsence(Integer position, List<AttendanceForm> dates) throws Exception{
		AttendanceForm subjectDate = dates.get(position);
		AttendanceForm lastPresentDate = AttendanceUtil.getLastPresentDate(position, dates);
		
		List<AttendanceForm> returns = new ArrayList<AttendanceForm>();
		
		if(lastPresentDate != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(AttendanceUtil.getStartDate(DateUtils.parseDate(subjectDate.getDate(), SIMPLE_DATE_PATTERN)));
			
			while(!DateUtils.parseDate(lastPresentDate.getEndDate(), SIMPLE_DATE_PATTERN).equals(c.getTime())) {
				AttendanceForm form = AttendanceUtil.supplyCutOffDates(c.getTime(), new AttendanceForm()) ;
				form.setStatus(WorshipServiceEnum.ABSENT.toString());
				c.setTime(AttendanceUtil.previousCutOff(c.getTime()));
				returns.add(form);
			}
		}
		
		return returns;
	}
	
	
}
