package com.epam.te.hotel.logic.impl.user;

import javax.servlet.http.HttpServletRequest;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.User;
import com.epam.te.hotel.service.user.ApplyUserInfoService;
import com.epam.te.hotel.service.ServiceException;
/**
 * Command for changing information about user. 
 * <br/>
 * Command that is allowed for execution for admins and for clients.
 */
public class ApplyUserInfoCommand implements ICommand{
	
	private static final String ACTION_MESSAGE = "actionMessage";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String USER = "user";
	private static final String EMAIL = "email";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String PATRONYMIC = "patronymic";
	private static final String PASSPORT = "passport";
	
	/**
	 * Execute command for changing information about user.
	 * Calls class ApplyUserInfoService.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;		
		User user = null;

		String name = request.getParameter(NAME.trim());
		String surname = request.getParameter(SURNAME.trim());
		String patronymic = request.getParameter(PATRONYMIC.trim());
		String email = request.getParameter(EMAIL.trim());
		String passport = request.getParameter(PASSPORT.trim());

		try{
			user = ApplyUserInfoService.findUserByLogin(((User)request.getSession().getAttribute(USER)).getLogin());
			int id_user = user.getId();
			ApplyUserInfoService.applyUserInfo(user, id_user, name, surname, patronymic, email, passport);
			page = refreshWithChanges(request, user,  id_user);
			request.setAttribute(ACTION_MESSAGE, ConfigManager.getInstance().getProperty(
					ConfigManager.CHANGE_USER_INFO_SUCCESS_MESSAGE));
		}catch (ServiceException e) {
			request.setAttribute(ERROR_MESSAGE, ConfigManager.getInstance().getProperty(
					ConfigManager.CHANGE_USER_INFO_ERROR_MESSAGE));
			throw new CommandException(e.getMessage(), e);
		}
		return page;
	}
	
	private String refreshWithChanges(HttpServletRequest request, User user, int id_user) throws  ServiceException {
		String page = null;
		user = ApplyUserInfoService.findUserByLogin(user.getLogin());	
		request.setAttribute(USER, user);
		if (user.getIdRole() == 1){
			page = JspPageName.ADMIN_PAGE;
		}else if (user.getIdRole() == 2){
			page = JspPageName.CLIENT_CABINET;
		}
		return page;
	}
	

}
