package it.by.library.services;

import java.util.List;
import it.by.library.entity.Users;
import it.by.library.services.exception.ServiceException;

public interface IUserService extends Services<Users> {
	List<Users> getUsers(Users t) throws ServiceException;

	Users getUserByName(String userName) throws ServiceException;
}
