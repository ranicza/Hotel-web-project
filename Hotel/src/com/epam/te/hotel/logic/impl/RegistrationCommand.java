package com.epam.te.hotel.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.Role;
import com.epam.te.hotel.model.entity.User;
import com.epam.te.hotel.service.RegistrationService;
import com.epam.te.hotel.service.ServiceException;

/**
 * Command for registration. 
 * <br/>
 * Everyone allowed to login.
 */
public class RegistrationCommand implements ICommand{

	private static final Logger LOG = Logger.getLogger(RegistrationCommand.class);
	
	private static final String USER = "user";	
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String RE_PASSWORD = "password_repeat";
	private static final String EMAIL = "email";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String PATRONYMIC = "patronymic";
	private static final String PASSPORT = "passport";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String ACTION_MESSAGE = "actionMessage";
	private static final String LOG_SUCCESS_MESSAGE = "New user was registrated. Login: ";
	private static final String LOG_ERROR_MESSAGE = "Something goes wrong with registration.";
			
	
	/**
	 * Execute command for registration.
	 * Calls class RegistrationService.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException{
		HttpSession session = request.getSession(true);
		
		String page = null;
		User user = new User();		
		String password = request.getParameter(PASSWORD.trim());
		String password_repeated = request.getParameter(RE_PASSWORD.trim());		
		user.setLogin(request.getParameter(LOGIN).trim());
		user.setPassword(password.hashCode());
		user.setEmail(request.getParameter(EMAIL).trim());
		user.setName(request.getParameter(NAME).trim());
		user.setSurname(request.getParameter(SURNAME).trim());
		user.setPatronymic(request.getParameter(PATRONYMIC).trim());
		user.setPassport(request.getParameter(PASSPORT).trim());
		user.setRole(Role.CLIENT);
		
		try{
			RegistrationService.registration(user, password, password_repeated);
			session.setAttribute(USER, user);
			request.setAttribute(USER, user);			
			LOG.info(LOG_SUCCESS_MESSAGE + user.getLogin());
			page = JspPageName.CLIENT_CABINET;			
			request.setAttribute(ACTION_MESSAGE, ConfigManager.getInstance().getProperty(ConfigManager.REGISTRATION_WAS_SUCCESSFUL_MESSAGE));
		}catch (ServiceException e){
			LOG.error(LOG_ERROR_MESSAGE, e);
			request.setAttribute(ERROR_MESSAGE,	ConfigManager.getInstance().getProperty(ConfigManager.REGISTRATION_ERROR_MESSAGE));
			page = JspPageName.REGISTRATION;
			throw new CommandException(e.getMessage(), e);
		}
		return page;		
	}

}
