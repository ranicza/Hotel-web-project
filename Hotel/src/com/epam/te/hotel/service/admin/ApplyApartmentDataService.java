package com.epam.te.hotel.service.admin;

import java.util.regex.Pattern;

import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IApartmentDAO;
import com.epam.te.hotel.model.dao.sql.ApartmentDAO;
import com.epam.te.hotel.service.ServiceException;
/**
 * Contains all logic associated with changing information about apartment.
 */
public class ApplyApartmentDataService {
	private static final String ROOM_NUMBER_VALIDATION = "^[0-9]{1,5}$";
	private static final String COST_VALIDATION = "^[0-9]{1,7}$";
	
	/**
	 * Changes apartment information.
	 * @param id_apartment
	 * @param number_room
	 * @param category
	 * @param positions
	 * @param cost
	 * @throws ServiceException
	 */
	public static void applyApartmentData(int id_apartment, int number_room, String category, int positions, int cost) throws ServiceException{
		IApartmentDAO apartmentDAO = new ApartmentDAO();
		
		if (id_apartment != 0){
			try{
				if(Pattern.matches(ROOM_NUMBER_VALIDATION, String.valueOf(number_room)) &&
						Pattern.matches(COST_VALIDATION, String.valueOf(cost)))	{
					apartmentDAO.changeApartmentData(id_apartment, number_room, category, positions, cost);
				}				
			}catch (DAOException e){
				throw new ServiceException(e.getMessage(), e);
			}
		} else{
			ConfigManager.getInstance().getProperty(ConfigManager.CHANGE_APARTMENT_DATA_ERROR_MESSAGE);
		}
	}

}

