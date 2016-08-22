package usertracker.browser.mapper;

public interface VisitorLogMapper<T, E> {

   public E map(T t) throws Exception ;
   public T unmap(E e) throws Exception ;
}
