package com.epam.te.hotel.service;

public class ServiceException extends Exception{
	/**
	 * Class ServiceException (the Service level exception).
	 * <br/>
	 * This must be thrown when there are problems creating in Service level.
	 */
	private static final long serialVersionUID = 4076954439815878392L;

	public ServiceException(){
		super();
	}
	
	public ServiceException(String message){
		super(message);
	}
	
	public ServiceException( String message, Exception e) {
		super(message, e);
	}
	public  ServiceException(Throwable cause) {
		super(cause);
	}
}
