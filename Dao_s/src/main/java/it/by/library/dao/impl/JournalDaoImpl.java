package it.by.library.dao.impl;

import java.sql.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import it.by.library.dao.BaseDao;
import it.by.library.dao.IJournalDao;
import it.by.library.dao.exception.DaoException;
import it.by.library.entity.Books;
import it.by.library.entity.Journal;
import it.by.library.entity.Users;
/**
 * The class extends the standard DAO methods for journal. 
 * It adds methods for extracting a list of all  positions from journal, 
 * a list of all positions from journal by name of user,
 * a list of  positions where books weren't returned,
 * a list of  positions where books were returned,
 * a list of all positions from journal by book
 * 
 
 * @author Ilya
 *
 */
@Repository()
public class JournalDaoImpl extends BaseDao<Journal> implements IJournalDao {
	private static Logger log = Logger.getLogger(JournalDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * select all positions from journal
	 * 
	 * @return List<Journal>
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Journal> getAll() throws DaoException {
		List<Journal> list;
		try {
			String hql = "FROM Journal";
			Query query = getSession().createQuery(hql);
			list = query.list();
		} catch (HibernateException e) {
			log.error("error was thrown in DAO: " + e);
			throw new DaoException(e);
		}
		return list;
	}

	/**
	 * finds positions by user
	 * 
	 * @return List<Journal>
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Journal> getByUser(Users user) throws DaoException {
		List<Journal> list;
		try {
			String hql = "Select J FROM Journal J where users =:user";
			Query query = getSession().createQuery(hql);
			query.setParameter("user", user);
			list = query.list();
		} catch (HibernateException e) {
			log.error("error was thrown in DAO: " + e);
			throw new DaoException(e);
		}
		return list;
	}

	/**
	 * Select positions where books weren't returned
	 * 
	 * @param
	 * @return List<Journal>
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Journal> getByTime() throws DaoException {
		List<Journal> list;
		try {
			Criteria crit = getSession().createCriteria(Journal.class);
			crit.add(Restrictions.isNull("date_time_return"));
			crit.addOrder(Order.desc("users"));
			list = crit.list();
		} catch (HibernateException e) {
			log.error("error was thrown in DAO: " + e);
			throw new DaoException(e);
		}
		return list;
	}

	/**
	 * Select positions where books were returned
	 * 
	 * @return List<Journal>
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Journal> getByTimeReturn() throws DaoException {
		List<Journal> list;
		try {
			Criteria crit = getSession().createCriteria(Journal.class);
			crit.add(Restrictions.isNotNull("date_time_return"));
			crit.addOrder(Order.desc("users"));
			list = crit.list();
		} catch (HibernateException e) {
			log.error("error was thrown in DAO: " + e);
			throw new DaoException(e);
		}

		return list;
	}

	/**
	 * sets current date of returned book
	 * 
	 * @param id
	 * @throws DaoException
	 */
	@Override
	public void updateById(Long id) throws DaoException {
		long curTime = System.currentTimeMillis();
		Date curDate = new Date(curTime);
		try {
			String hql = "update Journal J set J.date_time_return=:time where J.id =:id";
			Query query = getSession().createQuery(hql);
			query.setParameter("time", curDate);
			query.setParameter("id", id);
			@SuppressWarnings("unused")
			int result = query.executeUpdate();
		} catch (HibernateException e) {
			log.error("error was thrown in DAO: " + e);
			throw new DaoException(e);
		}
	}

	/**
	 * Select position from Journal by book
	 * 
	 * @param book
	 * @return List<Journal>
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Journal> getByBook(Books book) throws DaoException {
		List<Journal> list;
		try {
			String hql = "Select J FROM Journal J where books =:books";
			Query query = getSession().createQuery(hql);
			query.setParameter("books", book);
			list = query.list();
		} catch (HibernateException e) {
			log.error("error was thrown in DAO: " + e);
			throw new DaoException(e);
		}
		return list;
	}
}