package com.fingerprint.event.mapper;

public interface JMapper<T, E> {

   public E map(T t) throws Exception ;
   public T unmap(E e) throws Exception ;
}
