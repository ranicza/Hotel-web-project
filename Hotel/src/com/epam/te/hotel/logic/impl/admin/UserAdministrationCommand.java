package com.epam.te.hotel.logic.impl.admin;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.User;
import com.epam.te.hotel.service.user.FindUserService;
import com.epam.te.hotel.service.ServiceException;
/**
 * Command for showing information list about all users.
 * <br/>
 * Admin command that is allowed for execution for admins only.
 */
public class UserAdministrationCommand implements ICommand{
	private static final Logger LOG = Logger.getLogger(UserAdministrationCommand.class);
	
	private static final String USER_LIST = "userList";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String LOG_ERROR_MESSAGE = "Something goes wrong, redirect to error page.";
	
	/**
	 * Execute command for showing information list about all users.
	 * Calls class FindUserService.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;
		try {
			ArrayList<User> userList = FindUserService.findAllUser();
			request.setAttribute(USER_LIST, userList);
			page = JspPageName.USER_ADMINISTRATION;
		} catch (ServiceException e) {
			LOG.error(LOG_ERROR_MESSAGE, e);
			request.setAttribute(ERROR_MESSAGE,	ConfigManager.getInstance().getProperty(
							ConfigManager.SERVICE_EXCEPTION_ERROR_MESSAGE));
			page = JspPageName.ERROR;
			throw new CommandException(e.getMessage(), e);
		}
		return page;
	}
}

