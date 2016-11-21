package common.orm.query;

public interface Save<T> {
	public T save(T model) throws Exception;
}
