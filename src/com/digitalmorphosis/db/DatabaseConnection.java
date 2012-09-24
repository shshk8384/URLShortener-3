package com.digitalmorphosis.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.digitalmorphosis.constants.ApplicationConstants;
import com.digitalmorphosis.exception.ConnectionException;
import com.digitalmorphosis.exception.ExceptionHelper;

/**
 * The Class DatabaseConnection.
 * 
 * @author cooperc
 * @version 0.1
 * @date 21/09/2012
 */
public class DatabaseConnection {

	/** The log. */
	private Logger log  = Logger.getLogger(this.getClass());
	
	/** The connection. */
	private Connection connection = null;
	
	/** The instance. */
	private static DatabaseConnection instance;
	
	/**
	 * Gets the single instance of DatabaseConnection.
	 *
	 * @return single instance of DatabaseConnection
	 */
	public static DatabaseConnection getInstance(){
		
		if (instance == null){
			instance = new DatabaseConnection();
		}
		return instance;
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws ClassNotFoundException the class not found exception
	 * @throws ConnectionException the connection exception
	 */
	public Connection getConnection() throws ClassNotFoundException, ConnectionException {
		
		log.info(ApplicationConstants.STARTED + "getConnection");

		try {

			Class.forName(ApplicationConstants.HSQLDB);
			connection = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/xdb", "sa", "");
		} catch (SQLException sqlException) {
			log.error(ExceptionHelper.printStackTrace(sqlException));
			throw new ConnectionException();
		} catch (ClassNotFoundException classNotFoundException) {
			log.error(ExceptionHelper.printStackTrace(classNotFoundException));
			throw new ClassNotFoundException();
		} 
		
		log.info(ApplicationConstants.FINISHED + "getConnection");
		return connection;
	}
}
