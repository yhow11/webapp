package common.orm.query;

public interface QueryOneByID<T> {
	public T get(String id) throws Exception;
}
