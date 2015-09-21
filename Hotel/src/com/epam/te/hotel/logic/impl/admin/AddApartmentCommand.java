package com.epam.te.hotel.logic.impl.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.Apartment;
import com.epam.te.hotel.service.ServiceException;
import com.epam.te.hotel.service.admin.AddApartmentService;
import com.epam.te.hotel.service.user.FindApartmentService;
/**
 * Command for adding a new apartment to the system.
 * <br/>
 * Command that is allowed for execution for admins only.
 */
public class AddApartmentCommand  implements ICommand{
	
	private static final String ACTION_MESSAGE = "actionMessage";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String POSITIONS = "positions";
	private static final String CATEGORY = "category";
	private static final String COST = "cost";
	private static final String ROOM_NUMBER = "roomNumber";	
	private static final String APARTMENT_LIST = "apartmentList";
	
	/**
	 * Command adds a new apartment to the system.
	 * Calls class AddApartmentService & FindApartmentService.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;	
		Apartment apartment = new Apartment();
		
		Integer roomNumber = Integer.parseInt(request.getParameter(ROOM_NUMBER.trim()));	
		String category = request.getParameter(CATEGORY);
		String positions = request.getParameter(POSITIONS);
		Integer cost = Integer.parseInt(request.getParameter(COST.trim()));
		
		if (roomNumber == null || category == "" || positions == "" || cost == null){
			request.setAttribute(ERROR_MESSAGE,	ConfigManager.getInstance().getProperty(ConfigManager.WRITE_CORRECT_PARAMETERS_MESSAGE));
			page = JspPageName.ADD_APARTMENT;
		}else{
			try{
				if (checkRoomNumber(request, roomNumber) > 0){
					request.setAttribute(ERROR_MESSAGE,	ConfigManager.getInstance().getProperty(ConfigManager.INVALID_ROOMNUMBER_MESSAGE));
					page = JspPageName.ADD_APARTMENT;
				}else{
					int position = Integer.parseInt(positions);
					AddApartmentService.addApartment(apartment, roomNumber, category, position, cost);
					page = refreshWithChanges(request);
					request.setAttribute(ACTION_MESSAGE, ConfigManager.getInstance().getProperty(ConfigManager.ADD_APARTMENT_SUCCESS_MESSAGE));				
				}
			}catch (ServiceException e){
				throw new CommandException(e.getMessage(), e);
			}
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
	
	private static int checkRoomNumber(HttpServletRequest request, int number_room) throws ServiceException{
		ArrayList<Apartment> allApartment ;		
		int count = 0;
		allApartment = FindApartmentService.findAllApartment();		
		for (Apartment ap : allApartment) {
			if (ap.getRoomNumber() == number_room){			
				count++;
			}
		}
		return count;
	}
}
