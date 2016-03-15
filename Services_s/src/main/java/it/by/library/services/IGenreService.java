package it.by.library.services;

import java.util.List;

import it.by.library.entity.Genres;
import it.by.library.services.exception.ServiceException;

public interface IGenreService extends Services<Genres> {
	Genres getByGenre(String name) throws ServiceException;

	List<Genres> getAll() throws ServiceException;

}
