package com.epam.te.hotel.service.client;
import java.util.ArrayList;

import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IBillDAO;
import com.epam.te.hotel.model.dao.sql.BillDAO;
import com.epam.te.hotel.model.entity.Bill;
import com.epam.te.hotel.service.ServiceException;
/**
 * Contains all logic associated with finding client's bills.
 */
public class FindBillService {

	/**
	 * Finds client's bills by his id.
	 * @param id_user
	 * @return list of finding client's bills
	 * @throws ServiceException
	 */
	public static ArrayList<Bill> findClientBill(int id_user) throws ServiceException {
		IBillDAO billDAO = new BillDAO();
		ArrayList<Bill> billList = new ArrayList<Bill>();
		try {
			for (Bill bill : billDAO.findUserBills(id_user)) {
				billList.add(bill);
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);			
		}
		return billList;
	}
}

