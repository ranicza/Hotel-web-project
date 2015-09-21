package com.epam.te.hotel.logic;
/**
 * Class CommandException (the Command level exception).
 * <br/>
 * This must be thrown when there are problems creating in Command level.
 */
public class CommandException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public CommandException(){
		super();
	}
	
	public CommandException(String message){
		super(message);
	}
	
	public CommandException( String message, Exception e) {
		super(message, e);
	}
}
