package com.epam.te.hotel.logic.impl.admin;

import javax.servlet.http.HttpServletRequest;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.Apartment;
import com.epam.te.hotel.service.user.FindApartmentService;
import com.epam.te.hotel.service.ServiceException;

/**
 * Command for changing information about apartment.
 * <br/>
 * Command that is allowed for execution for admins only.
 */
public class ChangeDataApartmentCommand implements ICommand {
	private static final String APARTMENT_ID = "id_apartment";
	private static final String APARTMENT = "apartment";
	private static final String ERROR_MESSAGE = "errorMessage";
	
	/**
	 * Redirect admin to jsp page where he can change information about apartment.
	 * Calls class FindApartmentService.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;
		Apartment apartment = null;		
		int id_apartment = Integer.parseInt(request.getParameter(APARTMENT_ID));		
		try{
			apartment =  FindApartmentService.findApartmentById(id_apartment);
			if (apartment != null) {
				request.setAttribute(APARTMENT, apartment);
				 page = JspPageName.CHANGE_APARTMENT_DATA;
			} else {
				request.setAttribute(ERROR_MESSAGE,	ConfigManager.getInstance().getProperty(
										ConfigManager. DOES_NOT_HAVE_SUCH_APARTMENT_MESSAGE));
				page = JspPageName.ROOM_ADMINISTRATION;
			}
		}catch (ServiceException e){
			throw new CommandException(e.getMessage(), e);			
		}			   
		return page;
		
	}
}

