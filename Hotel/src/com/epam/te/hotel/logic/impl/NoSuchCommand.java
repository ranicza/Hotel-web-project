package com.epam.te.hotel.logic.impl;

import javax.servlet.http.HttpServletRequest;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.ICommand;
/**
 * Redirect to the index jsp page if there are no such existing commond. 
 * <br/>
 * Everyone allowed to logout.
 */
public class NoSuchCommand implements ICommand{
	
	/**
	 * Redirect to the index jsp page
	 * if there are no such existing command.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request){
		String page = JspPageName.INDEX;
		return page;
	}
}
