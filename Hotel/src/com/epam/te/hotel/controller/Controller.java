package com.epam.te.hotel.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.te.hotel.dao.sql.pool.ConnectionPool;
import com.epam.te.hotel.logic.CommandException;
import com.epam.te.hotel.logic.CommandHelper;
import com.epam.te.hotel.logic.ICommand;


/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG= Logger.getLogger(Controller.class);
	private static final String COMMAND_NAME = "command";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);		
		try{
			ConnectionPool.getInstance();
		}catch (Exception e){
			LOG.error(e.getMessage(), e);
		}	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @param request
     * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		String commandName = request.getParameter(COMMAND_NAME);
		ICommand command = null;		
		String page = null;
		
		try{
			command = CommandHelper.getInstance().getCommand(commandName);
			if (command != null){
				page = command.execute(request);
			}			
		}catch (CommandException e){
			page = JspPageName.ERROR;
			LOG.error(e.getMessage(),e);
		}
		catch (Exception e){
			page = JspPageName.ERROR;
			LOG.error(e.getMessage(),  e);
		}
			
		getServletContext().getRequestDispatcher(page).forward(request,	response);
	}
}
