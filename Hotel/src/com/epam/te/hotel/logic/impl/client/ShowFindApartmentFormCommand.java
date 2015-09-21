package com.epam.te.hotel.logic.impl.client;

import javax.servlet.http.HttpServletRequest;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;

/**
 * Command redirect client to jsp page where he could 
 * find apartment according his wishes. 
 * <br/>
 * Client command that is allowed for execution for clients only.
 */
public class ShowFindApartmentFormCommand implements ICommand{
	
	/**
	 * Execute command for redirecting client to jsp page
	 * where he could execute operation for finding
	 * apartment according written parameters.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) {
		String page = JspPageName.FIND_APARTMENT;

		return page;
	}
}
