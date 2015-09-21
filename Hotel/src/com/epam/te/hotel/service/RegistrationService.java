package com.epam.te.hotel.service;

import java.util.regex.Pattern;

import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IUserDAO;
import com.epam.te.hotel.model.dao.sql.UserDAO;
import com.epam.te.hotel.model.entity.User;
/**
 * Contains all logic associated with registration.
 */
public class RegistrationService {
	
	private static final String LOGIN_VALIDATION = "^[А-яЁёa-zA-Z0-9_-]{3,20}$";
	private static final String PASSWORD_VALIDATION = "^[a-z0-9_-]{5,20}$";
	private static final String EMAIL_VALIDATION = "^([A-Za-z0-9_\\.-]+)@([A-Za-z0-9_\\.-]+)\\.([A-Za-z\\.]{2,6})$";
	private static final String NAME_VALIDATION = "^[А-яЁёa-zA-Z]{1,20}$";
	private static final String SURNAME_VALIDATION = "^[А-яЁёa-zA-Z]{1,20}$";
	private static final String PATRONYMIC_VALIDATION = "^[А-яЁёa-zA-Z]{1,20}$";
	private static final String PASSPORT_VALIDATION = "^[а-яА-ЯёЁa-zA-Z]{2}[0-9]{7}$";
	
	/**
	 * Registrate user in the system in case he doesn't exist. 
	 * Find user by his unique login and if he doesn't
	 * exist validate written personal parametars by user
	 * in the jsp page and add him to the system.
	 * @param user
	 * @param password
	 * @param password_repeated
	 * @throws ServiceException
	 */
	public static void registration(User user, String password, String password_repeated) throws ServiceException {	
		IUserDAO userDAO = new UserDAO();	
		try{
			validation(user, password, password_repeated);
			if (userDAO.findUserByLogin(user.getLogin()) == null) {
				userDAO.addUser(user);		
			}
		}catch (NumberFormatException e) {
			throw new ServiceException(e.getMessage(), e);
		} catch(DAOException e){
			throw new ServiceException(e.getMessage(), e);
		}
	}
		
	private static void validation(User user, String password, String password_repeat) throws ServiceException{
		fieldValidation(LOGIN_VALIDATION, ConfigManager.LOGIN_IS_NOT_VALID, user.getLogin());
		passwordValidation(password, password_repeat);
		fieldValidation(EMAIL_VALIDATION, ConfigManager.EMAIL_IS_NOT_VALID, user.getEmail());
		fieldValidation(NAME_VALIDATION, ConfigManager.NAME_IS_NOT_VALID, user.getName());
		fieldValidation(SURNAME_VALIDATION, ConfigManager.SURNAME_IS_NOT_VALID, user.getSurname());
		fieldValidation(PATRONYMIC_VALIDATION, ConfigManager.PATRONYMIC_IS_NOT_VALID, user.getPatronymic());
		fieldValidation(PASSPORT_VALIDATION, ConfigManager.PASSPORT_IS_NOT_VALID, user.getPassport());
	}	
	
	private static void fieldValidation(String pattern, String errorMessage, String field) throws ServiceException{
		if (!Pattern.matches(pattern, field)) {
			throw new ServiceException(ConfigManager.getInstance().getProperty(errorMessage));
		}
	}
	
	private static void passwordValidation(String password, String password_repeated) throws ServiceException{
		if (!password.equals(password_repeated) || !Pattern.matches(PASSWORD_VALIDATION, password)) {
			throw new ServiceException(ConfigManager.getInstance().getProperty(ConfigManager.PASSWORD_IS_NOT_VALID));
		}
	}
}
