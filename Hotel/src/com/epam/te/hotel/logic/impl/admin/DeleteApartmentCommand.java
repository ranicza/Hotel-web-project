package com.epam.te.hotel.logic.impl.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.Apartment;
import com.epam.te.hotel.service.admin.DeleteApartmentService;
import com.epam.te.hotel.service.user.FindApartmentService;
import com.epam.te.hotel.service.ServiceException;
/**
 * Command for deleting apartment. 
 * <br/>
 * Admin command that is allowed for execution for admins only.
 */
public class DeleteApartmentCommand implements ICommand {
	
	private static final String APARTMENT_ID = "id_apartment";
	private static final String APARTMENT_LIST = "apartmentList";
	private static final String ACTION_MESSAGE = "actionMessage";
	
	/**
	 * Execute command for deleting apartment.
	 * Calls class DeleteApartmentService.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;
		
		int id_apartment = Integer.parseInt(request.getParameter(APARTMENT_ID));
		try{
			DeleteApartmentService.deleteApartment(id_apartment);
			page = refreshWithChanges(request);
			request.setAttribute(ACTION_MESSAGE, ConfigManager.getInstance().getProperty(
							ConfigManager.DELETE_APARTMENT_SUCCESS_MESSAGE));
		}catch (ServiceException e) {
			throw new CommandException(e.getMessage(), e);
		}		
		return page;
	}

	private String refreshWithChanges(HttpServletRequest request) throws CommandException {
		ArrayList<Apartment> apartmentList;
		String page = null;
		try{
			apartmentList = FindApartmentService.findAllApartment();
			request.setAttribute(APARTMENT_LIST, apartmentList);
			page = JspPageName.ROOM_ADMINISTRATION;
		}catch(ServiceException e){
			throw new CommandException(e.getMessage(), e);
		}
		return page;
	}
}

