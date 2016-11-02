package common;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {

	public static final String SIMPLE_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	
	public static Date simpleParse(String dateStr){
		try {
			return DateUtils.parseDate(dateStr, SIMPLE_DATE_PATTERN);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String simpleFormat(Date date){
		return DateFormatUtils.format(date, SIMPLE_DATE_PATTERN);
	}
}
