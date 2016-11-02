package inc.member.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

public class SummaryModel {
	
	@Field
	private String present;
	@Field
	private String absent;
	@Field
	private String left;
	@Field
	private Date completionDate;
	
	public String getPresent() {
		return present;
	}
	public void setPresent(String present) {
		this.present = present;
	}
	public String getAbsent() {
		return absent;
	}
	public void setAbsent(String absent) {
		this.absent = absent;
	}
	public String getLeft() {
		return left;
	}
	public void setLeft(String left) {
		this.left = left;
	}
	public Date getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}
	
}
