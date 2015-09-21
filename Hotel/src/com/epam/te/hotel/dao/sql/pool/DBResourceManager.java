package com.epam.te.hotel.dao.sql.pool;

import java.util.ResourceBundle;

/** 
 * Resource manager for the get connection with database.
*/
public class DBResourceManager {
	
	private static final String PATH_TO_RESOURCES = "resources.db";	
	
	private static final DBResourceManager instance = new DBResourceManager();
	
	private ResourceBundle bundle = ResourceBundle.getBundle(PATH_TO_RESOURCES);
	
	/**
	 * Gets the single instance of the Resource manager for database properties.
	 * @return single instance of DBResourceManager
	 */
	public static DBResourceManager getInstance(){
		return instance;
	}
	
	/**
	 * Gets the value from the resource bundle.
	 * @param key
	 * @return String with the property
	 */
	public String getValue(String key){
		return bundle.getString(key);
	}
	
	

}
