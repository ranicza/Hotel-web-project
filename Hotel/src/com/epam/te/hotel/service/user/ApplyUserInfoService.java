package com.epam.te.hotel.service.user;

import java.util.regex.Pattern;

import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.dao.DAOException;
import com.epam.te.hotel.model.dao.IUserDAO;
import com.epam.te.hotel.model.dao.sql.UserDAO;
import com.epam.te.hotel.model.entity.User;
import com.epam.te.hotel.service.ServiceException;
/**
 * Contains all logic associated with changing information about user.
 */
public class ApplyUserInfoService {

	private static final String EMAIL_VALIDATION = "^([A-Za-z0-9_\\.-]+)@([A-Za-z0-9_\\.-]+)\\.([A-Za-z\\.]{2,6})$";
	private static final String NAME_VALIDATION = "^[А-яЁёa-zA-Z]{1,20}$";
	private static final String SURNAME_VALIDATION = "^[А-яЁёa-zA-Z]{1,20}$";
	private static final String PATRONYMIC_VALIDATION = "^[А-яЁёa-zA-Z]{1,20}$";
	private static final String PASSPORT_VALIDATION = "^[а-яА-ЯёЁa-zA-Z]{2}[0-9]{7}$";

	/**
	 * Change user information. 
	 * Check if written data by user from the text area on the jsp page are correct, otherwise
	 * throw new ServiceException.
	 * @param user
	 * @param id_user
	 * @param name
	 * @param surname
	 * @param patronymic
	 * @param email
	 * @param passport
	 * @throws ServiceException
	 */
	public static void applyUserInfo(User user, int id_user, String name, String surname, String patronymic, String email, String passport) throws ServiceException{
		IUserDAO userDAO = new UserDAO();
		if (id_user != 0){
			try{
				validation(user, name, surname, patronymic, email, passport);
				userDAO.changeUserData(id_user, name, surname, patronymic, email, passport);
				
			}catch (NumberFormatException e) {
				ConfigManager.getInstance().getProperty(
						ConfigManager.CHANGE_USER_INFO_ERROR_MESSAGE);
				throw new ServiceException(e.getMessage(), e);
			}catch (DAOException e){
				ConfigManager.getInstance().getProperty(
						ConfigManager.CHANGE_USER_INFO_ERROR_MESSAGE);
				throw new ServiceException(e.getMessage(), e);
			}
		} else{
			ConfigManager.getInstance().getProperty(ConfigManager.CHANGE_USER_INFO_ERROR_MESSAGE);
		}
	}
	
	public static User findUserByLogin(String login) throws ServiceException{
		IUserDAO userDAO = new UserDAO();
		User user = null;
		if(!login.isEmpty()){
			try {
				user =  userDAO.findUserByLogin(login);
			} catch (DAOException e) {
				throw new ServiceException(e.getMessage(), e);
			}
		}
		return user;
	}
	
	private static void validation(User user,  String name, String surname, String patronymic, String email, String passport) throws ServiceException{
		fieldValidation(EMAIL_VALIDATION, ConfigManager.EMAIL_IS_NOT_VALID, email);
		fieldValidation(NAME_VALIDATION, ConfigManager.NAME_IS_NOT_VALID,name);
		fieldValidation(SURNAME_VALIDATION, ConfigManager.SURNAME_IS_NOT_VALID, surname);
		fieldValidation(PATRONYMIC_VALIDATION, ConfigManager.PATRONYMIC_IS_NOT_VALID, patronymic);
		fieldValidation(PASSPORT_VALIDATION, ConfigManager.PASSPORT_IS_NOT_VALID, passport);
	}
	
	private static void fieldValidation(String pattern, String errorMessage, String field) throws ServiceException{
		if (!Pattern.matches(pattern, field)) {
			throw new ServiceException(ConfigManager.getInstance().getProperty(errorMessage));
		}
	}
}
