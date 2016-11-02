package common.orm.query;

public interface Queue<T> {
	public void send(T model) throws Exception;
}
