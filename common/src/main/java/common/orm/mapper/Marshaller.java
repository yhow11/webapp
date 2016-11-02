package common.orm.mapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Marshaller<T, E> {

	
	public List<T> marshall(List<E> source) throws Exception {
		// TODO Auto-generated method stub
		return source.stream().map(form -> {
			try {
				return marshall(form);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
	}
	
	public abstract T marshall(E source) throws Exception;
	
	
}
