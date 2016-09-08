package common.mapper;

import java.util.List;

public interface ListMapper<T, E> extends Mapper<T, E>{

	public List<T> marshall(List<E> e) throws Exception;
	public List<E> unmarshall(List<T> t) throws Exception;
	
}
