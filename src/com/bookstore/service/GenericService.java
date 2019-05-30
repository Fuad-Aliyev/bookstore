package com.bookstore.service;

import java.util.List;

public interface GenericService<T> {
	
	public T create(T t);
	
	public int update(T t);
	
	public T get(int id);
	
	public int delete(int id);
	
	public List<T> listAll();
	
	public long count();
	
	public int findByEmail(String email);
}
