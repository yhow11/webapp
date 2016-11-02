package common.orm.command;

public interface RemoveCommand<T> {
	public void execute(T model) throws Exception;
}
