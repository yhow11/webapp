package common.mapper;

public interface Mapper<T, E> {

	public T marshal(E e) throws Exception;
	public E unmarshal(T t) throws Exception;
	
}
