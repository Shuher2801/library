package it.by.library.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import it.by.library.dao.IBookDao;
import it.by.library.dao.exception.DaoException;
import it.by.library.entity.Books;
import it.by.library.services.BaseService;
import it.by.library.services.IBookService;
import it.by.library.services.exception.ServiceException;
/**
 *  service methods class for books
 * @author Ilya
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BookServices extends BaseService<Books> implements IBookService {

	private static Logger log = Logger.getLogger(BookServices.class);

	@Autowired
	IBookDao bookDao;

	/**
	 * Selects all books from database
	 */
	@Override
	public List<Books> findAll() throws ServiceException {

		List<Books> list = null;
		try {

			list = bookDao.getAll();

		} catch (DaoException e) {
			log.error(e + "Error in BookServices/findAll()");
			throw new ServiceException(e.getMessage(), e);
		}
		log.info("Find " + list.size() + " books");
		return list;
	}

	

	/**

	/**
	 * method for pagination which sets FirstResult and MaxResults on the page
	 * 
	 * @param offset
	 * @param maxResults
	 * @return List<Books>
	 */
	@Override
	public List<Books> list(Integer offset, Integer maxResults) {

		return bookDao.list(offset, maxResults);
	}

	/**
	 * method for pagination which returns the number of rows
	 * 
	 * @return
	 */
	@Override
	public Long count() {
		return bookDao.count();
	}

}
