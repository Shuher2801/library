package it.by.library.services;


import it.by.library.services.exception.ServiceException;

public interface Services<T> {
	T getById(Long id) throws ServiceException;

	void add(T model) throws ServiceException;

	void update(T model) throws ServiceException;

	void remove(Long id) throws ServiceException;

	void remove(T model) throws ServiceException;

}
