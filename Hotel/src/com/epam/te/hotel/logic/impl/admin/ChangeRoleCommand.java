package com.epam.te.hotel.logic.impl.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.User;
import com.epam.te.hotel.service.admin.ChangeUserRoleService;
import com.epam.te.hotel.service.user.FindUserService;
import com.epam.te.hotel.service.ServiceException;
/**
 * Command for changing user role (access level). 
 * <br/>
 * Command that is allowed for execution for admins only.
 */
public class ChangeRoleCommand implements ICommand {
	
	private static final String USER_LIST = "userList";
	private static final String USER_ID = "id_user";
	private static final String USER_ROLE = "role";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String ACTION_MESSAGE = "actionMessage";	
	
	/**
	 * Execute command for changing user role (access level).
	 * Calls class ChangeUserRoleService.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;

		String id_user = request.getParameter(USER_ID);
		String role = request.getParameter(USER_ROLE);
		try {
			ChangeUserRoleService.changeRole(id_user, role);
			page = refreshWithChanges(request);
			request.setAttribute(ACTION_MESSAGE,ConfigManager.getInstance().getProperty(
							ConfigManager.CHANGE_ROLE_SUCCESS_MESSAGE));
		}catch (ServiceException e) {
			request.setAttribute(ERROR_MESSAGE, ConfigManager.getInstance().getProperty(
					ConfigManager.CHANGE_ROLE_ERROR_MESSAGE));
			page = refreshWithChanges(request);
			throw new CommandException(e.getMessage(), e);
		}
		return page;
	}
	
	private String refreshWithChanges(HttpServletRequest request) {
		ArrayList<User> userList;
		String page = null;
		try {
			userList = FindUserService.findAllUser();
			request.setAttribute(USER_LIST, userList);
			page = JspPageName.USER_ADMINISTRATION;
			return page;
		} catch (ServiceException e) {
			request.setAttribute(ERROR_MESSAGE,	ConfigManager.getInstance().getProperty(
							ConfigManager.SERVICE_EXCEPTION_ERROR_MESSAGE));
			page = JspPageName.ERROR;
			return page;			
		}
	}
}
