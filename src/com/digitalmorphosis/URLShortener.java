package com.digitalmorphosis;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.digitalmorphosis.constants.ApplicationConstants;
import com.digitalmorphosis.dao.URLMappingDAO;
import com.digitalmorphosis.dto.URLMappingDTO;
import com.digitalmorphosis.encoder.Base64;
import com.digitalmorphosis.exception.ConnectionException;

/**
 * The Class URLShortener.
 * 
 * @author cooperc
 * @version 0.1
 * @date 21/09/2012
 */
public class URLShortener {

	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * Instantiates a new uRL shortener.
	 */
	public URLShortener() {
		
	}

	/**
	 * Gets the short url.
	 *
	 * @param longURL the long url
	 * @return the short url
	 */
	public String getShortURL(String longURL) {

		log.info(ApplicationConstants.STARTED + "getShortURL");

		String shortURL = "";

		// create the data access object
		URLMappingDAO urlMappingDAO = new URLMappingDAO();

		// Check that we may have already generated this URL
		URLMappingDTO urlMappingDTO;

		try {
			urlMappingDTO = urlMappingDAO.isPresent(longURL);
		} catch (SQLException sqlException) {
			log.debug("Unable to query database: Generating URL anyway!");
			urlMappingDTO = null;
		} catch (ClassNotFoundException classNotFoundException) {
			log.debug("Unable to get a database connection");
			urlMappingDTO = null;
		}

		if (urlMappingDTO != null) {
			log.debug(urlMappingDTO.toString());
			shortURL = urlMappingDTO.getShortURL();
		} else {
			log.debug("Calling generateShortURL()");
			shortURL = generateShortURL(longURL);
		}

		log.info(ApplicationConstants.FINISHED + "getShortURL");
		return shortURL;
	}

	/**
	 * Gets the long url.
	 *
	 * @param shortURL the short url
	 * @return the long url
	 */
	public String getLongURL(String shortURL) {

		log.info(ApplicationConstants.STARTED + "getLongURL");

		String longURL = "";

		URLMappingDAO urlMappingDAO = new URLMappingDAO();

		// Check that we may have already generated this URL
		URLMappingDTO urlMappingDTO;
		try {
			urlMappingDTO = urlMappingDAO.isPresent(longURL);
		} catch (SQLException sqlDeveloper) {
			urlMappingDTO = null;
		} catch (ClassNotFoundException classNotFoundException) {
			log.debug("Unable to get a database connection");
			urlMappingDTO = null;
		}

		if (urlMappingDTO != null) {
			log.debug("URL Mapping DTO: " + urlMappingDTO.toString());
			longURL = urlMappingDTO.getLongURL();
		} else {
			log.debug("Not found: " + shortURL);
		}
		
		log.info(ApplicationConstants.FINISHED + "getShortURL");
		return longURL;
	}

	/**
	 * Generate short url.
	 *
	 * @param longURL the long url
	 * @return the string
	 */
	private String generateShortURL(String longURL) {

		log.info(ApplicationConstants.STARTED + "generateShortURL");

		URLMappingDTO urlMappingDTO = new URLMappingDTO();
		URLMappingDAO urlMappingDAO = new URLMappingDAO();

		// set the long URL within the DTO
		urlMappingDTO.setLongURL(longURL);

		// get the next id from the database
		try {
			urlMappingDTO.setId(urlMappingDAO.getNextID());
		} catch (SQLException e) {
			log.debug("Unable to retrieve next database ID from the database");
			// unable to retrieve next database ID
			// return null to show maintenance page
			return null;
		} catch (ClassNotFoundException classNotFoundException) {
			log.debug("Unable to get a database connection");
			urlMappingDTO = null;
		}

		// generate the unique short URL
		urlMappingDTO.setShortURL(Base64.getInstance().encode(
				Integer.toString(urlMappingDTO.getId())));

		log.debug("ShortURL: " + urlMappingDTO.getShortURL());

		// commit to the database
		try {
			urlMappingDAO.presistURLData(urlMappingDTO);
		} catch (ClassNotFoundException classNotFoundException) {
			log.debug("Unable to get a database connection");
			urlMappingDTO = null;
		} catch (ConnectionException connectionException) {
			log.debug("Unable to get a database connection");
			urlMappingDTO = null;
		} catch (SQLException sqlException) {
			log.debug("");
			urlMappingDTO = null;
		}

		log.info(ApplicationConstants.FINISHED + "generateShortURL");
		return urlMappingDTO.getShortURL();
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		URLShortener urlShortener = new URLShortener();
		urlShortener
				.getShortURL("/jhjkhfkd/sjkdhkhk/zdjhjhdgjhd/djhjkfhjkhdjk/4w5436tfrgdfgfd");
	}

}
