package common.query.enums;

public enum QuerySortEnum {
	ASC("asc"),
	DESC("desc");

	private String type;
	QuerySortEnum(String type){
		this.type = type;
	}
	public String getType() {
		return type;
	}
	
}
