package common;

public interface Processor<T, E> {
	public E process(T param, LogMetaData lmd);
}
