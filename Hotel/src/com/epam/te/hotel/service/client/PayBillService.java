package com.epam.te.hotel.service.client;

import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IBillDAO;
import com.epam.te.hotel.model.dao.sql.BillDAO;
import com.epam.te.hotel.model.entity.Bill;
import com.epam.te.hotel.model.entity.enumeration.BillStatus;
import com.epam.te.hotel.service.ServiceException;
/**
 * Contains all logic associated with making bill payment for the order by client.
 */
public class PayBillService {
	public static final String ERROR_MESSAGE = "errorMessage";
	/**
	 * Find client's bill by its id and change
	 * bill status to the status 'paid'.
	 * @param id_bill
	 * @throws ServiceException
	 */
	public static void payBill(String id_bill) throws ServiceException {
		IBillDAO billDAO = new BillDAO();
		if (id_bill != null) {
			try {
				Bill bill = billDAO.findBillById(Integer.parseInt(id_bill));
				if (bill.getStatus().equals(BillStatus.PAID)) {
					throw new ServiceException(ConfigManager.getInstance().getProperty(
											ConfigManager.BILL_ALREADY_PAID_MESSAGE));
				}
				billDAO.changeBillStatusById(Integer.parseInt(id_bill),	BillStatus.PAID);
			} catch (DAOException e) {
				throw new ServiceException(e.getMessage(), e);
			}
		} else {
			throw new ServiceException(ConfigManager.getInstance().getProperty(ConfigManager.BILL_PAID_ERROR_MESSAGE));
		}
	}
}
