package com.epam.te.hotel.model.dao;

import java.util.ArrayList;

import com.epam.te.hotel.model.entity.Bill;
import com.epam.te.hotel.model.entity.enumeration.BillStatus;
/**
 * Interface IBillDAO for the work with bill DAO level.
 */
public interface IBillDAO {
	
	/**
	 * Creates a new bill.
	 * @param bill
	 * @throws DAOException
	 */
	void createBill(Bill bill) throws DAOException;
	
	/**
	 * Finds bill by its id.
	 * @param id_bill
	 * @return finding bill
	 * @throws DAOException
	 */
	Bill findBillById(int id_bill) throws DAOException;
	
	/**
	 * Change bill status by its id.
	 * @param id_bill
	 * @param status
	 * @throws DAOException
	 */
	void changeBillStatusById(int id_bill, BillStatus status) throws DAOException;
	
	/** Find all existing user bills.
	 * @param idUser
	 * @return list of bills
	 * @throws DAOException
	 */
	ArrayList<Bill> findUserBills(int idUser) throws DAOException;

}
