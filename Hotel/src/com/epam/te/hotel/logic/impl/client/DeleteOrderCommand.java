package com.epam.te.hotel.logic.impl.client;

import javax.servlet.http.HttpServletRequest;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.User;
import com.epam.te.hotel.service.client.DeleteOrderService;
import com.epam.te.hotel.service.user.FindOrderService;
import com.epam.te.hotel.service.ServiceException;

/**
 * Command for deleting order. 
 * <br/>
 * Client command that is allowed for execution for clients only.
 */
public class DeleteOrderCommand implements ICommand{

	private static final String CLIENT_ORDER = "clientOrder";
	private static final String ORDER_ID = "id_order";
	private static final String USER = "user";
	private static final String ERROR_MESSAGE = "errorMessage";
	public static final String ACTION_MESSAGE = "actionMessage";	
	
	/**
	 * Execute command for deleting order.
	 * Calls class DeleteOrderService.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;
		String id_order = request.getParameter(ORDER_ID);
		try {
			DeleteOrderService.deleteOrder(id_order);
			page = refreshWithChanges(request);
			request.setAttribute(ACTION_MESSAGE, ConfigManager.getInstance().getProperty(
							ConfigManager.DELETE_ORDER_SUCCESS_MESSAGE));
		}catch (ServiceException e) {
			request.setAttribute(ERROR_MESSAGE, ConfigManager.getInstance().getProperty(
					ConfigManager.CAN_NOT_DELETE_ORDER_MESSAGE));
			throw new CommandException(e.getMessage(), e);
		}
		return page;
	}
		
	private String refreshWithChanges(HttpServletRequest request){
		String page = null;
		User user = (User) request.getSession().getAttribute(USER);
		try{
			request.setAttribute(CLIENT_ORDER,	FindOrderService.findClientOrder(user.getId()));
			page = JspPageName.CLIENT_ORDER;
		}catch(ServiceException e){
			request.setAttribute(ERROR_MESSAGE,	ConfigManager.getInstance().getProperty(
							ConfigManager.CAN_NOT_DELETE_ORDER_MESSAGE));
			page = JspPageName.CLIENT_ORDER;
			return page;
		}
		return page;
	}
}

