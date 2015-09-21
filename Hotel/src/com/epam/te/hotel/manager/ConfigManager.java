package com.epam.te.hotel.manager;

import java.util.ResourceBundle;

/** 
 * Configuration manager for the work with resource bundle and 
 * sending information messages on the jsp pages.
*/
public class ConfigManager {
	
	private static ConfigManager instance;
	private ResourceBundle resourceBundle;
	
	public static final String LOG4J_PATH = "log4j.path";	
	private static final String BUNDLE = "resources.config";
	
	// Error messages
	public static final String LOGIN_ERROR_MESSAGE = "login.error.message";
	public static final String PASSWORD_IS_NOT_VALID = "password.is.not.valid";
	public static final String LOGIN_IS_NOT_VALID = "login.is.not.valid";
	public static final String EMAIL_IS_NOT_VALID = "email.is.not.valid";
	public static final String NAME_IS_NOT_VALID = "name.is.not.valid";
	public static final String SURNAME_IS_NOT_VALID = "surname.is.not.valid";
	public static final String PATRONYMIC_IS_NOT_VALID = "patronymic.is.not.valid";
	public static final String PASSPORT_IS_NOT_VALID = "passport.is.not.valid";	
	public static final String REGISTRATION_ERROR_MESSAGE = "registration.error.message";
	public static final String REGISTRATION_WAS_INTERRUPTED_MESSAGE = "registration.was.interrupted";
	public static final String CHOOSE_ACTION_ERROR_MESSAGE = "choose.action.error.message";
	public static final String CHANGE_APARTMENT_STATUS_ERROR_MESSAGE = "change.room.status.error.message";
	public static final String SERVICE_EXCEPTION_ERROR_MESSAGE = "service.exception.error.message";
	public static final String CHANGE_STATUS_ORDER_EXCEPTION_MESSAGE = "change.status.order.exception.message";
	public static final String DELETE_ORDER_PROBLEM_MESSAGE = "delete.order.problem";
	public static final String BILL_PAID_ERROR_MESSAGE = "bill.paid.error.message";
	public static final String CHOOSE_CORRECT_DATE_MESSAGE = "uncorrect.date.message";	
	public static final String USER_NOT_FOUND_MESSAGE = "user.not.found";
	public static final String CHANGE_APARTMENT_DATA_ERROR_MESSAGE = "change.apartment.data.message";
	public static final String DELETE_APARTMENT_ERROR_MESSAGE = "delete.apartment.error.message";
	public static final String CHANGE_USER_INFO_ERROR_MESSAGE = "change.user.info.error.message";
	public static final String CHANGE_ROLE_ERROR_MESSAGE = "change.role.error.message";
	public static final String CAN_NOT_DELETE_ORDER_MESSAGE = "can.not.delete.order.message";
	public static final String WRITE_CORRECT_PARAMETERS_MESSAGE = "write.correct.parameter.message";
	public static final String INVALID_ROOMNUMBER_MESSAGE = "invalid.roomnumber.message";
	
	//Messages about successful operation	
	public static final String REGISTRATION_WAS_SUCCESSFUL_MESSAGE = "registration.was.successful";
	public static final String CREATE_ORDER_SUCCESS_MESSAGE = "create.order.success.message";
	public static final String CHANGE_STATUS_SUCCESS_MESSAGE = "change.status.success.message";
	public static final String CHANGE_ROLE_SUCCESS_MESSAGE = "change.role.success.message";
	public static final String BILL_ALREADY_PAID_MESSAGE = "bill.already.paid.message";
	public static final String PAYMENT_SUCCESS_MESSAGE = "payment.success.mesage";
	public static final String DELETE_ORDER_SUCCESS_MESSAGE = "delete.order.success.message";
	public static final String CHANGE_APARTMENT_DATA_SUCCESS_MESSAGE = "change.apartment.data.success";
	public static final String DELETE_APARTMENT_SUCCESS_MESSAGE = "delete.apartment.success.message";
	public static final String CHANGE_USER_INFO_SUCCESS_MESSAGE = "change.user.info.success";
	public static final String ADD_APARTMENT_SUCCESS_MESSAGE = "add.apartment.success";
	
	// Information messages
	public static final String NO_FREE_APARTMENT_MESSAGE = "no.free.apartment";
	public static final String DELETE_CONFIRMED_ORDER_MESSAGE = "delete.confirmed.order.message";
	public static final String DOES_NOT_HAVE_BILL_MESSAGE = "does.not.have.bill.message";
	public static final String DOES_NOT_HAVE_ANY_ORDER = "does.not.have.any.order";
	public static final String DOES_NOT_HAVE_FREE_ROOM_MESSAGE = "does.not.have.free.rooms";
	public static final String DOES_NOT_HAVE_SUCH_APARTMENT_MESSAGE = "does.not.have.such.apartment";
	public static final String DOES_NOT_HAVE_ACCESS_LEVEL_MESSAGE = "does.not.have.access.level";
	public static final String DOES_NOT_HAVE_SUCH_USER_MESSAGE = "does.not.have.such.user";
	public static final String BANNED_MESSAGE = "account.was.banned";

	/**
	 * Gets the single instance of the Configuration manager.
	 * @return single instance of DBResourceManager
	 */
	public static ConfigManager getInstance() {
		if (instance == null) {
			instance = new ConfigManager();
			instance.setResourceBundle(ResourceBundle.getBundle(BUNDLE));
		}
		return instance;
	}
	
	/**
	 * Gets resource boundle.
	 * @return resource bundle
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	
	/**
	 * Sets resource bundle.
	 * @param resourceBundle
	 */
	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}
	 
	/**
	 * Gets the property.
	 * @param property
	 * @return property
	 */
	public String getProperty(String property) {
		if (resourceBundle == null) {
			instance.setResourceBundle(ResourceBundle.getBundle(BUNDLE));
		}
		return (String) resourceBundle.getObject(property);
	}
	
}
