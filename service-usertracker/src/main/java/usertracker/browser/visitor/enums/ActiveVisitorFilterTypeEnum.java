package usertracker.browser.visitor.enums;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateUtils;

public enum ActiveVisitorFilterTypeEnum {
	
	MONTH("MONTH"),
	WEEK("WEEK"),
	DAY("DAY"),
	HOUR("HOUR"),
	ALL("ALL");
	
	private String type;
	
	ActiveVisitorFilterTypeEnum(String type){
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	public Long getTime() {
		if(type.equals("HOUR")){
			return DateUtils.addHours(new Date(), -1).getTime();
		} else if(type.equals("DAY")) {
			return DateUtils.addDays(new Date(), -1).getTime();
		} else if(type.equals("WEEK")) {
			return DateUtils.addWeeks(new Date(), -1).getTime();
		}  else if(type.equals("MONTH")) {
			return DateUtils.addMonths(new Date(), -1).getTime();
		} else {
			return null;
		}
	}
	public String getTimeAgo(String timestamp) {
		Date past = new Date(Long.valueOf(timestamp));
        Date now = new Date();

        if(type.equals("HOUR")){
			return TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime()) + " minutes ago";
		} else if(type.equals("DAY")) {
			return TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime()) + " hours ago";
		} else if(type.equals("WEEK")) {
			return TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime()) + " days ago";
		}  else if(type.equals("MONTH")) {
			return TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime()) + " days ago";
		} else {
			return TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime()) + " days ago";
		}
	}
}
