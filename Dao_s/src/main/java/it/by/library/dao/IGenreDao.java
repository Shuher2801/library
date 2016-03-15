package it.by.library.dao;

import java.util.List;

import it.by.library.dao.exception.DaoException;
import it.by.library.entity.Genres;

public interface IGenreDao extends Dao<Genres> {
	List<Genres> getAll() throws DaoException;

	Genres getByGenre(String name) throws DaoException;

}
