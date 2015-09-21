package com.epam.te.hotel.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.ICommand;
/**
 * Command for logout. 
 * <br/>
 * Everyone allowed to logout.
 */
public class LogOutCommand implements ICommand {
	
	private static final Logger LOG = Logger.getLogger(LogOutCommand.class);
	private static final String INVALIDATE_SESSION = "Invalidate session.";

	/**
	 * Execute command for logout.
	 * Invalidate user's session.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		LOG.info(INVALIDATE_SESSION);
		return JspPageName.INDEX;
	}
}
