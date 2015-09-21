package com.epam.te.hotel.logic;

/**
 * Contains the names of commands for execution.
 */
public enum CommandName {
	
	//Common names of commands
	LANGUAGE,
	LOG_IN,
	REGISTRATION,
	LOG_OUT,
	NO_SUCH_COMMAND,
	
	//Names of commands for client
	SHOW_CLIENT_BILL,
	PAYMENT,	
	SHOW_CLIENT_ORDER,
	SHOW_FIND_APARTMENT_FORM,
	FIND_APARTMENT,
	CREATE_ORDER,
	DELETE_ORDER,
	
	//Names of commands for user (admin & client)
	EDIT_USER_INFO,
	APPLY_USER_INFO,
	
	//Names of commands for admin
	ROOM_ADMINISTRATION,
	ADD_NEW_APARTMENT_FORM,
	ADD_APARTMENT,
	CHANGE_DATA_APARTMENT,
	DELETE_APARTMENT,
	APPLY_APARTMENT_DATA,
	SET_STATUS_ORDER,
	ORDER_ADMINISTRATION,
	USER_ADMINISTRATION,
	CHANGE_ROLE
}
