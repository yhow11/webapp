package common.orm.mapper;

import java.util.List;
import java.util.stream.Collectors;

import common.ObjectUtil;
import common.orm.query.param.Param;

public abstract class Mapper<T, E> {

	private Class<T> marshallClass;
	private Class<E> unmarshallClass;
	
	public Mapper(Class<T> marshallClass, Class<E> unmarshallClass) {
		// TODO Auto-generated constructor stub
		this.marshallClass = marshallClass;
		this.unmarshallClass = unmarshallClass;
	}
	
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
	
	public List<E> unmarshall(List<T> source)  throws Exception{
		// TODO Auto-generated method stub
		return source.stream().map(form -> {
			try {
				return unmarshall(form);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
	}
	
	public Param<T> marshall(Param<E> source, Param<T> param) throws Exception {
		Param<T> ret = param;
		ObjectUtil.isPresent(source.getLimit(), value -> ret.setLimit(value));
		ObjectUtil.isPresent(source.getOffset(), value -> ret.setOffset(value));
		ObjectUtil.isPresent(source.getModel(), value -> ret.setModel(this.marshall(source.getModel())));
		ObjectUtil.isPresent(source.getSort(), value -> ret.setSort(value));
		ObjectUtil.isPresent(source.getSortBy(), value -> ret.setSortBy(value));
		ObjectUtil.isPresent(source.getConditions(), value -> ret.setConditions(value));
		return ret;
	}
	public abstract T marshall(E source,  T target) throws Exception;
	public abstract E unmarshall(T source, E target) throws Exception;
	
	public T marshall(E source) throws Exception {
		return marshall(source, marshallClass.newInstance());
	}
	public E unmarshall(T source) throws Exception{
		return unmarshall(source, unmarshallClass.newInstance());
	} 
	
	
}
