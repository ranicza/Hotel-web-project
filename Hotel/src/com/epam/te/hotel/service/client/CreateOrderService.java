package com.epam.te.hotel.service.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IApartmentDAO;
import com.epam.te.hotel.model.dao.IOrderDAO;
import com.epam.te.hotel.model.dao.sql.ApartmentDAO;
import com.epam.te.hotel.model.dao.sql.OrderDAO;
import com.epam.te.hotel.model.entity.Order;
import com.epam.te.hotel.model.entity.User;
import com.epam.te.hotel.service.ServiceException;
/**
 * Contains all logic associated with creating a new order.
 */
public class CreateOrderService {
	public static final Logger LOG = Logger.getLogger(CreateOrderService.class);

	public static final String ERROR_MESSAGE = "errorMessage";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String LOG_ERROR_DATE_MESSAGE = "Wrong date exception.";
	private static final String LOG_ERROR_CREATE_ORDER_MESSAGE = "Create order problem message.";
	
	/**
	 * Create order according chosen parameters by client.
	 * @param order
	 * @param user
	 * @param id_apartment
	 * @param date_arrival
	 * @param date_departure
	 * @throws ServiceException
	 */
	public static void createOrder(Order order, User user, int id_user, String id_apartment,	String date_arrival, String date_departure) throws ServiceException{
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		IOrderDAO orderDAO = new OrderDAO();
		IApartmentDAO apartmentDAO = new ApartmentDAO();
		
		if (id_apartment != null && date_arrival != "" && date_departure != "") {
			try{
				order.setDateArrival(format.parse(date_arrival));
				order.setDateDeparture(format.parse(date_departure));
			}catch (ParseException e) {
				throw new ServiceException(e.getMessage(), e);
			}
			if (CreateOrderService.checkDate(order.getDateArrival(), order.getDateDeparture())) {
				order.setUser(user);
				try {
					order.setApartment(apartmentDAO.findApartmentById(Integer.parseInt(id_apartment)));
					orderDAO.createOrder(order, id_user);
				} catch (DAOException e) {
					throw new ServiceException(e.getMessage(), e);
				}
			}else {
				LOG.error(LOG_ERROR_DATE_MESSAGE);
				throw new ServiceException(LOG_ERROR_DATE_MESSAGE);
			}
		} else {
			LOG.error(LOG_ERROR_CREATE_ORDER_MESSAGE);
			throw new ServiceException(LOG_ERROR_CREATE_ORDER_MESSAGE);
		}		
	}
	
	
	private static boolean checkDate(Date date_arrival, Date date_departure){
		if (date_departure.getTime() - date_arrival.getTime() > 0) {
			return true;
		}
		return false;
	}
}

