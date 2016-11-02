package common.function;

@FunctionalInterface
public interface JConsumerWithReturn<T, E> {
	 E accept(T t) throws Exception;
}
