package com.epam.te.hotel.logic;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface ICommand
 * <br/>
 * Contains the method execute().
 */
public interface ICommand {
	
	/**This method reads a command from the request
     * and processes it. The result will be given as
     * a page to forward to.
	 * @param request
	 * @return String (name of the command)
	 * @throws CommandException 
	 */
	public String execute(HttpServletRequest request) throws CommandException;
}
