package service.membermanagement.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class MemberService<T, E> {
	public abstract List<T> getAll(E e);
	public abstract List<T> get(E e);
	public abstract T getOne(E e);
	public abstract T save(E e);
}
