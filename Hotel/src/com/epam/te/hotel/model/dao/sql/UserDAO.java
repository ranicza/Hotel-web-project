package com.epam.te.hotel.model.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.epam.te.hotel.model.dao.AbstractDAO;
import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IUserDAO;
import com.epam.te.hotel.model.entity.Role;
import com.epam.te.hotel.model.entity.User;
/**
 *Class UserDAO for the work with the database.
 */
public class UserDAO extends AbstractDAO implements IUserDAO{
	
	public static final String USER_ID = "id_user";
	public static final String USER_LOGIN = "login";
	public static final String USER_PASSWORD = "password";
	public static final String USER_EMAIL = "email";
	public static final String USER_NAME = "name";
	public static final String USER_SURNAME = "surname";
	public static final String USER_PATRONYMIC = "patronymic";
	public static final String USER_PASSPORT = "passport";
	public static final String USER_ROLE = "role";	
	public static final String USER_ID_ROLE = "id_role";
	public static final String USER_ID_REGISTRATION = "id_registration";
	
	public static final String INSERT_USER = "INSERT INTO `user` (id_role,id_registration) VALUES(?,?)";
	
	public static final String INSERT_USER_INFO = "INSERT INTO `user` "
			+ "(login, password, email, name,surname,patronymic,passport, id_role) VALUES(?,?,?,?,?,?,?,?)";
	
	public static final String GET_USER_BY_LOGIN_PASSWORD = "SELECT * FROM `user` as u where "
			+ "u.login = ? AND u.password = ?";
	
	public static final String GET_USER_BY_LOGIN = "SELECT * FROM `user` as u WHERE u.login = ?";
	
	public static final String GET_USER_BY_ID = "SELECT * FROM `user` WHERE id_user = ?";
	
	public static final String GET_ALL_USER = "SELECT * FROM `user`";
	
	public static final String UPDATE_USER_ROLE = "UPDATE user SET id_role = ? WHERE id_user =?";
	
	public static final String UPDATE_USER_DATA = "UPDATE user SET name = ?, surname = ?, patronymic = ?, email = ?, passport = ? WHERE id_user = ? ";

	/**
	 * Finds user by his login and password in the database.
	 * @param login
	 * @param password
	 * @return user
	 * @throws DAOException
	 */
	@Override
	public User findUserByLoginPassword(String login, int password) throws DAOException{
		User user = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();		
			preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN_PASSWORD);
			preparedStatement.setString(1, login);
			preparedStatement.setLong(2, password);		
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {				
				user = new User();
				user.setId(Integer.parseInt(resultSet.getString(USER_ID)));
				user.setName(resultSet.getString(USER_NAME));
				user.setSurname(resultSet.getString(USER_SURNAME));
				user.setPatronymic(resultSet.getString(USER_PATRONYMIC));
				user.setPassport(resultSet.getString(USER_PASSPORT));
				user.setLogin(resultSet.getString(USER_LOGIN));
				user.setPassword(resultSet.getInt(USER_PASSWORD));
				user.setEmail(resultSet.getString(USER_EMAIL));
				user.setRole(Role.getRoleById(resultSet.getInt(USER_ID_ROLE)));
			}
		} catch (SQLException | NumberFormatException e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			closePreparedStatement(preparedStatement);	
			releaseConnection(connection);	
		}
		return user;
	}
	
	/**
	 * Adds a new user in the database.
	 * @param user
	 * @throws DAOException
	 */
	@Override
	public void addUser(User user) throws DAOException{
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(INSERT_USER_INFO);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setInt(2, user.getPassword());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getName());
			preparedStatement.setString(5, user.getSurname());
			preparedStatement.setString(6, user.getPatronymic());
			preparedStatement.setString(7, user.getPassport());		
			preparedStatement.setInt(8, user.getRole().getIdByRole());
			preparedStatement.executeUpdate();			
		}catch (SQLException e){
			throw new DAOException(e.getMessage(), e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
	}
	
	/**
	 * Finds user by his unique login in the database.
	 * @param login
	 * @return user
	 * @throws DAOException
	 */
	public User findUserByLogin(String login) throws DAOException{
		User user = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN);
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(Integer.parseInt(resultSet.getString(USER_ID)));
				user.setName(resultSet.getString(USER_NAME));
				user.setSurname(resultSet.getString(USER_SURNAME));
				user.setPatronymic(resultSet.getString(USER_PATRONYMIC));
				user.setPassport(resultSet.getString(USER_PASSPORT));
				user.setLogin(resultSet.getString(USER_LOGIN));
				user.setPassword(resultSet.getInt(USER_PASSWORD));
				user.setEmail(resultSet.getString(USER_EMAIL));
				user.setIdRole(resultSet.getInt(USER_ID_ROLE));
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return user;
	}	
	
	/**
	 * Finds user by his id in the database.
	 * @param id_user
	 * @return user
	 * @throws DAOException
	 */
	@Override
	public User findUserById(int id_user) throws DAOException {
		User user = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
			preparedStatement.setInt(1, id_user);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(Integer.parseInt(resultSet.getString(USER_ID)));
				user.setName(resultSet.getString(USER_NAME));
				user.setSurname(resultSet.getString(USER_SURNAME));
				user.setPatronymic(resultSet.getString(USER_PATRONYMIC));
				user.setPassport(resultSet.getString(USER_PASSPORT));
				user.setLogin(resultSet.getString(USER_LOGIN));
				user.setPassword(resultSet.getInt(USER_PASSWORD));
				user.setEmail(resultSet.getString(USER_EMAIL));
				user.setIdRole(resultSet.getInt(USER_ID_ROLE));
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return user;
	}
	
	/**
	 * Finds all existing users in the database.
	 * @return list of all users
	 * @throws DAOException
	 */
	@Override
	public ArrayList<User> findAllUser() throws DAOException {
		ArrayList<User> userList = new ArrayList<User>();
		User user = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(GET_ALL_USER);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = new User();
				user.setId(Integer.parseInt(resultSet.getString(USER_ID)));
				user.setName(resultSet.getString(USER_NAME));
				user.setSurname(resultSet.getString(USER_SURNAME));
				user.setPatronymic(resultSet.getString(USER_PATRONYMIC));
				user.setPassport(resultSet.getString(USER_PASSPORT));
				user.setLogin(resultSet.getString(USER_LOGIN));
				user.setPassword(resultSet.getInt(USER_PASSWORD));
				user.setEmail(resultSet.getString(USER_EMAIL));
				user.setIdRole(resultSet.getInt(USER_ID_ROLE));
				user.setRole(Role.getRoleById(resultSet.getInt(USER_ID_ROLE)));
				userList.add(user);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage() , e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return userList;
	}
	
	/**
	 * Changing user role (access level) by his id in the database.
	 * @param id_user
	 * @param id_role
	 * @throws DAOException
	 */
	@Override
	public void changeUserRoleById(int id_user, int id_role)throws DAOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_USER_ROLE);
			preparedStatement.setInt(1, id_role);
			preparedStatement.setInt(2, id_user);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
	}
	
	/**
	 * Change user personal information in the database.
	 * @param id_user
	 * @param name
	 * @param surname
	 * @param patronymic
	 * @param email
	 * @param passport
	 * @throws DAOException
	 */
	@Override
	public void changeUserData(int id_user, String name, String surname, String patronymic, String email, String passport) throws DAOException{
		PreparedStatement preparedStatement = null;
		Connection connection = null;		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_USER_DATA);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, patronymic);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, passport);
			preparedStatement.setInt(6, id_user);			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);			
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
	}
}
