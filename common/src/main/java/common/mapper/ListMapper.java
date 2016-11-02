package common.mapper;

import java.util.List;
import java.util.stream.Collectors;

import common.ObjectUtil;
import common.orm.query.param.Param;

public interface ListMapper<T, E> extends Mapper<T, E>{

	public Class<T> marshallClass();
	public Class<E> unmarshallClass();
	public Param<T> param();
	default public List<T> marshall(List<E> source) throws Exception {
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
	
	default public List<E> unmarshall(List<T> source)  throws Exception{
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
	
	default Param<T> marshall(Param<E> source) throws Exception {
		Param<T> ret = param();
		ObjectUtil.isPresent(source.getLimit(), value -> ret.setLimit(value));
		ObjectUtil.isPresent(source.getOffset(), value -> ret.setOffset(value));
		ObjectUtil.isPresent(source.getModel(), value -> ret.setModel(this.marshall(source.getModel())));
		ObjectUtil.isPresent(source.getSort(), value -> ret.setSort(value));
		ObjectUtil.isPresent(source.getSortBy(), value -> ret.setSortBy(value));
		return ret;
	}

	default T marshall(E source) throws Exception {
		return marshall(source, marshallClass().newInstance());
	}
	default E unmarshall(T source) throws Exception{
		return unmarshall(source, unmarshallClass().newInstance());
	}
}
