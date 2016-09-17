package service.metricmanagement.enums;

public enum MetricTypeEnum {

	PAGE_COUNT("PAGECOUNT"),
	TIME_ON_PAGE("TIMEONPAGE"),
	KEY_SUM("KEYSUM");
	
	private String type;
	
	MetricTypeEnum(String type){
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
