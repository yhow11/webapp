package common.function;

@FunctionalInterface
public interface JConsumer<T> {
	 void accept(T t) throws Exception;
}
