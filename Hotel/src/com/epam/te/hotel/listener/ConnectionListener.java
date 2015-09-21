package com.epam.te.hotel.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.epam.te.hotel.dao.sql.pool.ConnectionPool;

/**
 * Application Lifecycle Listener implementation class ConnectionListener
 *<br/>
 *Listener for initialization Connection.
 */
public class ConnectionListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ConnectionListener() {
    }

	/**
	 * Calls method initPoolData() from the class ConnectionPool which 
	 * initialize (create and fill) pool of connections.
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         ConnectionPool.getInstance().initPoolData();
    }

	/**
	 * Calls method dispose() from class ConnectionPool which
	 * clear blocking queues of connections.
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         ConnectionPool.getInstance().dispose();
    }
	
}
