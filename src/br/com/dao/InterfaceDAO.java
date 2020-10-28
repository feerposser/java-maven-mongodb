package br.com.dao;

import java.util.List;

public interface InterfaceDAO <T> {
	
	public boolean insert(T t); // C
	
	// R
	public T findById(String id);
	public List<T> findAll();
	
	public boolean update(T t); 	// U
	
	public boolean delete(T t); // D
	
}
