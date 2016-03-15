package it.by.library.dao;

import java.util.List;

import it.by.library.dao.exception.DaoException;
import it.by.library.entity.Books;

public interface IBookDao extends Dao<Books> {

	List<Books> getAll() throws DaoException;

	List<Books> getByGenre(Long id) throws DaoException;

	List<Books> list(Integer offset, Integer maxResults);

	Long count();
}
