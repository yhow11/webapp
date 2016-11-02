package common;

import java.lang.reflect.ParameterizedType;

public interface CommonInterface<T> {

	default Class<T> getParameterizedType(int index){
		return (Class<T>)
		   ((ParameterizedType)this.getClass().getGenericSuperclass())
		      .getActualTypeArguments()[index];
	}
}
