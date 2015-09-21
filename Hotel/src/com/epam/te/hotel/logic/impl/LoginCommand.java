package com.epam.te.hotel.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.User;
import com.epam.te.hotel.service.LoginService;
import com.epam.te.hotel.service.ServiceException;

/**
 * Command for login. 
 * <br/>
 * Everyone allowed to login.
 */
public class LoginCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	private static final String USER = "user";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String LOG_MESSAGE = "Add user to session. Login: ";

	/**
	 * Execute command for login.
	 * Calls class LoginService.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	public String execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession(true);
		String page = null;
		User user = null;

		String login = request.getParameter(LOGIN).trim();
		String password = request.getParameter(PASSWORD).trim();
		try {
			if ((user = LoginService.checkLogin(login, password)) != null) {
				session.setAttribute(USER, user);
				LOG.info(LOG_MESSAGE + user.getLogin());
				page = LoginService.checkUserRole(user.getRole(), request);				
			} else {
				request.setAttribute(ERROR_MESSAGE,
						ConfigManager.getInstance().getProperty(ConfigManager.LOGIN_ERROR_MESSAGE));
				page = JspPageName.LOGIN;
			}
		} catch (ServiceException e) {
			throw new CommandException(e.getMessage(), e);
		}
		return page;
	}

}
