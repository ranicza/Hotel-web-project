package com.epam.te.hotel.logic.impl.client;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.ICommand;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.Bill;
import com.epam.te.hotel.model.entity.User;
import com.epam.te.hotel.service.client.FindBillService;
import com.epam.te.hotel.service.ServiceException;
/**
 * Command for showing information list of all client's bills. 
 * <br/>
 * Client command that is allowed for execution for clients only.
 */
public class ShowClientBillCommand implements ICommand{
	
	private static final String CLIENT_BILL = "clientBill";
	private static final String USER = "user";
	private static final String ERROR_MESSAGE = "errorMessage";
	
	/**
	 * Execute command for showing information list about all client's bills.
	 * Calls class FindBillService.
	 * @param request request to read the command from
     * @return String (name of the command)
     * @throws CommandException
	 */
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;
		ArrayList<Bill> clientBill = null;
		User user = (User) request.getSession().getAttribute(USER);
		try {
			clientBill = FindBillService.findClientBill(user.getId());
			if (clientBill.size() != 0) {
				request.setAttribute(CLIENT_BILL, clientBill);
				page = JspPageName.CLIENT_BILL;
			} else {
				request.setAttribute(ERROR_MESSAGE,	ConfigManager.getInstance().getProperty(
										ConfigManager.DOES_NOT_HAVE_BILL_MESSAGE));
				page = JspPageName.CLIENT_CABINET;
			}			
		} catch (ServiceException e) {
			request.setAttribute(ERROR_MESSAGE, ConfigManager.getInstance().getProperty(
							ConfigManager.SERVICE_EXCEPTION_ERROR_MESSAGE));
			throw new CommandException(e.getMessage(), e);
		}
		return page;
	}

}

