package com.epam.te.hotel.service.user;
import java.util.ArrayList;

import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IUserDAO;
import com.epam.te.hotel.model.dao.sql.UserDAO;
import com.epam.te.hotel.model.entity.User;
import com.epam.te.hotel.service.ServiceException;
/**
 * Contains all logic associated with finding user process.
 */
public class FindUserService {
	
	/**
	 * Finds all existing users in the system.
	 * @return list of all existing users
	 * @throws ServiceException
	 */
	public static ArrayList<User> findAllUser() throws ServiceException{
		ArrayList<User> userList = new ArrayList<User>();
		IUserDAO userDAO = new UserDAO();
		try {
			userList = userDAO.findAllUser();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return userList;
	}
	
	/**
	 * Finds user by his id.
	 * @param id_user
	 * @return finding user
	 * @throws ServiceException
	 */
	public static User findUserById(int id_user) throws ServiceException{
		User user = null;
		
		IUserDAO userDAO = new UserDAO();
		try{
			user = userDAO.findUserById(id_user);
		}catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		
		return user;
	}
}

