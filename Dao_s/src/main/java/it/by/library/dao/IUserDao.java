package it.by.library.dao;

import java.util.List;

import it.by.library.dao.exception.DaoException;
import it.by.library.entity.Users;

public interface IUserDao extends Dao<Users> {

	List<Users> getUser(Users t) throws DaoException;

	Users getUserByName(String userName) throws DaoException;

}
