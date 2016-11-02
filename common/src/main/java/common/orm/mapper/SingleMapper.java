package common.orm.mapper;

import java.util.List;
import java.util.stream.Collectors;

import common.ObjectUtil;
import common.orm.query.param.Param;

public abstract class SingleMapper<T, E> {

	private Class<T> marshallClass;
	private Class<E> unmarshallClass;
	private Param<T> param;
	
	public SingleMapper(Class<T> marshallClass, Class<E> unmarshallClass, Param<T> param) {
		// TODO Auto-generated constructor stub
		this.marshallClass = marshallClass;
		this.unmarshallClass = unmarshallClass;
		this.param = param;
	}
	
	public abstract T marshall(E source,  T target) throws Exception;
	public abstract List<E> unmarshall(T source, List<E> target) throws Exception;
	
	public abstract T marshall(List<E> source) throws Exception;
	public abstract List<E> unmarshall(T source) throws Exception;
	
	
}
