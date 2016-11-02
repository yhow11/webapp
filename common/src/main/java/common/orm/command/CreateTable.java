package common.orm.command;

public interface CreateTable<T> {
	public void create(Class<T> clazz) throws Exception;
}
