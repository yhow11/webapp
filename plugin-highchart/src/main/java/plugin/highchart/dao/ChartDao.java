package plugin.highchart.dao;

import java.util.List;

public interface ChartDao {

	public <T> List<T> getCompletion(Class<T> clazz, String time, String type, String value);
}
