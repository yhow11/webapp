package com.inctool.common.utilities;

import java.lang.reflect.Field;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.inctool.common.annotation.MapUtilField;
import com.inctool.common.enums.WorshipServiceEnum;
import com.inctool.management.enums.MemberEnum;
import com.inctool.management.form.MemberForm;
import com.inctool.management.model.MemberModel;
import com.inctool.management.util.AttendanceUtil;

public class MapUtil {

	public static <T> T map(Class<T> clazz, Object object) throws Exception {
		T newInstance = clazz.newInstance();
		newInstance = map(newInstance, object, clazz.getDeclaredFields());
		newInstance = map(newInstance, object, clazz.getSuperclass().getDeclaredFields());
		return newInstance;
	}
	public static <T> T map(T newInstance, Object object, Field[] fields) throws Exception {
		
		for(Field field : fields){
			if(field.isAnnotationPresent(MapUtilField.class)){
				String name = field.getAnnotation(MapUtilField.class).name();
				if(PropertyUtils.getProperty(object, name) != null) {
					if (field.getType().equals(Integer.class)) {
						if(PropertyUtils.getPropertyType(object, name).equals(Integer.class)){
							PropertyUtils.setProperty(newInstance, name,
									PropertyUtils.getProperty(object, name));
						} else if(PropertyUtils.getPropertyType(object, name) == String.class){
							PropertyUtils.setProperty(newInstance, name,
									Integer.parseInt((String) PropertyUtils.getProperty(object, name)));
						}
						
			         } else if (field.getType().equals(String.class)){
			        	if(PropertyUtils.getPropertyType(object, name).equals(Integer.class)){
							PropertyUtils.setProperty(newInstance, name,
									String.valueOf(PropertyUtils.getProperty(object, name)));
						} else if(PropertyUtils.getPropertyType(object, name) == String.class){
							PropertyUtils.setProperty(newInstance, name,
									PropertyUtils.getProperty(object, name));
						}  else if(PropertyUtils.getPropertyType(object, name) == Date.class){
							PropertyUtils.setProperty(newInstance, name,
									DateFormatUtils.format((Date)PropertyUtils.getProperty(object, name), AttendanceUtil.SIMPLE_DATE_PATTERN));
						}   else if(PropertyUtils.getPropertyType(object, name) == MemberEnum.class){
							PropertyUtils.setProperty(newInstance, name,
									((MemberEnum)PropertyUtils.getProperty(object, name)).toString());
						}   else if(PropertyUtils.getPropertyType(object, name) == WorshipServiceEnum.class){
							PropertyUtils.setProperty(newInstance, name,
									((WorshipServiceEnum)PropertyUtils.getProperty(object, name)).toString());
						}
			         } else if (field.getType().equals(Date.class)){
				        	if(PropertyUtils.getPropertyType(object, name).equals(String.class)){
								PropertyUtils.setProperty(newInstance, name,
										DateUtils.parseDate((String) PropertyUtils.getProperty(object, name), AttendanceUtil.SIMPLE_DATE_PATTERN));
							} else {
								PropertyUtils.setProperty(newInstance, name,
										PropertyUtils.getProperty(object, name));
							} 
			         } else if (field.getType().equals(MemberEnum.class)){
				        	if(PropertyUtils.getPropertyType(object, name).equals(String.class)){
								PropertyUtils.setProperty(newInstance, name,
										MemberEnum.valueOf((String) PropertyUtils.getProperty(object, name)));
							} else {
								PropertyUtils.setProperty(newInstance, name,
										PropertyUtils.getProperty(object, name));
							} 
			         } else if (field.getType().equals(WorshipServiceEnum.class)){
				        	if(PropertyUtils.getPropertyType(object, name).equals(String.class)){
								PropertyUtils.setProperty(newInstance, name,
										WorshipServiceEnum.valueOf((String) PropertyUtils.getProperty(object, name)));
							} else {
								PropertyUtils.setProperty(newInstance, name,
										PropertyUtils.getProperty(object, name));
							} 
			         }
					
	        	}
				
			}
		}
		return newInstance;
	}
	
	public static void main(String[] args)throws Exception{
//		MemberForm form  = new MemberForm();
//		form.setId("wqeqwewq");
//		form.setAbsent("5");
//		form.setStatus(MemberEnum.GUEST.toString());
//		form.setCreatedDate(DateFormatUtils.format(new Date(), AttendanceUtil.SIMPLE_DATE_PATTERN));
//		MemberModel model = MapUtil.map(MemberModel.class, form);
//		System.out.println(model.getAbsent());
//		System.out.println(model.getCreatedDate());
//		System.out.println(model.getStatus().toString());
//		
//		
//		model.setAbsent(10);
//		model.setCreatedDate(new Date());
//		model.setStatus(MemberEnum.DK);
//		
//		form  = MapUtil.map(MemberForm.class, model);
//		
//		System.out.println(form.getAbsent());
//		System.out.println(form.getCreatedDate());
//		System.out.println(form.getStatus());
	}
}
