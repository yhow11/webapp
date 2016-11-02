package common.mapper;

import common.CommonInterface;

public interface Mapper<T, E> extends CommonInterface<T>{

	public T marshall(E source) throws Exception;
	public E unmarshall(T source) throws Exception;
	public T marshall(E source, T target) throws Exception;
	public E unmarshall(T source, E target) throws Exception;
	
	
}
