package com.epam.te.hotel.service.user;
import java.util.ArrayList;

import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IOrderDAO;
import com.epam.te.hotel.model.dao.sql.OrderDAO;
import com.epam.te.hotel.model.entity.Order;
import com.epam.te.hotel.service.ServiceException;
/**
 * All logic associated with finding order process.
 */
public class FindOrderService {

	/**
	 * Finds all existing orders.
	 * @return list of all orders
	 * @throws ServiceException
	 */
	public static ArrayList<Order> findAllOrder() throws ServiceException{
		IOrderDAO orderDAO = new OrderDAO();
		ArrayList<Order> orderList = null;
		try {
			orderList = orderDAO.findAllOrders();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return orderList;
	}
	
	/**
	 * Find client's order according his id.
	 * @param id_user
	 * @return list of client's orders
	 * @throws ServiceException
	 */
	public static ArrayList<Order> findClientOrder(int id_user) throws ServiceException{
		IOrderDAO orderDAO = new OrderDAO();
		ArrayList<Order> orderList = new ArrayList<Order>();
		try{
			for (Order order : orderDAO.findUserOrder(id_user)) {
				orderList.add(order);
			}
		}catch (DAOException e){
			throw new ServiceException(e.getMessage(), e);
		}
		return orderList;
	}
}

