package common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JAnnotationUtils {

	public static List<Field> findFields(Class<?> classs, Class<? extends Annotation> ann,
			Class<? extends Annotation>... exclude) {
		List<Field> fields = new ArrayList<>();
		Class<?> c = classs;
		while (c != null) {
			for (Field field : c.getDeclaredFields()) {
				boolean add = true;
				if (exclude != null) {
					for (Class<? extends Annotation> ex : exclude) {
						if (field.isAnnotationPresent(ex)) {
							add = false;
						}
					}
				}
				if (field.isAnnotationPresent(ann) && add) {
					fields.add(field);
				}
			}
			c = c.getSuperclass();
		}
		return fields;
	}
}
