package service.metricmanagement.enums;

public enum MetricTypeEnum {

	PAGE_COUNT("PAGECOUNT", "Page Count"),
	TIME_ON_PAGE("TIMEONPAGE", "Time On Page"),
	KEY_SUM("KEYSUM", "Key Sum");
	
	private String type;
	private String name;
	
	MetricTypeEnum(String type, String name){
		this.type = type;
		this.name = name;
	}
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	public static MetricTypeEnum getByType(String type){
	  for(MetricTypeEnum e : MetricTypeEnum.values()){
	    if(type.equals(e.getType())) {
	    	return e;
	    }
	  }
	  return null;
	}
}
