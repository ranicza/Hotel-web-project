package com.epam.te.hotel.logic.impl.client;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.te.hotel.controller.Controller;
import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.Apartment;
import com.epam.te.hotel.service.user.FindApartmentService;
import com.epam.te.hotel.service.ServiceException;
/**
 * Command for finding apartment. 
 * <br/>
 * Client command that is allowed for execution for clients only.
 */
public class FindApartmentCommand implements ICommand{
	private static final Logger LOG= Logger.getLogger(Controller.class);
	private static final String FREE_APARTMENT= "freeApartment";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String POSITIONS = "positions";
	private static final String CATEGORY = "category";
	private static final String DATE_ARRIVAL = "date_arrival";
	private static final String DATE_DEPARTURE = "date_departure";
	private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private static Date date_a;
	private static Date date_d;
	
	/**
	 * Execute command for finding apartment 
	 * according chosen parameters by client.
	 * Calls class FindApartmentService.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;
		ArrayList<Apartment> freeApartment = null;
			
		String date_arrival = request.getParameter(DATE_ARRIVAL);
		String date_departure = request.getParameter(DATE_DEPARTURE);

		Integer positions = Integer.parseInt(request.getParameter(POSITIONS));
		String apartment_category = request.getParameter(CATEGORY);

		try{
			date_a = format.parse(date_arrival);
        	date_d = format.parse(date_departure);     	
    	}catch(ParseException e){
    		LOG.error(e.getMessage(), e);
    	}

		if (date_arrival == "" || date_departure == "" || date_a.after(date_d)){
			page = JspPageName.FIND_APARTMENT;
			request.setAttribute(ERROR_MESSAGE,	ConfigManager.getInstance().getProperty(ConfigManager.CHOOSE_CORRECT_DATE_MESSAGE));			
		}else{
			request.getSession().setAttribute(DATE_ARRIVAL, date_arrival);
			request.getSession().setAttribute(DATE_DEPARTURE, date_departure);
			try{				
				freeApartment  = FindApartmentService.findFreeApartment(
						date_arrival ,
						date_departure, 
						positions,	
						(apartment_category.equalsIgnoreCase("any") ? null : apartment_category)); 
				if (freeApartment.size() > 0) {
					request.setAttribute(FREE_APARTMENT, freeApartment);
					page = JspPageName.FREE_APARTMENT;
				}else{
					page = JspPageName.FIND_APARTMENT;
					request.setAttribute(ERROR_MESSAGE,	ConfigManager.getInstance().getProperty(ConfigManager.NO_FREE_APARTMENT_MESSAGE));
				}						
			}catch(ServiceException e){
				throw new CommandException(e.getMessage(), e);
			}
		}
		return page;
	}

}

