package common;

public interface VoidProcessor<T> {
	public void process(T param, LogMetaData lmd) throws Exception;
}
