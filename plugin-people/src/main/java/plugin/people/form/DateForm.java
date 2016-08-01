package plugin.people.form;

public class DateForm {

	private String id;
	private String date;
	private String weekStartDate;
	private String weekEndDate;
	private String worshipServiceStatus = WorshipServiceEnum.NA.toString();

	public String getWorshipServiceStatus() {
		return worshipServiceStatus;
	}

	public void setWorshipServiceStatus(String worshipServiceStatus) {
		this.worshipServiceStatus = worshipServiceStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWeekStartDate() {
		return weekStartDate;
	}

	public void setWeekStartDate(String weekStartDate) {
		this.weekStartDate = weekStartDate;
	}

	public String getWeekEndDate() {
		return weekEndDate;
	}

	public void setWeekEndDate(String weekEndDate) {
		this.weekEndDate = weekEndDate;
	}

}
