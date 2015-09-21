package com.epam.te.hotel.service.admin;

import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IUserDAO;
import com.epam.te.hotel.model.dao.sql.UserDAO;
import com.epam.te.hotel.model.entity.Role;
import com.epam.te.hotel.service.ServiceException;

/**
 * Contains all logic associated with changing user role.
 */
public class ChangeUserRoleService {
	private static final String EMPTY_STATUS = "empty";
	
	/**
	 * Changed user role by id.
	 * @param id_user
	 * @param role
	 * @throws ServiceException
	 */
	public static void changeRole(String id_user, String role) throws ServiceException{
		IUserDAO userDAO = new UserDAO();
		if (id_user != null) {
			if (EMPTY_STATUS.equals(role)) {
				throw new ServiceException(ConfigManager.getInstance().getProperty(
										ConfigManager.CHOOSE_ACTION_ERROR_MESSAGE));
			}
			try {				
				userDAO.changeUserRoleById(Integer.parseInt(id_user), Role.valueOf(role).getIdByRole());
			} catch (NumberFormatException e) {
				throw new ServiceException(e.getMessage(), e);
			} catch (DAOException e) {
				throw new ServiceException(e.getMessage(), e);
			}
		} else {
			throw new ServiceException(ConfigManager.getInstance().getProperty(
									ConfigManager.CHANGE_ROLE_ERROR_MESSAGE));
		}
	}
}

