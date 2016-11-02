package common.orm.command;

public interface SaveCommand<T> {
	public T execute(T model) throws Exception;
}
