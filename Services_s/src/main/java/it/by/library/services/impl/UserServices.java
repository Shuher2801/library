package it.by.library.services.impl;

import java.util.List;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import it.by.library.dao.IUserDao;
import it.by.library.dao.exception.DaoException;

import it.by.library.entity.Users;
import it.by.library.services.BaseService;
import it.by.library.services.IUserService;
import it.by.library.services.exception.ServiceException;
/**
 * service methods class for users
 * @author Ilya
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServices extends BaseService<Users>  implements IUserService{
	private static Logger log = Logger.getLogger(UserServices.class);

	@Autowired
	IUserDao userDao;
	
	
	/**
	 * Gets Users from database by user
	 * 
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	public List<Users> getUsers(Users user) throws ServiceException {
		
		log.info("Get: " + user.getName());
		 List<Users> us;
		try {
			 us = userDao.getUser(user);
		} catch (DaoException e) {
			log.error(e + "Error in UserServices/getUsers");
			throw new ServiceException(e.getMessage(), e);
		}
		return us;
	}

	@Override
	public Users getUserByName(String userName) throws ServiceException {
		Users us;
		try {
			 us = userDao.getUserByName(userName);
		} catch (DaoException e) {
			log.error(e + "Error in UserServices/getUserByName");
			throw new ServiceException(e.getMessage(), e);
		}
		return us;
	}

	

}
