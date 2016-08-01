package plugin.highchart.enums;

public enum ChartCategoryEnum {

	GROUP("group"),
	AREA("area"),
	LOCAL("lcode"),
	DISTRICT("dcode");
	
	private String code;
    public String getCode(){return code;}
    public void setCode(String code){this.code = code;}
    private ChartCategoryEnum(String code){this.code = code;}
}
