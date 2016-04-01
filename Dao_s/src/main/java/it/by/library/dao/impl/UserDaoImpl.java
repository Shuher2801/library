package it.by.library.dao.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import it.by.library.dao.BaseDao;
import it.by.library.dao.IUserDao;
import it.by.library.dao.exception.DaoException;
import it.by.library.entity.Users;
/**
 * The class extends the standard DAO methods for user. 
 * @author Ilya
 *
 */
@Repository()
public class UserDaoImpl extends BaseDao<Users> implements IUserDao {
	
	private static Logger log = Logger.getLogger(UserDaoImpl.class);
	
	@SuppressWarnings("unused")
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Gets Users from database by user
	 * 
	 * @param user
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getUser(Users user) throws DaoException, HibernateException {
		String userName = user.getName();
		String userPassword = user.getPassword();

		Criteria criteria = getSession().createCriteria(Users.class);
		criteria.add(Restrictions.eq("name", userName));
		criteria.add(Restrictions.eq("password", userPassword));
		@SuppressWarnings("rawtypes")
		List result = criteria.list();

		return result;

	}
	/**
	 * Selects user by name of user
	 */
	public Users getUserByName(String userName) throws DaoException {
		Users user=null;
		try {
		Criteria criteria = getSession().createCriteria(Users.class);
		criteria.add(Restrictions.eq("name", userName));
		user = (Users) criteria.uniqueResult();
		} catch (HibernateException e) {
			log.error("error was thrown in userDAO: " + e);
			throw new DaoException(e);
		}
		return user;

	}
	
}
