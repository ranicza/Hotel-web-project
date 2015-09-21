package com.epam.te.hotel.service.admin;

import java.util.regex.Pattern;

import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IApartmentDAO;
import com.epam.te.hotel.model.dao.sql.ApartmentDAO;
import com.epam.te.hotel.model.entity.Apartment;
import com.epam.te.hotel.model.entity.enumeration.ApartmentCategory;
import com.epam.te.hotel.service.ServiceException;
/**
 * Contains all logic associated with adding a new apartment.
 */
public class AddApartmentService {

	private static final String ROOM_NUMBER_VALIDATION = "^[0-9]{1,5}$";
	private static final String COST_VALIDATION = "^[0-9]{1,7}$";
	
	/**
	 * Creates a new apartment.
	 * @param apartment
	 * @param number_room
	 * @param category
	 * @param positions
	 * @param cost
	 * @throws ServiceException
	 */
	public static void addApartment(Apartment apartment, int number_room, String category, int positions, int cost) throws ServiceException{
		IApartmentDAO apartmentDAO = new ApartmentDAO();
		try{
			if(Pattern.matches(ROOM_NUMBER_VALIDATION, String.valueOf(number_room)) && Pattern.matches(COST_VALIDATION, String.valueOf(cost)))	{
				apartment.setRoomNumber(number_room);
				apartment.setPositions(positions);
				apartment.setCategory(ApartmentCategory.valueOf(category));
				apartment.setCost(cost);
				apartmentDAO.addApartment(apartment);
		    }
		}catch (DAOException e){
			throw new ServiceException(e.getMessage(), e);
		}

	}

}
