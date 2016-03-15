package it.by.library.services;

import java.util.List;

import it.by.library.entity.Books;
import it.by.library.services.exception.ServiceException;

public interface IBookService extends Services<Books> {
	List<Books> findAll() throws ServiceException;

	List<Books> list(Integer offset, Integer maxResults);

	Long count();

}
