package com.epam.te.hotel.logic.impl.user;

import javax.servlet.http.HttpServletRequest;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.User;
/**
 * Command for editing information about user. 
 * <br/>
 * Command that is allowed for execution for admins and clients only.
 */
public class EditUserInfoCommand implements ICommand{
	private static final String USER_ID = "id_user";
	private static final String USER = "user";
	private static final String ERROR_MESSAGE = "errorMessage";
	
	/**
	 * Find user by id and redirect him to jsp page 
	 * for changing his personal information,
	 * otherwise redirect to jsp page 'client cabinet'.
	 * Calls class FindUserService.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;
		User user = (User)request.getSession().getAttribute(USER);		
		if (user != null) {
			int id_user = user.getId();
			request.setAttribute( USER_ID, id_user);
			page = JspPageName.CHANGE_USER_INFO;
		} else {
			request.setAttribute(ERROR_MESSAGE,	ConfigManager.getInstance().getProperty(
									ConfigManager.SERVICE_EXCEPTION_ERROR_MESSAGE));
			 page = JspPageName.INDEX;
		}
		
		
		
		//int id_user = Integer.parseInt(request.getParameter(USER_ID));		
//		try{
//			user = FindUserService.findUserById(id_user);
//			if (user != null) {
//				request.setAttribute( USER_ID, id_user);
//				page = JspPageName.CHANGE_USER_INFO;
//			} else {
//				request.setAttribute(ERROR_MESSAGE,	ConfigManager.getInstance().getProperty(
//										ConfigManager.SERVICE_EXCEPTION_ERROR_MESSAGE));
//				 page = JspPageName.INDEX;
//			}
//		}catch (ServiceException e){
//			throw new CommandException(e.getMessage(), e);
//		}
		return page;		
	}

}

