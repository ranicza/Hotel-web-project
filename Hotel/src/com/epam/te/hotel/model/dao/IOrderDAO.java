package com.epam.te.hotel.model.dao;

import java.util.ArrayList;

import com.epam.te.hotel.model.entity.Order;
import com.epam.te.hotel.model.entity.enumeration.OrderStatus;

/**
 * Interface IOrderDAO for the work with order DAO level.
 */
public interface IOrderDAO {

	/**
	 * Creates a new order.
	 * @param order
	 * @param id_user
	 * @throws DAOException
	 */
	void createOrder(Order order, int id_user) throws DAOException;
	
	/**
	 * Changes order status by its id.
	 * @param id
	 * @param status
	 * @throws DAOException
	 */
	void changeOrderStatusById(int id, OrderStatus status)throws DAOException;
	
	/**
	 * Finds client's order by its id.
	 * @param id
	 * @return order
	 * @throws DAOException
	 */
	Order findOrderById(int id) throws DAOException;
	
	/**
	 * Finds all existing orders.
	 * @return list of orders
	 * @throws DAOException
	 */
	ArrayList<Order> findAllOrders() throws DAOException;
	
	/**
	 * Deleting client's order by its id by client.
	 * @param id_order
	 * @throws DAOException
	 */
	void deleteOrderById(int id_order) throws DAOException;
	
	/**
	 * Finds all client's order by his id.
	 * @param id_user
	 * @return list of orders
	 * @throws DAOException
	 */
	ArrayList<Order> findUserOrder(int id_user) throws DAOException;
}
