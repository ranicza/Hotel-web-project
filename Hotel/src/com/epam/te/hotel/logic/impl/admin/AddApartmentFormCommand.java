package com.epam.te.hotel.logic.impl.admin;

import javax.servlet.http.HttpServletRequest;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
/**
 * Command for redirecting admin to the jsp page where he can add new apartment.
 * <br/>
 * Command that is allowed for execution for admins only.
 */
public class AddApartmentFormCommand implements ICommand{
	/**
	 * Redirect admin to jsp page where he can add new apartment.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = JspPageName.ADD_APARTMENT;			   
		return page;
		
	}
}
