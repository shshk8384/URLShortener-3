package com.digitalmorphosis.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.digitalmorphosis.constants.ApplicationConstants;
import com.digitalmorphosis.dto.URLMappingDTO;
import com.digitalmorphosis.exception.ConnectionException;
import com.digitalmorphosis.exception.ExceptionHelper;

/**
 * The Class DBExecutor.
 * 
 * @author cooperc
 * @version 0.1
 * @date 21/09/2012
 */
public class DBExecutor {
	
	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/** The instance. */
	private static DBExecutor instance;
	
	/** The connection. */
	private Connection connection = null;

	/**
	 * Gets the single instance of DBExecutor.
	 *
	 * @return single instance of DBExecutor
	 * @throws ClassNotFoundException the class not found exception
	 * @throws ConnectionException the connection exception
	 */
	public static DBExecutor getInstance() throws ClassNotFoundException, ConnectionException {

		if (instance == null) {
			instance = new DBExecutor();
		}
		return instance;
	}

	/**
	 * Insert.
	 *
	 * @param insert the insert
	 * @param urlMappingDTO the url mapping dto
	 * @return true, if successful
	 * @throws SQLException the sQL exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public boolean insert(String insert, URLMappingDTO urlMappingDTO) throws SQLException, ClassNotFoundException {

		log.info(ApplicationConstants.STARTED + "insert");
		
		boolean successful = false;
		PreparedStatement preparedStatement = null;

		try {
			
			setupConnection();
			
			if (connection != null) {
				
				log.debug("SQL Insert: " + insert);
				preparedStatement = connection.prepareStatement(insert);
				preparedStatement.setInt(1, urlMappingDTO.getId());
				preparedStatement.setString(2, urlMappingDTO.getShortURL());
				preparedStatement.setString(3, urlMappingDTO.getLongURL());
				boolean result = preparedStatement.execute();
				
				if(!result && (preparedStatement.getUpdateCount() == 1)){
					successful = true;
				}
				
			}
		} catch (SQLException sqlException) {
			successful = false;
			sqlException.printStackTrace();
		} catch (ClassNotFoundException classNotFoundException) {
			throw new ClassNotFoundException();
		}finally{
			log.debug("About to close the database connection");
			closeConnection();
		}
		log.info(ApplicationConstants.FINISHED + "insert");
		return successful;
	}

	/**
	 * Select.
	 *
	 * @param query the query
	 * @return the result set
	 * @throws SQLException the sQL exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public ResultSet select(String query) throws SQLException, ClassNotFoundException {

		log.info(ApplicationConstants.STARTED + "select");
		
		Statement statement = null;
		ResultSet resultSet = null;

			try {
				
				setupConnection();
				
				if (connection != null) {
					log.debug("SQL Query: " + query);
					statement = connection.createStatement();
					resultSet = statement.executeQuery(query);
				}
			} catch (SQLException sqlException) {
				log.error(ExceptionHelper.printStackTrace(sqlException));
			} catch (ClassNotFoundException classNotFoundException) {
				throw new ClassNotFoundException();
			} finally {
				log.debug("About to close the database connection");
				closeConnection();
			}
		
		
		log.info(ApplicationConstants.FINISHED + "select");
		return resultSet;
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Sets the connection.
	 *
	 * @param connection the new connection
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * Setup connection.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws ConnectionException the connection exception
	 */
	public void setupConnection() throws ClassNotFoundException, ConnectionException{
		try {
			setConnection(DatabaseConnection.getInstance().getConnection());
		} catch (ClassNotFoundException classNotFoundException) {
			log.error(ExceptionHelper.printStackTrace(classNotFoundException));
			throw new ClassNotFoundException();
		} catch (ConnectionException connectionException) {
			log.error(ExceptionHelper.printStackTrace(connectionException));
			throw new ConnectionException();
		}
	}
	
	/**
	 * Close connection.
	 */
	public void closeConnection(){
		log.info(ApplicationConstants.STARTED + "closeConnection");
		try {
			connection.close();
		} catch (SQLException sqlException) {
			log.error(ExceptionHelper.printStackTrace(sqlException));
		}
		log.info(ApplicationConstants.FINISHED + "closeConnection");
	}

}
