package com.epam.te.hotel.logic.impl.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.Order;
import com.epam.te.hotel.service.admin.ChangeStatusOrderService;
import com.epam.te.hotel.service.user.FindOrderService;
import com.epam.te.hotel.service.ServiceException;

/**
 * Command for changing order status (awaiting/ confirmed/ denied). 
 * <br/>
 * Command that is allowed for execution for admins only.
 */
public class ChangeStatusOrderCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(ChangeStatusOrderCommand.class);
	
	private static final String ORDER_LIST = "orderList";
	private static final String ORDER_ID = "id_order";
	private static final String ORDER_STATUS = "statusOrder";
	private static final String ERROR_MESSAGE = "errorMessage";
	public static final String ACTION_MESSAGE = "actionMessage";
	public static final String LOG_ERROR_MESSAGE = "Something goes wrong with changing order status.";
	
	/**
	 * Execute command for changing order status.
	 * Calls class ChangeStatusOrderService.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;

		String id_order = request.getParameter(ORDER_ID);
		String status_order = request.getParameter(ORDER_STATUS);

		try {
			ChangeStatusOrderService.changeStatusOrder(id_order,  status_order);
			page = refreshWithChanges(request);
			request.setAttribute(ACTION_MESSAGE, ConfigManager.getInstance().getProperty(
							ConfigManager.CHANGE_STATUS_SUCCESS_MESSAGE));
		} catch (ServiceException e) {
			LOG.error(LOG_ERROR_MESSAGE, e);
			request.setAttribute(ERROR_MESSAGE, e);
			page = refreshWithChanges(request);
			throw new CommandException(e.getMessage(), e);
		}
		return page;
	}

	private String refreshWithChanges(HttpServletRequest request) {
		String page = null;
		ArrayList<Order> orderList = null;
			try {
				orderList = FindOrderService.findAllOrder();
			} catch (ServiceException e) {
				LOG.error(LOG_ERROR_MESSAGE, e);
			}
			request.setAttribute(ORDER_LIST, orderList);
			page = JspPageName.ORDER_ADMINISTRATION;
		return page;
	}

}

