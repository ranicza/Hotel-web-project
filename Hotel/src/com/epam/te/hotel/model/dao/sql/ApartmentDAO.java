package com.epam.te.hotel.model.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.te.hotel.model.dao.AbstractDAO;
import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IApartmentDAO;
import com.epam.te.hotel.model.entity.Apartment;
import com.epam.te.hotel.model.entity.enumeration.ApartmentCategory;
/**
 *Class ApartmentDAO for the work with the database.
 */
public class ApartmentDAO extends AbstractDAO implements IApartmentDAO {
	
	public static final String ID = "id_apartment";
	public static final String ROOM_NUMBER = "room_number";
	public static final String POSITIONS = "positions";
	public static final String CATEGORY = "category";
	public static final String STATUS = "status";
	public static final String COST = "cost";
	
	public static final String FIND_FREE_DATE = "SELECT * FROM 'apartment' INNER JOIN 'order' ON "
			+ "apartment.id_apartment = order.id_apartment WHERE order.date_arrival>? AND order.date_departure<?";
	
	public static final String FIND_APARTMENT_LIST = "SELECT * FROM `apartment`";
	
	public static final String FIND_APARTMENT_BY_ID = "SELECT * FROM `apartment` WHERE id_apartment=?";

	public static final String FIND_FREE_APARTMENT = "Select * from apartment a1 " + 
			"WHERE a1.id_apartment not in ( " +
			"SELECT id_apartment from `order` o1 " + 
			"where (? >= o1.date_arrival and ? <= o1.date_departure  ) " + 
			"or (? < o1.date_arrival and ? <= o1.date_departure) ) " ;
	
	public static final String FINDFREE_APARTMENT_POSITIONS_PREDICATE = " and a1.positions = ?  ";
	
	public static final String FINDFREE_APARTMENT_CATEGORY_PREDICATE = " and a1.category = ?  ";
	
	public static final String UPDATE_APARTMENT_DATA = "UPDATE apartment SET room_number = ?, positions = ?, category = ?, cost = ? WHERE id_apartment = ? ";
	
	public static final String DELETE_APARTMENT_BY_ID = "DELETE FROM `apartment` WHERE id_apartment = ?";
	
	public static final String INSERT_APARTMENT = "INSERT INTO `apartment` "
			+ "(room_number, positions, category, cost) VALUES(?,?,?,?)";
	
	/**
	 * Finds all apartments in the database.
	 * @return list of finding apartments
	 * @throws DAOException
	 */
	@Override
	public ArrayList<Apartment> findAllApartment() throws DAOException{
		ArrayList<Apartment> apartmentList = new ArrayList<Apartment>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(FIND_APARTMENT_LIST);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Apartment apartment = new Apartment();
				apartment.setId(Integer.parseInt(resultSet.getString(ID)));
				apartment.setRoomNumber(Integer.parseInt(resultSet.getString(ROOM_NUMBER)));
				apartment.setPositions(Integer.parseInt(resultSet.getString(POSITIONS)));
				apartment.setCategory(ApartmentCategory.valueOf(resultSet.getString(CATEGORY)));
				apartment.setCost(Integer.parseInt(resultSet.getString(COST)));	
				apartmentList.add(apartment);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);	
		}
		return apartmentList;
	}
	
	/**
	 * Find apartment by its id in the database.
	 * @param id_apartment
	 * @return apartment
	 * @throws DAOException
	 */
	@Override
	public Apartment findApartmentById(int id_apartment) throws DAOException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Apartment apartment = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(FIND_APARTMENT_BY_ID);
			preparedStatement.setInt(1, id_apartment);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				apartment = new Apartment();
				apartment.setId(Integer.parseInt(resultSet.getString(ID)));
				apartment.setRoomNumber(Integer.parseInt(resultSet.getString(ROOM_NUMBER)));
				apartment.setPositions(Integer.parseInt(resultSet.getString(POSITIONS)));
				apartment.setCategory(ApartmentCategory.valueOf(resultSet.getString(CATEGORY)));
				apartment.setCost(Integer.parseInt(resultSet.getString(COST)));
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);			
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return apartment;
	}
	
	/**
	 * Finds vacant apartments according chosen parameters by client in the database.
	 * @param arrival_date
	 * @param depart_date
	 * @param positions
	 * @param category
	 * @return list of findings apartments
	 * @throws DAOException
	 */
	@Override
	public List<Apartment> findVacantApartment(String arrival_date, String depart_date, Integer positions, String category) throws DAOException{
		ArrayList<Apartment> apartmentList = new ArrayList<Apartment>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			java.sql.Date start_ = java.sql.Date.valueOf(arrival_date);
			java.sql.Date end_ = java.sql.Date.valueOf(depart_date);
			connection = getConnection();
			String finalStatement = FIND_FREE_APARTMENT + (positions != null ? FINDFREE_APARTMENT_POSITIONS_PREDICATE : "" ) +
					(category != null ? FINDFREE_APARTMENT_CATEGORY_PREDICATE : "");
			preparedStatement = connection.prepareStatement(finalStatement);
			preparedStatement.setDate(1, start_);
			preparedStatement.setDate(2, start_ );
			preparedStatement.setDate(3, start_);
			preparedStatement.setDate(4, end_);
			
			if (positions != null ){ 
				preparedStatement.setInt(5, positions);
			}
			if (category != null) {
				preparedStatement.setString((positions == null ? 5 : 6), category);
			}
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Apartment apartment = new Apartment();
				apartment.setId(Integer.parseInt(resultSet.getString(ID)));
				apartment.setRoomNumber(Integer.parseInt(resultSet.getString(ROOM_NUMBER)));
				apartment.setPositions(Integer.parseInt(resultSet.getString(POSITIONS)));
				apartment.setCategory(ApartmentCategory.valueOf(resultSet.getString(CATEGORY)));
				apartment.setCost(Integer.parseInt(resultSet.getString(COST)));
				apartmentList.add(apartment);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);		
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return apartmentList;
	}
	
	/**
	 * Changing information about apartment by admin in the database.
	 * @param id_apartment
	 * @param number_room
	 * @param category
	 * @param positions
	 * @param cost
	 * @throws DAOException
	 */
	@Override
	public void changeApartmentData(int id_apartment, int number_room, String category, int positions, int cost) throws DAOException{
		PreparedStatement preparedStatement = null;
		Connection connection = null;		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_APARTMENT_DATA);
			preparedStatement.setInt(1, number_room);
			preparedStatement.setInt(2, positions);
			preparedStatement.setString(3, category);
			preparedStatement.setInt(4, cost);
			preparedStatement.setInt(5, id_apartment);			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);			
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
	}
	
	/**
	 * Deleting apartment by its id by admin in the database.
	 * @param id_apartment
	 * @throws DAOException
	 */
	@Override
	public void deleteApartmentById(int id_apartment) throws DAOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(DELETE_APARTMENT_BY_ID);
			preparedStatement.setInt(1, id_apartment);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
	}
	
	/**
	 * Adding a new apartment by admin.
	 * @param apartment
	 * @throws DAOException
	 */
	@Override
	public void addApartment(Apartment apartment) throws DAOException{
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(INSERT_APARTMENT);
			preparedStatement.setInt(1,apartment.getRoomNumber());
			preparedStatement.setInt(2, apartment.getPositions());
			preparedStatement.setString(3,apartment.getCategory().toString());
			preparedStatement.setInt(4,apartment.getCost());
			preparedStatement.executeUpdate();			
		}catch (SQLException e){
			throw new DAOException(e.getMessage(), e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
	}

}
