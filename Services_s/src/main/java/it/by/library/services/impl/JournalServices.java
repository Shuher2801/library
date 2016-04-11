package it.by.library.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import it.by.library.dao.IBookDao;
import it.by.library.dao.IGenreDao;
import it.by.library.dao.IJournalDao;
import it.by.library.dao.exception.DaoException;
import it.by.library.entity.Books;
import it.by.library.entity.Journal;
import it.by.library.entity.Users;
import it.by.library.services.BaseService;
import it.by.library.services.IJournalService;
import it.by.library.services.exception.ServiceException;

/**
 *  service methods class for journal
 * @author Ilya
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class JournalServices extends BaseService <Journal> implements IJournalService  {
	
	private static Logger log = Logger.getLogger(JournalServices.class);

	@Autowired
	IGenreDao genreDao;

	@Autowired
	IBookDao bookDao;

	@Autowired
	IJournalDao journalDao;

	/**
	 * Selects all positions from journal
	 */
	@Override
	public List<Journal> getAll() throws ServiceException {

		// it's not recommended to initialize variables by null value, because they are null by default.
		// If you don't initialize it at all IDE wiil highlight such variable 
		List<Journal> list = null;
		try {
			list = journalDao.getAll();

		} catch (DaoException e) {
			log.error(e + "Error in JournalServices/getAll()");
			throw new ServiceException(e.getMessage(), e);
		}
		log.info("Find " + list.size() + " books");
		return list;
	}

	@Override
	public List<Journal> findByUser(Users user) throws ServiceException {

		List<Journal> list = null;
		try {

			list = journalDao.getByUser(user);

		} catch (DaoException e) {
			log.error(e + "Error in JournalServices/getByUser()");
			throw new ServiceException(e.getMessage(), e);
		}
		log.error("Error in JournalServices/getByUser()/sessionClose");
		log.info("Find " + list.size() + " books");
		return list;
	}

	/**
	 * select position of Journal where books weren't returned
	 * 
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 */
	@Override
	public List<Journal> findByTime() throws ServiceException {

		List<Journal> list = null;
		try {
			list = journalDao.getByTime();

		} catch (DaoException e) {
			log.error(e + "Error in JournalServices/getByUser()");
			throw new ServiceException(e.getMessage(), e);
		}
		log.info("Find " + list.size() + " books");
		return list;
	}

	/**
	 * select position of Journal where books were returned
	 * 
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 */

	@Override
	public List<Journal> findByTimeReturn() throws ServiceException {

		List<Journal> list = null;
		try {

			list = journalDao.getByTimeReturn();

		} catch (DaoException e) {
			log.error(e + "Error in JournalServices/getByUser()");

			throw new ServiceException(e.getMessage(), e);
		}
		log.info("Find " + list.size() + " books");
		return list;
	}

	/**
	 * sets the return date of the book by id
	 * 
	 * @param id
	 * @throws ServiceException
	 * @throws DaoException
	 */
	@Override
	public void updateById(Long id) throws ServiceException {

		try {
			journalDao.updateById(id);
		} catch (DaoException e) {
			log.error(e + "Error in JournalServices/updateById()");

			throw new ServiceException(e.getMessage(), e);
		}
		log.info("Update journal");
	}

	/**
	 * This method for deleting Genre. returns boolean value. if true, it means
	 * that Journal doesn't contains position with such Genre and it can be
	 * deleted. if value false,you can't delete Genre
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 */
	@Override
	@Transactional
	public boolean findByGenre(Long id) throws ServiceException {
		List<Books> ListBook = null;
		boolean flag = false;
		try {

			ListBook = bookDao.getByGenre(id);
			if (ListBook.size() != 0) {
				List<List<Journal>> multi = new ArrayList<>();

				for (Books bk : ListBook) {
					multi.add(journalDao.getByBook(bk));
				}
				int count = 0;

				for (List<Journal> lj : multi) {
					count += lj.size();
				}
				if (count > 0) {

					flag = false;
					return flag;
				} else

					flag = true;
				return flag;
			} else {
				flag = true;

			}
		} catch (DaoException e) {
			log.error(e + "Error in JournalServices/findByBook()");
				throw new ServiceException(e.getMessage(), e);
			}
				
		return flag;
	}

	/**
	 * This method for deleting Books. returns boolean value. if true, it means
	 * that Journal doesn't contains position with such book and it can be
	 * deleted. if value false,you can't delete book
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 */
	@Override
	@Transactional
	public boolean findByBook(Long id) throws ServiceException {
	
		List<Journal> list = null;
		boolean flag = false;
		try {
			
			Books book = (Books) bookDao.getById(id);
			list = journalDao.getByBook(book);

			if (list.size() != 0) {
				flag = false;
				return flag;
			} else
				flag = true;
		} catch (DaoException e) {
			log.error(e + "Error in JournalServices/findByBook()");
				throw new ServiceException(e.getMessage(), e);
			}
		
		return flag;
	}

}
