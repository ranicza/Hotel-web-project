package com.epam.te.hotel.service.user;
import java.util.ArrayList;

import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IApartmentDAO;
import com.epam.te.hotel.model.dao.sql.ApartmentDAO;
import com.epam.te.hotel.model.entity.Apartment;
import com.epam.te.hotel.service.ServiceException;
/**
 * All logic associated with finding apartment process.
 */
public class FindApartmentService {
	/**
	 * Find all free apartments 
	 * @return list of apartments
	 * @throws ServiceException
	 */
	public static ArrayList<Apartment> findFreeApartment() throws ServiceException {
		IApartmentDAO apartmentDAO = new ApartmentDAO();
		ArrayList<Apartment> freeApartment = new ArrayList<Apartment>();
		try {
			for (Apartment apartment : apartmentDAO.findAllApartment()) {
				freeApartment.add(apartment);					
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return freeApartment;
	}
	
	/**
	 * Find free apartments according chosen parameters by client.
	 * @param date_arrival
	 * @param date_departure
	 * @param positions
	 * @param apartment_category
	 * @return list of free apartments
	 * @throws ServiceException
	 */
	public static ArrayList<Apartment> findFreeApartment(String date_arrival, String date_departure, Integer positions, String apartment_category) throws ServiceException {
		IApartmentDAO apartmentDAO = new ApartmentDAO();
		ArrayList<Apartment> freeApartment = new ArrayList<Apartment>();
		try {
			freeApartment = (ArrayList<Apartment>) apartmentDAO.findVacantApartment(date_arrival, date_departure, positions, apartment_category);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return freeApartment;
	}
	
	/**
	 * Find all existing apartments 
	 * @return list of all apartments
	 * @throws ServiceException
	 */
	public static ArrayList<Apartment> findAllApartment() throws ServiceException{
		IApartmentDAO apartmentDAO = new ApartmentDAO();
		ArrayList<Apartment> apartmentList = new ArrayList<Apartment>();
		try {
			apartmentList = apartmentDAO.findAllApartment();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return apartmentList;
	}
	
	/**
	 * Find apartment by its id.
	 * @param id_apartment
	 * @return apartment
	 * @throws ServiceException
	 */
	public static Apartment findApartmentById(int id_apartment) throws ServiceException {
		IApartmentDAO apartmentDAO = new ApartmentDAO();
		Apartment apartment = new Apartment();
		try{
			apartment = apartmentDAO.findApartmentById(id_apartment);
		}catch (DAOException e){
			throw new ServiceException(e.getMessage(), e);
		}
		return apartment;
	}

}

