package com.epam.te.hotel.logic.impl.client;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.Bill;
import com.epam.te.hotel.model.entity.User;
import com.epam.te.hotel.service.client.FindBillService;
import com.epam.te.hotel.service.client.PayBillService;
import com.epam.te.hotel.service.ServiceException;
/**
 * Command for making bill payment for the order by client. 
 * <br/>
 * Client command that is allowed for execution for clients only.
 */
public class PaymentCommand implements ICommand{
	
	private static final Logger LOG = Logger.getLogger(PaymentCommand.class);

	private static final String CLIENT_BILL = "clientBill";
	private static final String BILL_ID = "id_bill";
	private static final String USER = "user";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String ACTION_MESSAGE = "actionMessage";
	
	/**
	 * Execute command for making payment for the order by client. 
	 * Calls class PayBillService.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;

		String idBill = request.getParameter(BILL_ID);
		try {
			PayBillService.payBill(idBill);
			page = refreshWithChanges(request);
			request.setAttribute(ACTION_MESSAGE,ConfigManager.getInstance().getProperty(
							ConfigManager.PAYMENT_SUCCESS_MESSAGE));
		} catch (ServiceException e) {
			LOG.error("Something goes wrong with payment bill.", e);
			request.setAttribute(ERROR_MESSAGE, ConfigManager.getInstance().getProperty(
					ConfigManager.SERVICE_EXCEPTION_ERROR_MESSAGE));		
			throw new CommandException(e.getMessage(), e);
		}
		return page;
	}

	private String refreshWithChanges(HttpServletRequest request) {
		String page = null;
		ArrayList<Bill> clientBill = null;
		User user = (User) request.getSession().getAttribute(USER);
		try {
			clientBill = FindBillService.findClientBill(user.getId());
			request.setAttribute(CLIENT_BILL, clientBill);
		} catch (ServiceException e) {
			LOG.error("Something goes wrong, redirect to error page.", e);
			request.setAttribute(ERROR_MESSAGE,	ConfigManager.getInstance().getProperty(
							ConfigManager.SERVICE_EXCEPTION_ERROR_MESSAGE));
		}		
		page = JspPageName.CLIENT_BILL;
		return page;
	}
	
	
}

