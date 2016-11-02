package common.orm.command;

public interface CommandService<T> {
	public T execute(T model) throws Exception;
}
