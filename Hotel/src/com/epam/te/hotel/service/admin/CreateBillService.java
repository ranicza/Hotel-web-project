package com.epam.te.hotel.service.admin;

import java.util.Date;

import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IBillDAO;
import com.epam.te.hotel.model.dao.IOrderDAO;
import com.epam.te.hotel.model.dao.sql.BillDAO;
import com.epam.te.hotel.model.dao.sql.OrderDAO;
import com.epam.te.hotel.model.entity.Bill;
import com.epam.te.hotel.model.entity.Order;
import com.epam.te.hotel.model.entity.enumeration.BillStatus;
import com.epam.te.hotel.service.ServiceException;
/**
 * Contains all logic associated with bill creating.
 */
public class CreateBillService {
	/**
	 * Creates new bill with status 'unpaid'. 
	 * @param id_order
	 * @throws ServiceException
	 */
	public static void createBill(String id_order) throws ServiceException{
		try {
			IBillDAO billDAO = new BillDAO();
			IOrderDAO orderDAO = new OrderDAO();
			Bill bill = new Bill();
			Order order = orderDAO.findOrderById(Integer.parseInt(id_order));
			bill.setStatus(BillStatus.UNPAID);
			bill.setDateBill(new Date());
			bill.setTotalPrice(calculateTotalSum(order));
			bill.setOrder(order);		
			billDAO.createBill(bill);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}	
	
	private static int calculateTotalSum(Order order) {
		int sumOfDay = (int) ((order.getDateDeparture().getTime() - order.getDateArrival().getTime()) / (24 * 60 * 60 * 1000));
		int totalSum = sumOfDay * order.getApartment().getCost();
		return totalSum;
	}

}

