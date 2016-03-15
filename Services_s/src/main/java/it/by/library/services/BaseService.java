package it.by.library.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.by.library.dao.Dao;
import it.by.library.dao.exception.DaoException;
import it.by.library.services.exception.ServiceException;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BaseService<T> implements Services<T> {

	private static Logger log = Logger.getLogger(BaseService.class);
	@Autowired
	private Dao<T> baseDao;

	public BaseService() {
	}


	@Override
	public T getById(Long id) throws ServiceException {
		T t = null;
		try {
			t = baseDao.getById(id);
		} catch (DaoException e) {
			log.error(e + "Error in BaseService/getById()");
			throw new ServiceException(e.getMessage(), e);
		}
		return t;
	}

	@Override
	public void add(T model) throws ServiceException {
		if (model != null) {
			try {
				baseDao.add(model);
			} catch (DaoException e) {
				log.error(e + "Error in BaseService/add()");
				throw new ServiceException(e.getMessage(), e);
			}
		}
	}

	@Override
	public void update(T model) throws ServiceException {
		if (model != null) {
			try {
				baseDao.update(model);
			} catch (DaoException e) {
				log.error(e + "Error in BaseService/update()");
				throw new ServiceException(e.getMessage(), e);
			}
		}
	}

	@Override
	public void remove(Long id) throws ServiceException {
		if (id != null) {
			try {
				baseDao.remove(id);
			} catch (DaoException e) {
				log.error(e + "Error in BaseService/remove(id)");
				throw new ServiceException(e.getMessage(), e);
			}
		}
	}

	@Override
	public void remove(T model) throws ServiceException {
		if (model != null) {
			try {
				baseDao.remove(model);
			} catch (DaoException e) {
				log.error(e + "Error in BaseService/remove(model)");
				throw new ServiceException(e.getMessage(), e);
			}
		}

	}
}
