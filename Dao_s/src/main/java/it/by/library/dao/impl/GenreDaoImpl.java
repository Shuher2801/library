package it.by.library.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.by.library.dao.BaseDao;
import it.by.library.dao.IGenreDao;
import it.by.library.dao.exception.DaoException;
import it.by.library.entity.Genres;
/**
 * The class extends the standard DAO methods for genres. 
 * It adds methods for extracting a list of all genres, 
 * a list of genres by name of a genre
 * @author Ilya
 *
 */
@Repository()
public class GenreDaoImpl extends BaseDao<Genres> implements IGenreDao {

	private static Logger log = Logger.getLogger(GenreDaoImpl.class);
	
	public  GenreDaoImpl(){}

	@Autowired
	private SessionFactory sessionFactory;
	

	/**
	 * Select list of genres from database
	 * @return List<Genres>
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Genres> getAll() throws DaoException {
		List<Genres> list = null;
		try {
			String hql = "FROM Genres";
			Query query = getSession().createQuery(hql);
			list = query.list();
		} catch (HibernateException e) {
			log.error("error was thrown in DAO: " + e);
			throw new DaoException(e);
		}
		return list;
	}

	/**
	 * Selects genre by name of a genre
	 * 
	 * @param name
	 * @return Genres genre
	 * @throws DaoException
	 */
	@Override
	public Genres getByGenre(String name) throws DaoException {

		String hql = "FROM Genres g where g.genre=:genre";
		Genres genre = null;
		try {
			Query query = getSession().createQuery(hql);
			query.setParameter("genre", name);
			genre = (Genres) query.uniqueResult();
		} catch (HibernateException e) {
			log.error("error was thrown in DAO: " + e);
			throw new DaoException(e);
		}
		return genre;

	}
}
