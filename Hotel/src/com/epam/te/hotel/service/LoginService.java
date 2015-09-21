package com.epam.te.hotel.service;

import javax.servlet.http.HttpServletRequest;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IUserDAO;
import com.epam.te.hotel.model.dao.sql.UserDAO;
import com.epam.te.hotel.model.entity.Role;
import com.epam.te.hotel.model.entity.User;
/**
 * Contains all logic associated with login.
 */
public class LoginService {
	
	private static final String ERROR_MESSAGE = "errorMessage";

	/**
	 * Check login. Calls method for finding user by login and password.
	 * @param login
	 * @param password
	 * @return finded user
	 * @throws ServiceException
	 */
	public static User checkLogin(String login, String password) throws ServiceException {
		IUserDAO userDAO = new UserDAO();
		User user = null;
		try{
			user = userDAO.findUserByLoginPassword(login, password.hashCode());			
		}catch (DAOException e){
			throw new ServiceException(e.getMessage(), e);
		}
		return user;
	}

	/**
	 * Check user role (access level).
	 * Return jsp pages according user's role.
	 * @param role
	 * @param request
	 * @return page for user
	 */
	public static String checkUserRole(Role role, HttpServletRequest request) {
		Role check = role;
		String page = null;
		if (check != null) {
			switch (check) {
			case ADMIN:
				page = JspPageName.ADMIN_PAGE;
				break;
			case CLIENT:
				page = JspPageName.CLIENT_CABINET;
				break;
			case GUEST:
				request.setAttribute(
						ERROR_MESSAGE,ConfigManager.getInstance().getProperty(ConfigManager.BANNED_MESSAGE));
				page = JspPageName.INDEX;
				break;
			}
		}
		return page;
	}
}
