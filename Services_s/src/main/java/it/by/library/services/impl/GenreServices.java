package it.by.library.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.by.library.dao.IBookDao;
import it.by.library.dao.IGenreDao;
import it.by.library.dao.exception.DaoException;
import it.by.library.entity.Genres;
import it.by.library.services.BaseService;
import it.by.library.services.IGenreService;
import it.by.library.services.exception.ServiceException;

/**
 *  service methods class for genres
 * @author NotePad.by
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class GenreServices extends BaseService<Genres> implements IGenreService {
	private static Logger log = Logger.getLogger(GenreServices.class);

	@Autowired
	IGenreDao genreDao;

	@Autowired
	IBookDao bookDao;

	/**
	 * Selects genre by name of a genre
	 * 
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public Genres getByGenre(String name) throws ServiceException {
		Genres genre;
		try {
			genre = (Genres) genreDao.getByGenre(name);
		} catch (DaoException e) {
			log.error(e + "Error in GenreServices/ getByGenre");
			throw new ServiceException(e.getMessage(), e);
		}
		return genre;
	}

	/**
	 * selects all genres from database
	 * 
	 * @return
	 * @throws DaoException
	 * @throws ServiceException
	 */
	@Override
	public List<Genres> getAll() throws ServiceException {
		List<Genres> list;
		try {

			list = genreDao.getAll();

		} catch (DaoException e) {
			log.error(e + "Error in GenreServices/ getAll");
			throw new ServiceException(e.getMessage(), e);
		}
		return list;
	}

}
