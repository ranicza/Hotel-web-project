package com.epam.te.hotel.model.dao;

import java.util.ArrayList;

import com.epam.te.hotel.model.entity.User;

/**
 * Interface IUserDAO for the work with user DAO level.
 */
public interface IUserDAO {

	/**
	 * Adds a new user.
	 * @param user
	 * @throws DAOException
	 */
	void addUser(User user) throws DAOException;
	
	/**
	 * Finds user by his unique login.
	 * @param login
	 * @return user
	 * @throws DAOException
	 */
	User findUserByLogin(String login) throws DAOException;

	/**
	 * Finds user by his login and password.
	 * @param login
	 * @param password
	 * @return user
	 * @throws DAOException
	 */
	User findUserByLoginPassword(String login, int password) throws DAOException;
	
	/**
	 * Finds user by his id.
	 * @param id_user
	 * @return user
	 * @throws DAOException
	 */
	User findUserById(int id_user) throws DAOException;
	
	/**
	 * Finds all existing users.
	 * @return list of all users
	 * @throws DAOException
	 */
	ArrayList<User> findAllUser() throws DAOException;
	
	/**
	 * Changing user role (access level) by his id by admin.
	 * @param id_user
	 * @param id_role
	 * @throws DAOException
	 */
	void changeUserRoleById(int id_user, int id_role)throws DAOException ;
	
	/**
	 * Change user personal information.
	 * @param id_user
	 * @param name
	 * @param surname
	 * @param patronymic
	 * @param email
	 * @param passport
	 * @throws DAOException
	 */
	void changeUserData(int id_user, String name, String surname, String patronymic, String email, String passport) throws DAOException;
}
