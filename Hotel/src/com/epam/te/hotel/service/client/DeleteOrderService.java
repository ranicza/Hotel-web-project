package com.epam.te.hotel.service.client;

import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IOrderDAO;
import com.epam.te.hotel.model.dao.sql.OrderDAO;
import com.epam.te.hotel.model.entity.Order;
import com.epam.te.hotel.model.entity.enumeration.OrderStatus;
import com.epam.te.hotel.service.ServiceException;
/**
 * Contains all logic associated with deleting order.
 */
public class DeleteOrderService {	
	public static final String ERROR_MESSAGE = "errorMessage";
	
	/**
	 * Delete order by its id. Can't delete order which is 
	 * confirmed by admin (has status 'confirmed'), in this case 
	 * throws new ServiceException. 
	 * @param id_order
	 * @throws ServiceException
	 */
	public static void deleteOrder(String id_order) throws ServiceException {
		IOrderDAO orderDAO = new OrderDAO();
		if (id_order != null) {
			try {
				Order order = orderDAO.findOrderById(Integer.parseInt(id_order));
				if (order.getStatus().equals(OrderStatus.CONFIRMED)) {
					throw new ServiceException(ConfigManager.getInstance().getProperty(
											ConfigManager.DELETE_CONFIRMED_ORDER_MESSAGE));
				}
				orderDAO.deleteOrderById(Integer.parseInt(id_order));
			} catch (DAOException e) {
				throw new ServiceException(e.getMessage(), e);
			}
		} else {
			throw new ServiceException(ConfigManager.getInstance().getProperty(
							ConfigManager.DELETE_ORDER_PROBLEM_MESSAGE));
		}
		}
}

