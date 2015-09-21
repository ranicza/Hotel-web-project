package com.epam.te.hotel.model.dao;

import java.util.ArrayList;
import java.util.List;

import com.epam.te.hotel.model.entity.Apartment;
/**
 * Interface IApartmentDAO for the work with apartment DAO level.
 */
public interface IApartmentDAO{

	/**
	 * Finds all apartments existing in the system.
	 * @return list of finding apartments
	 * @throws DAOException
	 */
	ArrayList<Apartment> findAllApartment() throws DAOException;
	
	/**
	 * Find apartment by its id.
	 * @param parseInt  the parse integer id
	 * @return finding apartment
	 * @throws DAOException
	 */
	Apartment findApartmentById(int parseInt) throws DAOException;
	
	/**
	 * Finds vacant apartments according chosen parameters by client.
	 * @param arrival_date
	 * @param depart_date
	 * @param positions
	 * @param category
	 * @return list of findings apartments
	 * @throws DAOException
	 */
	List<Apartment> findVacantApartment(String arrival_date, String depart_date, Integer positions, String category) throws DAOException;
	
	/**
	 * Changing information about apartment by admin.
	 * @param id_apartment
	 * @param number_room
	 * @param category
	 * @param positions
	 * @param cost
	 * @throws DAOException
	 */
	void changeApartmentData(int id_apartment, int number_room, String category, int positions, int cost) throws DAOException;
	
	/**
	 * Deleting apartment by its id by admin.
	 * @param id_apartment
	 * @throws DAOException
	 */
	public void deleteApartmentById(int id_apartment) throws DAOException;
	
	/**
	 * Adding a new apartment by admin.
	 * @param apartment
	 * @throws DAOException
	 */
	public void addApartment(Apartment apartment) throws DAOException;
}
