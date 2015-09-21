package com.epam.te.hotel.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.ICommand;
/**
 * Command for changing language. 
 * <br/>
 * Everyone allowed to changing language.
 */
public class LanguageCommand implements ICommand{
	
	private static final String LANGUAGE = "local";
	
	/**
	 * Execute command for changing language.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		
		String lang = (String) request.getParameter(LANGUAGE);
		session.setAttribute(LANGUAGE, lang);
		
		return  JspPageName.INDEX;
	}
}
