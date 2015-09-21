package com.epam.te.hotel.logic.impl.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.Apartment;
import com.epam.te.hotel.service.admin.ApplyApartmentDataService;
import com.epam.te.hotel.service.user.FindApartmentService;
import com.epam.te.hotel.service.ServiceException;
/**
 * Command for showing information list about all apartments. 
 * <br/>
 * Admin command that is allowed for execution for admins only.
 */
public class ApplyApartmentDataCommand implements ICommand{
	
	private static final String ACTION_MESSAGE = "actionMessage";
	private static final String APARTMENT_ID = "id_apartment";
	private static final String POSITIONS = "positions";
	private static final String CATEGORY = "category";
	private static final String COST = "cost";
	private static final String ROOM_NUMBER = "roomNumber";	
	private static final String APARTMENT_LIST = "apartmentList";
	
	/**
	 * Execute command for changing information about apartment.
	 * Calls class ApplyApartmentDataService.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;
		int id_apartment = 0;
		
		id_apartment = Integer.parseInt(request.getParameter(APARTMENT_ID));
		Integer changed_roomNumber = Integer.parseInt(request.getParameter(ROOM_NUMBER.trim()));	
		String changed_category = request.getParameter(CATEGORY);
		Integer changed_positions = Integer.parseInt(request.getParameter(POSITIONS));
		Integer changed_cost = Integer.parseInt(request.getParameter(COST.trim()));
		
		try{
			ApplyApartmentDataService.applyApartmentData(id_apartment,changed_roomNumber, changed_category, changed_positions, changed_cost);
			page = refreshWithChanges(request);
			request.setAttribute(ACTION_MESSAGE, ConfigManager.getInstance().getProperty(
							ConfigManager.CHANGE_APARTMENT_DATA_SUCCESS_MESSAGE));
		}catch(ServiceException e) {
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

