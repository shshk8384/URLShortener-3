package com.digitalmorphosis.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.digitalmorphosis.constants.ApplicationConstants;
import com.digitalmorphosis.db.DBExecutor;
import com.digitalmorphosis.dto.URLMappingDTO;
import com.digitalmorphosis.exception.ExceptionHelper;

/**
 * The Class URLMappingDAO.
 * 
 * @author cooperc
 * @version 0.1
 * @date 21/09/2012
 */
public class URLMappingDAO {
	
	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * Presist url data.
	 *
	 * @param urlMappingDTO the url mapping dto
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 */
	public void presistURLData(URLMappingDTO urlMappingDTO) throws ClassNotFoundException, SQLException{
		
		log.info(ApplicationConstants.STARTED + "presistURLData");
		
		boolean successful = false;
		
		if (urlMappingDTO != null){
			
			String insertStatement = "insert into url_mapping "
					+ "values (?,?,?)";
			
			log.debug("Insert statement: " + insertStatement);
			
			successful = DBExecutor.getInstance().insert(insertStatement, urlMappingDTO);
			
			if (successful){
				log.debug("Successfully commited data");
			}else{
				log.debug("Unable to write: " + urlMappingDTO.toString() + " to the database!");
			}
		}else{
			log.debug("URL Mapping Object is NULL");
		}
		log.info(ApplicationConstants.FINISHED + "presistURLData");
	}
	
	/**
	 * Checks if is present.
	 *
	 * @param longURL the long url
	 * @return the uRL mapping dto
	 * @throws SQLException the sQL exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public URLMappingDTO isPresent(String longURL) throws SQLException, ClassNotFoundException{
		
		log.info(ApplicationConstants.STARTED + "isPresent");
		
		ResultSet resultSet = null;
		URLMappingDTO urlMappingDTO = null;
		
		try {
			
			String query = "select * from url_mapping where long_url='" + longURL + "'";
			
			resultSet = DBExecutor.getInstance().select(query);
			
			while (resultSet.next()){
				
				urlMappingDTO = new URLMappingDTO();
				
				urlMappingDTO.setId(resultSet.getInt("id"));
				urlMappingDTO.setShortURL(resultSet.getString("short_url"));
				urlMappingDTO.setLongURL(resultSet.getString("long_url"));

			}
			
		} catch (SQLException sqlException) {
			log.error(ExceptionHelper.printStackTrace(sqlException));
			throw new SQLException();
		}
		
		log.info(ApplicationConstants.FINISHED + "isPresent");
		return urlMappingDTO;
	}
	
	/**
	 * Gets the next id.
	 *
	 * @return the next id
	 * @throws SQLException the sQL exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public int getNextID() throws SQLException, ClassNotFoundException{
		
		log.info(ApplicationConstants.STARTED + "getNextID");
		
		int maxID = 0;
	
		try {
			// Should usually be placed within a configuration file
			ResultSet resultSet = DBExecutor.getInstance().select("select max(id) as max_id from url_mapping");
			while(resultSet.next()){
				maxID = resultSet.getInt("max_id");	
			}
		} catch (SQLException sqlException) {
			log.error(ExceptionHelper.printStackTrace(sqlException));
			throw new SQLException();
		}
		log.info(ApplicationConstants.FINISHED + "getNextID");
		return (maxID + 1);
	}
}
