package it.by.library.services;

import java.util.List;

import it.by.library.entity.Journal;
import it.by.library.entity.Users;
import it.by.library.services.exception.ServiceException;

public interface IJournalService extends Services<Journal> {
	List<Journal> getAll() throws ServiceException;

	List<Journal> findByUser(Users user) throws ServiceException;

	List<Journal> findByTime() throws ServiceException;

	List<Journal> findByTimeReturn() throws ServiceException;

	void updateById(Long id) throws ServiceException;

	boolean findByGenre(Long id) throws ServiceException;

	boolean findByBook(Long id) throws ServiceException;
}
