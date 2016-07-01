package helper.mongodb.util.mapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import com.mongodb.DBObject;

public class DBObjectMapper {

	public static <T> T map(Class<T> clazz, DBObject object) throws Exception {
		T t  = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field: fields){
			if(field.isAnnotationPresent(org.springframework.data.mongodb.core.mapping.Field.class)){
				String name = field.getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class).value();
				try{
					PropertyUtils.setProperty(t, name, object.get(name));
				} catch (Exception e) {
					System.out.println("No value of"+name);
				}
			}
		}
		return t;
	}
	
	public static <T> List<T> map(Class<T> clazz, Iterable<DBObject> objects) throws Exception {
		List<T> results = new ArrayList<T>();
		for(DBObject object: objects) {
			results.add(map(clazz, object));
		}
		return results;
	}
}
