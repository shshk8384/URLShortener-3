package com.digitalmorphosis.exception;

import java.sql.SQLException;

/**
 * The Class ConnectionException.
 * @author cooperc
 * @version 0.1
 * @date 21/09/2012
 */
public class ConnectionException extends SQLException{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6803872819360618488L;
	
	/** The connection exception. */
	private String connectionException;
	
	/**
	 * Instantiates a new connection exception.
	 */
	public ConnectionException(){
		super();
		connectionException = "Unknown";
	}
	
	/**
	 * Instantiates a new connection exception.
	 *
	 * @param connectionException the connection exception
	 */
	public ConnectionException(String connectionException){
		super();
		this.connectionException = connectionException;
	}
	
	/**
	 * Gets the connection exception.
	 *
	 * @return the connection exception
	 */
	public String getConnectionException(){
		return connectionException;
	}
}
