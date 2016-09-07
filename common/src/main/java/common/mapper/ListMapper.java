package common.mapper;

import java.util.List;

public interface ListMapper<T, E> extends Mapper<T, E>{

	public List<T> marshal(List<E> e) throws Exception;
	public List<E> unmarshal(List<T> t) throws Exception;
	
}
