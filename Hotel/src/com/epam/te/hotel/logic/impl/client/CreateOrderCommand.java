package com.epam.te.hotel.logic.impl.client;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.Apartment;
import com.epam.te.hotel.model.entity.Order;
import com.epam.te.hotel.model.entity.User;
import com.epam.te.hotel.model.entity.enumeration.OrderStatus;
import com.epam.te.hotel.service.client.CreateOrderService;
import com.epam.te.hotel.service.user.ApplyUserInfoService;
import com.epam.te.hotel.service.user.FindApartmentService;
import com.epam.te.hotel.service.ServiceException;

/**
 * Command for creating order. 
 * <br/>
 * Client command that is allowed for execution for clients only.
 */
public class CreateOrderCommand implements ICommand{
	
	private static final String FREE_APARTMENT = "freeApartment";
	public static final String ACTION_MESSAGE = "actionMessage";
	private static final String DATE_ARRIVAL = "date_arrival";
	private static final String DATE_DEPARTURE = "date_departure";
	private static final String APARTMENT_ID = "id_apartment";
	private static final String USER = "user";
	
	
	/**
	 * Execute command for creating order with status 'awaiting'.
	 * Calls classes CreateOrderService and FindApartmentService.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;
		int id_user = 0;
		
		Order order = new Order();
		User user;
		try{
			user = ApplyUserInfoService.findUserByLogin(((User)request.getSession().getAttribute(USER)).getLogin());
		    id_user = user.getId();
		} catch(ServiceException e){
			throw new CommandException(e.getMessage(), e);
		}

		if (user != null){
			order.setDateOrder(new Date());
			order.setStatus(OrderStatus.AWAITING);
			String id_apartment = request.getParameter(APARTMENT_ID);
			
			String date_arrival = (String) request.getSession().getAttribute(DATE_ARRIVAL);
			String date_departure = (String) request.getSession().getAttribute(DATE_DEPARTURE);

			try{				
				CreateOrderService.createOrder(order, user, id_user, id_apartment, date_arrival, date_departure);
				ArrayList<Apartment> freeApartment = FindApartmentService.findFreeApartment();
				request.setAttribute(FREE_APARTMENT, freeApartment);
				page = refreshWithChanges(request);
				request.setAttribute(ACTION_MESSAGE, ConfigManager.getInstance().getProperty(ConfigManager.CREATE_ORDER_SUCCESS_MESSAGE));
			}catch(ServiceException e){
				throw new CommandException(e.getMessage(), e);
			}
		}

		return page;
	}
	
	private String refreshWithChanges(HttpServletRequest request) throws ServiceException{
		ArrayList<Apartment> freeApartment;
		
		freeApartment = FindApartmentService.findFreeApartment();
		request.setAttribute(FREE_APARTMENT, freeApartment);

		return JspPageName.CLIENT_CABINET;
	}
}

