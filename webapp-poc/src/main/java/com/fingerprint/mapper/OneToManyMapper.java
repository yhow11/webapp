package com.fingerprint.mapper;

import java.util.List;

public interface OneToManyMapper<T, E> {
	
	public List<T> unmarshall(E e);
	public E marshall(List<T> t);
	public List<T> unmarshall(E e, List<T> t);
	public E marshall(List<T> t, E e);
}
