package common;

import java.util.List;

import common.function.JConsumer;
import common.function.JConsumerListWithReturn;
import common.function.JConsumerWithReturn;
import common.function.JFunction;


public class ObjectUtil {

	public static <T> void isPresent(T value, JConsumer<T> consumer) throws Exception{
		if(value != null && !"".equals(value)){
			consumer.accept(value);
		}
	}
	
	public static <T> void isPresent(List<T> values, JConsumer<List<T>> consumer) throws Exception{
		if(values != null && values.size() > 0){
			consumer.accept(values);
		}
	}
	public static <T, E> E isPresentWithReturnOne(List<T> values, JConsumerListWithReturn<T, E> consumerWithReturn) throws Exception{
		if(values != null && values.size() > 0){
			return consumerWithReturn.accept(values);
		}
		return null;
	}
	public static <T, E> E isPresentWithReturn(T value, JConsumerWithReturn<T, E> consumerWithReturn) throws Exception{
		if(value != null && value != ""){
			return consumerWithReturn.accept(value);
		}
		return null;
	}
	public static <T, E> E isNotPresentWithReturn(T value, JConsumerWithReturn<T, E> consumerWithReturn) throws Exception{
		if(value == null || value == ""){
			return consumerWithReturn.accept(value);
		}
		return null;
	}
	public static <T> void isNotPresent(List<T> values, JConsumer<List<T>> consumer) throws Exception{
		if(values == null ||  values.size() == 0){
			consumer.accept(values);
		}
	}
	public static <T> void isNotPresent(T value, JConsumer<T> consumer) throws Exception{
		if(value == null ||  value == ""){
			consumer.accept(value);
		}
	}
	public static <T, E> E map(T value, JFunction<T, E> consumer) throws Exception{
		if(value != null){
			return consumer.apply(value);
		}
		return null;
	}
}
