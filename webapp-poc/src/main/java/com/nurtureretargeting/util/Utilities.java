package com.nurtureretargeting.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Utilities {

	public static <E> ArrayList<E> makeCollection(Iterable<E> iter) {
		ArrayList<E> list = new ArrayList<E>();
	    for (E item : iter) {
	        list.add(item);
	    }
	    return list;
	}
	
	
	public static Date getStartOfDay(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    return calendar.getTime();
	}
	public static Date getDate(Date date, int diff) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, diff);
		Date oneHourBack = cal.getTime();
	    return oneHourBack;
	}
}
