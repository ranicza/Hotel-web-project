package com.epam.te.hotel.model.dao;
/**
 * Class DAOException (the DAO level exception).
 * <br/>
 * This must be thrown when there are problems creating in DAO level.
 */
public class DAOException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public DAOException(){
		super();
	}
	
	public DAOException(String message){
		super(message);
	}
	
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DAOException(Throwable cause) {
		super(cause);
	}
}
