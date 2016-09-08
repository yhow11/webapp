package common.mapper;

public interface Mapper<T, E> {

	public T marshall(E param) throws Exception;
	public E unmarshall(T param) throws Exception;
	public T marshall(E e, T target) throws Exception;
	public E unmarshall(T t, E target) throws Exception;
}
