package com.epam.te.hotel.logic.impl.client;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.Order;
import com.epam.te.hotel.model.entity.User;
import com.epam.te.hotel.service.user.FindOrderService;
import com.epam.te.hotel.service.ServiceException;
/**
 * Command for showing information list of all client's orders. 
 * <br/>
 * Client command that is allowed for execution for clients only.
 */
public class ShowClientOrderCommand implements ICommand {	
	private static final String CLIENT_ORDER = "clientOrder";
	private static final String USER = "user";
	private static final String ERROR_MESSAGE = "errorMessage";
	
	/**
	 * Execute command for showing information list about all client's orders.
	 * Calls class FindOrderService.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		ArrayList<Order> clientOrder = null;
		User user = (User) request.getSession().getAttribute(USER);
		try {
			clientOrder = FindOrderService.findClientOrder(user.getId());
			if (clientOrder.size() != 0) {
				request.setAttribute(CLIENT_ORDER, clientOrder);
				page = JspPageName.CLIENT_ORDER;
			} else {
				request.setAttribute(ERROR_MESSAGE,	ConfigManager.getInstance().getProperty(
										ConfigManager.DOES_NOT_HAVE_ANY_ORDER));
				page = JspPageName.CLIENT_CABINET;
			}			
		} catch (ServiceException e) {
			request.setAttribute(ERROR_MESSAGE,	ConfigManager.getInstance().getProperty(
							ConfigManager.SERVICE_EXCEPTION_ERROR_MESSAGE));
		}
		return page;
	}
}

