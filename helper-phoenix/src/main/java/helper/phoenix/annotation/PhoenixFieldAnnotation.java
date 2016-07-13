package helper.phoenix.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) //can use in method only.
public @interface PhoenixFieldAnnotation {
	
	public String type() default "";
	public boolean nullable() default true;
	public boolean primary() default false;
}