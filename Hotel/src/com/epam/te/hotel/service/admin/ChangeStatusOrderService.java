package com.epam.te.hotel.service.admin;

import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IOrderDAO;
import com.epam.te.hotel.model.dao.sql.OrderDAO;
import com.epam.te.hotel.model.entity.enumeration.OrderStatus;
import com.epam.te.hotel.service.ServiceException;
/**
 * Contains all logic associated with changing order status.
 */
public class ChangeStatusOrderService {
	
	public static final String ERROR_MESSAGE = "errorMessage";
	public static final String STATUS = "empty";
	
	/**
	 * Change order status (awaiting/ confirmed/ denied) by order id.
	 * If order status equals 'confirmed' creates a new client's bill
	 * with status 'unpaid'.
	 * @param id_order
	 * @param status_order
	 * @throws ServiceException
	 */
	public static void changeStatusOrder(String id_order, String status_order) throws ServiceException{
		IOrderDAO orderDAO = new OrderDAO();
		if (id_order != null) {
			if (STATUS.equals(status_order)) {
				throw new ServiceException(ConfigManager.getInstance().getProperty(
						ConfigManager.CHOOSE_ACTION_ERROR_MESSAGE));
			}
			try {
				orderDAO.changeOrderStatusById(Integer.parseInt(id_order), OrderStatus.valueOf(status_order));
				if (OrderStatus.valueOf(status_order).equals(OrderStatus.CONFIRMED)) {
					CreateBillService.createBill(id_order);
				}
			} catch (DAOException e) {
				throw new ServiceException(e.getMessage(), e);
			}
		} else {
			throw new ServiceException(ConfigManager.getInstance().getProperty(
					ConfigManager.CHANGE_STATUS_ORDER_EXCEPTION_MESSAGE));
		}
	}
}

