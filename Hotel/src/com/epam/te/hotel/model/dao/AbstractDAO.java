package com.epam.te.hotel.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.epam.te.hotel.dao.sql.pool.ConnectionPool;
/**
 * Abstract DAO 
 * <br/>
 * Get connection from pool of connections for DAO level,
 * release connection and close prepared statement.
 */
public abstract class AbstractDAO {
	protected ConnectionPool pool;

	 /**
	 * Instantiates class AbstractDAO and 
	 * gets the single instance of the pool of connections.
	 */
	public AbstractDAO() {
		pool = ConnectionPool.getInstance();
	}

    /**
     * Gets single connection for DAO level.
     * @return connection to use
     */
	public Connection getConnection() {
		return pool.takeConnection();
	}

	/**
	 * Close connection.
	 * @param connection
	 */
	public void releaseConnection(Connection connection) {
		if (connection != null) {
			ConnectionPool.getInstance().closeConnection(connection, null);
		}
	}

	/**
	 * Close prepared statement.
	 * @param preparedStatement
	 * @throws DAOException
	 */
	public void closePreparedStatement(PreparedStatement preparedStatement) throws DAOException {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage(), e);
			}
		}
	}
}
