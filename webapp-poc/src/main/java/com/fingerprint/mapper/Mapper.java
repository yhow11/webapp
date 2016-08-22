package com.fingerprint.mapper;

import java.util.List;

public interface Mapper<T, E> {
	
	public T unmarshall(E e, T t);
	public E marshall(T t, E e);
	public T unmarshall(E e);
	public E marshall(T t);
	public List<T> unmarshall(List<E> e);
	public List<E> marshall(List<T> t);
}
