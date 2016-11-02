package common.orm.command;

public interface TService<T, E> {
	public T query();
	public E command();
}
