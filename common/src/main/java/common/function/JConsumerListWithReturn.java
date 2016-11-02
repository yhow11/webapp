package common.function;

import java.util.List;

@FunctionalInterface
public interface JConsumerListWithReturn<T, E> {
	 E accept(List<T> t) throws Exception;
}
