package com.inctool.management.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

public class ScheduleUtil {

	public static Date getCompletionDate(Date completionDate, int absent) {
		Calendar c = Calendar.getInstance();
		c.setTime(completionDate);
		for(int ctr = 1; ctr <= Integer.valueOf(absent); ctr++ ){
			if(c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){
				while (c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			        c.add(Calendar.DATE, 1);
			    }
			} else {
				while (c.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
			        c.add(Calendar.DATE, 1);
			    }
			}
		}
		while (c.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
	        c.add(Calendar.DATE, 1);
	    }
		
		return c.getTime();
	}
	public static List<List<Date>> getSchedules(Date startDate) {
		List<List<Date>> schedules = new ArrayList<>();
		
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		if(c.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY && c.get(Calendar.DAY_OF_WEEK) < Calendar.THURSDAY){
			while (c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
		        c.add(Calendar.DATE, -1);
		    }
		} else {
			while (c.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
		        c.add(Calendar.DATE, 1);
		    }
		}
	    
	    Date start = c.getTime();
	    Date end = null;
	    
	    System.out.println(DateFormatUtils.format(c, "yyyy MM dd"));
		for(int x = 0; x < 48 ; x++){
			if(c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
				c.add(Calendar.DATE, 3); 
			} else {
				c.add(Calendar.DATE, 2); 
			}
			
			end = c.getTime();
			
			List<Date> ranges = new ArrayList<>();
			ranges.add(start);
			ranges.add(end);
			schedules.add(ranges);
			
			System.out.println("Start:" +DateFormatUtils.format(start, "yyyy MM dd"));
			System.out.println("End:" +DateFormatUtils.format(end, "yyyy MM dd"));
			
			if(c.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY && c.get(Calendar.DAY_OF_WEEK) <= Calendar.THURSDAY){
				while (c.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
			        c.add(Calendar.DATE, 1);
			    }
			} else {
				while (c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			        c.add(Calendar.DATE, 1);
			    }
			}
			end = c.getTime();
			start = end;
			
		}
		
		return schedules;
	}
}
