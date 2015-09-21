package com.epam.te.hotel.logic.impl.admin;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.Order;
import com.epam.te.hotel.service.user.FindOrderService;
import com.epam.te.hotel.service.ServiceException;

/**
 * Command for showing information list about all client's orders. 
 * <br/>
 * Admin command that is allowed for execution for admins only.
 */
public class OrderAdministrationCommand implements ICommand{

	private static final String ORDER_LIST = "orderList";
	private static final String ERROR_MESSAGE = "errorMessage";
	
	/**
	 * Execute command for showing information list about all client's orders.
	 * Calls class FindOrderService.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;
		ArrayList<Order> orderList = null;
		try {
			orderList = FindOrderService.findAllOrder();
			request.setAttribute(ORDER_LIST, orderList);
			page = JspPageName.ORDER_ADMINISTRATION;
		} catch (ServiceException e) {
			request.setAttribute(ERROR_MESSAGE,	ConfigManager.getInstance().getProperty(
							ConfigManager.SERVICE_EXCEPTION_ERROR_MESSAGE));
			page = JspPageName.ERROR;
			throw new CommandException(e.getMessage(), e);
		}
		return page;
	}
}

