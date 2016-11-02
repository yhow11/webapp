package common.orm.query.util;

public class QueryUtil {

	public static Long getOffset(String page, String limit) throws Exception {
		Long tPage = Long.valueOf(page);
		Long tLimit = Long.valueOf(limit);
		return (tPage * tLimit) - tLimit;
	}
}
