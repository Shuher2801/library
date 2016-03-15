package it.by.library.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import it.by.library.dao.BaseDao;
import it.by.library.dao.IBookDao;
import it.by.library.dao.exception.DaoException;
import it.by.library.entity.Books;

@Repository()
public class BookDaoImpl extends BaseDao<Books> implements IBookDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	private static Logger log = Logger.getLogger(BookDaoImpl.class);

	/**
	 * selects all books from database
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Books> getAll() throws DaoException {

		List<Books> results = null;
		try {
			String hql = "FROM Books";
			Query query = null;
			query = getSession().createQuery(hql);
			results = query.list();

		} catch (HibernateException e) {
			log.error("error was thrown in DAO: " + e);
			throw new DaoException(e);
		}
		return results;

	}

	/**
	 * selects all books from database by genre
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Books> getByGenre(Long id) throws DaoException {
		log.info("Delete class by id:" + id);
		Books t = null;
		List<Books> list = null;
		try {
			String hql = "Select b from Books b  where genres.id=:id";
			Query query = getSession().createQuery(hql);
			query.setParameter("id", id);
			list = query.list();
			log.info("delete:" + t);
		} catch (HibernateException e) {
			log.error("Error delete in Dao" + e);
			throw new DaoException(e);
		}
		return list;
	}

	/**
	 * method for pagination which sets FirstResult and MaxResults on the page
	 * 
	 * @param offset
	 * @param maxResults
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Books> list(Integer offset, Integer maxResults) {
		return getSession().createCriteria(Books.class)
				.setFirstResult(offset != null ? offset : 0)
				.setMaxResults(maxResults != null ? maxResults : 10)
				.list();
	}

	/**
	 * method for pagination which returns the number of rows
	 * 
	 * @return
	 */
	@Override
	public Long count() {
		return (Long) getSession().createCriteria(Books.class).setProjection(Projections.rowCount()).uniqueResult();
	}

}
