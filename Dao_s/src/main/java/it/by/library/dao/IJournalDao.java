package it.by.library.dao;

import java.util.List;

import it.by.library.dao.exception.DaoException;
import it.by.library.entity.Books;
import it.by.library.entity.Journal;
import it.by.library.entity.Users;

public interface IJournalDao extends Dao<Journal> {
	List<Journal> getAll() throws DaoException;

	List<Journal> getByUser(Users user) throws DaoException;

	List<Journal> getByTime() throws DaoException;

	List<Journal> getByTimeReturn() throws DaoException;

	void updateById(Long id) throws DaoException;

	List<Journal> getByBook(Books book) throws DaoException;

}
