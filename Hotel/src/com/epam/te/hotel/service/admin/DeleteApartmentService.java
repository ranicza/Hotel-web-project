package com.epam.te.hotel.service.admin;

import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IApartmentDAO;
import com.epam.te.hotel.model.dao.sql.ApartmentDAO;
import com.epam.te.hotel.service.ServiceException;
/**
 * Contains all logic associated with deleting apartment.
 */
public class DeleteApartmentService {
	
	/**
	 * Delete apartment
	 * @param id_apartment
	 * @throws ServiceException
	 */
	public static void deleteApartment(int id_apartment) throws ServiceException{
		IApartmentDAO apartmentDAO = new ApartmentDAO();
		
		if (id_apartment != 0){
			try{
				apartmentDAO.deleteApartmentById(id_apartment);;
			}catch (DAOException e){
				throw new ServiceException(e.getMessage(), e);
			}
		} else{
			ConfigManager.getInstance().getProperty(ConfigManager.DELETE_APARTMENT_ERROR_MESSAGE);
		}
	}

}
