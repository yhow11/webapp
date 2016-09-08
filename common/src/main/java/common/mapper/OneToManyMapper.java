package common.mapper;

import java.util.List;

public interface OneToManyMapper<T, E> {

	public List<T> unmarshall(E param);
	public E marshall(List<T> param);
	public List<T> unmarshall(E param, List<T> target);
	public E marshall(List<T> param, E target);
}
