package com.epam.te.hotel.model.dao.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.epam.te.hotel.model.dao.AbstractDAO;
import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IApartmentDAO;
import com.epam.te.hotel.model.dao.IOrderDAO;
import com.epam.te.hotel.model.dao.IUserDAO;
import com.epam.te.hotel.model.entity.Order;
import com.epam.te.hotel.model.entity.enumeration.OrderStatus;
/**
 *Class OrderDAO for the work with the database.
 */
public class OrderDAO extends AbstractDAO implements IOrderDAO{
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String ID_ORDER = "id_order";
	public static final String DATE_ORDER = "date_order";
	public static final String DATE_ARRIVAL = "date_arrival";
	public static final String DATE_DEPARTURE = "date_departure";
	public static final String STATUS_ORDER = "status";
	public static final String ID_APARTMENT = "id_apartment";
	public static final String ID_USER = "id_user";

	public static DateFormat format = new SimpleDateFormat(DATE_FORMAT);
		
	public static final String INSERT_ORDER = "INSERT INTO `order` "
			+ "(date_arrival, date_departure, status, date_order, id_apartment, id_user) VALUES(?,?,?,?,?,?)";
	
	public static final String UPDATE_ORDER_STATUS = "UPDATE `order` SET status = ? WHERE id_order =?";
	
	public static final String GET_ORDER_BY_ID = "SELECT * FROM `order` WHERE id_order = ?";
	
	public static final String GET_ALL_ORDERS = "SELECT * FROM `order`";
	
	public static final String DELETE_ORDER_BY_ID = "DELETE FROM `order` WHERE id_order = ?";
	
	public static final String GET_USER_ORDERS = "SELECT * FROM `order` WHERE id_user = ?";
	
	/**
	 * Adds a new order in the database.
	 * @param order
	 * @param id_user
	 * @throws DAOException
	 */
	@Override
	public void createOrder(Order order, int id_user) throws DAOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(INSERT_ORDER);
			preparedStatement.setDate(1, new Date(order.getDateArrival().getTime()));
			preparedStatement.setDate(2, new Date(order.getDateDeparture().getTime()));
			preparedStatement.setString(3, order.getStatus().toString());
			preparedStatement.setDate(4, new Date(order.getDateOrder().getTime()));
			preparedStatement.setInt(5, order.getApartment().getId());
			preparedStatement.setInt(6, id_user);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
	}
	
	/**
	 * Changes order status by its id in the database.
	 * @param id
	 * @param status
	 * @throws DAOException
	 */
	@Override
	public void changeOrderStatusById(int id, OrderStatus status)throws DAOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS);
			preparedStatement.setString(1, status.toString());
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
	}
	
	/**
	 * Finds client's order by its id in the database.
	 * @param id
	 * @return order
	 * @throws DAOException
	 */
	@Override
	public Order findOrderById(int id) throws DAOException {
		Order order = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				order = new Order();
				IApartmentDAO apartmentDAO = new ApartmentDAO();
				IUserDAO userDAO = new UserDAO();
				order.setId(resultSet.getInt(ID_ORDER));
				order.setDateArrival(resultSet.getDate(DATE_ARRIVAL));
				order.setDateDeparture(resultSet.getDate(DATE_DEPARTURE));
				order.setStatus(OrderStatus.valueOf(resultSet.getString(STATUS_ORDER)));
				order.setDateOrder(resultSet.getDate(DATE_ORDER));
				order.setApartment(apartmentDAO.findApartmentById(resultSet.getInt(ID_APARTMENT)));
				order.setUser(userDAO.findUserById(resultSet.getInt(ID_USER)));
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return order;
	}
	
	/**
	 * Finds all existing orders in the database.
	 * @return list of orders
	 * @throws DAOException
	 */
	@Override
	public ArrayList<Order> findAllOrders() throws DAOException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		IUserDAO userDAO = new UserDAO();
		IApartmentDAO apartmentDAO = new ApartmentDAO();
		ArrayList<Order> orderList = new ArrayList<Order>();
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(GET_ALL_ORDERS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt(ID_ORDER));
				order.setDateArrival(resultSet.getDate(DATE_ARRIVAL));
				order.setDateDeparture(resultSet.getDate(DATE_DEPARTURE));
				order.setStatus(OrderStatus.valueOf(resultSet.getString(STATUS_ORDER)));
				order.setDateOrder(resultSet.getDate(DATE_ORDER));
				order.setApartment(apartmentDAO.findApartmentById(resultSet.getInt(ID_APARTMENT)));
				order.setUser(userDAO.findUserById(resultSet.getInt(ID_USER)));
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage(), e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return orderList;
	}
	
	/**
	 * Deleting client's order by its id in the database.
	 * @param id_order
	 * @throws DAOException
	 */
	@Override
	public void deleteOrderById(int id_order) throws DAOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_ID);
			preparedStatement.setInt(1, id_order);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
	}
	
	/**
	 * Finds all client's order by his id in the database.
	 * @param id_user
	 * @return list of orders
	 * @throws DAOException
	 */
	@Override
	public ArrayList<Order> findUserOrder(int id_user) throws DAOException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		IUserDAO userDAO = new UserDAO();
		IApartmentDAO apartmentDAO = new ApartmentDAO();
		ArrayList<Order> orderList = new ArrayList<Order>();
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(GET_USER_ORDERS);
			preparedStatement.setInt(1, id_user);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt(ID_ORDER));
				order.setDateArrival(resultSet.getDate(DATE_ARRIVAL));
				order.setDateDeparture(resultSet.getDate(DATE_DEPARTURE));
				order.setStatus(OrderStatus.valueOf(resultSet.getString(STATUS_ORDER)));
				order.setDateOrder(resultSet.getDate(DATE_ORDER));
				order.setApartment(apartmentDAO.findApartmentById(resultSet.getInt(ID_APARTMENT)));
				order.setUser(userDAO.findUserById(resultSet.getInt(ID_USER)));
				orderList.add(order);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return orderList;
	}
}
