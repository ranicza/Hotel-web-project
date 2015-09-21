package com.epam.te.hotel.model.dao.sql;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.epam.te.hotel.model.dao.AbstractDAO;
import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IBillDAO;
import com.epam.te.hotel.model.dao.IOrderDAO;
import com.epam.te.hotel.model.entity.Bill;
import com.epam.te.hotel.model.entity.enumeration.BillStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *Class BillDAO for the work with the database.
 */
public class BillDAO extends AbstractDAO implements IBillDAO{

	private static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String BILL_ID = "id_bill";
	public static final String BILL_STATUS = "status";
	public static final String BILL_TOTAL_PRICE = "total_price";
	public static final String BILL_DATE = "date_bill";
	public static final String BILL_ORDER_ID = "id_order";	

	public static final String INSERT_BILL = "INSERT INTO bill (status, date_bill, total_price, id_order) VALUES(?,?,?,?)";
	
	private static final String FIND_BILL_BY_ID = "SELECT * FROM bill WHERE id_bill = ?";
	
	private static final String UPDATE_BILL_STATUS = "UPDATE bill SET status = ? WHERE id_bill = ?";
	
	public static final String FIND_USER_BILLS = "SELECT * FROM `bill` "
			+ "INNER JOIN `order` ON bill.id_order = order.id_order WHERE id_user = ?";
	
	/**
	 * Adds a new bill in the database.
	 * @param bill
	 * @throws DAOException
	 */
	@Override
	public void createBill(Bill bill) throws DAOException {
		PreparedStatement preparedStatement = null;
		DateFormat format = new SimpleDateFormat(DATE_FORMAT);
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(INSERT_BILL);
			preparedStatement.setString(1, bill.getStatus().toString());
			preparedStatement.setString(2, format.format(bill.getDateBill()).toString());
			preparedStatement.setInt(3, bill.getTotalPrice());		
			preparedStatement.setInt(4, bill.getOrder().getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
	}
	
	/**
	 * Finds bill by its id in the database.
	 * @param id_bill
	 * @return finding bill
	 * @throws DAOException
	 */
	@Override
	public Bill findBillById(int id_bill) throws DAOException {
		Bill bill = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(FIND_BILL_BY_ID);
			preparedStatement.setInt(1, id_bill);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				bill = new Bill();
				IOrderDAO orderDAO = new OrderDAO();
				bill.setId(resultSet.getInt(BILL_ID));
				bill.setStatus(BillStatus.valueOf(resultSet.getString(BILL_STATUS)));
				bill.setDateBill(resultSet.getDate(BILL_DATE));			
				bill.setTotalPrice(resultSet.getInt(BILL_TOTAL_PRICE));
				bill.setOrder(orderDAO.findOrderById(resultSet.getInt(BILL_ORDER_ID)));
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return bill;
	}
	
	/**
	 * Change bill status by its id in the database.
	 * @param id_bill
	 * @param status
	 * @throws DAOException
	 */
	@Override
	public void changeBillStatusById(int id_bill, BillStatus status) throws DAOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_BILL_STATUS);
			preparedStatement.setString(1, status.toString());
			preparedStatement.setInt(2, id_bill);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
	}

	/** Find all existing user bills in the database.
	 * @param idUser
	 * @return list of bills
	 * @throws DAOException
	 */
	@Override
	public ArrayList<Bill> findUserBills(int idUser) throws DAOException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Bill> billList = new ArrayList<Bill>();
		IOrderDAO orderDAO = new OrderDAO();
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(FIND_USER_BILLS);
			preparedStatement.setInt(1, idUser);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Bill bill = new Bill();
				bill.setId(resultSet.getInt(BILL_ID));
				bill.setStatus(BillStatus.valueOf(resultSet.getString(BILL_STATUS)));
				bill.setDateBill(resultSet.getDate(BILL_DATE));
				bill.setTotalPrice(resultSet.getInt(BILL_TOTAL_PRICE));
				bill.setOrder(orderDAO.findOrderById(resultSet.getInt(BILL_ORDER_ID)));
				billList.add(bill);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return billList;
	}
}
