package com.digitalmorphosis.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.Test;

import com.digitalmorphosis.db.DBExecutor;
import com.digitalmorphosis.dto.URLMappingDTO;
import com.digitalmorphosis.exception.ConnectionException;

/**
 * The Class URLMappingDAOTest.
 * 
 * @author cooperc
 * @version 0.1
 * @date 21/09/2012
 */
public class URLMappingDAOTest {

	/**
	 * Test presist url data.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 */
	@Test
	public void testPresistURLData() throws ClassNotFoundException, SQLException {
		
		URLMappingDTO urlMappingDTO = new URLMappingDTO(101, "MTe=", "/test/data/url/mapping/to/hello/world");
		URLMappingDTO urlMappingDTOTest = new URLMappingDTO();
		URLMappingDAO urlMappingDAO = new URLMappingDAO();
		urlMappingDAO.presistURLData(urlMappingDTO);
		
		ResultSet rs = null;
		
		rs = DBExecutor.getInstance().select("select * from url_mapping where id = 101");
		
		while(rs.next()){
			urlMappingDTOTest.setId(rs.getInt("id"));
			urlMappingDTOTest.setShortURL(rs.getString("short_url"));
			urlMappingDTOTest.setLongURL(rs.getString("long_url"));
		}
		
		Assert.assertEquals(urlMappingDTO.toString(), urlMappingDTOTest.toString());
	}

	/**
	 * Test is present.
	 *
	 * @throws ConnectionException the connection exception
	 * @throws SQLException the sQL exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	@Test
	public void testIsPresent() throws ConnectionException, SQLException, ClassNotFoundException {

		URLMappingDTO urlMappingDTO = new URLMappingDTO(101, "MTe=", "/test/data/url/mapping/to/hello/world");
		URLMappingDTO urlMappingDTOTest = new URLMappingDTO();
		URLMappingDAO urlMappingDAO = new URLMappingDAO();
		
		urlMappingDTOTest = urlMappingDAO.isPresent("/test/data/url/mapping/to/hello/world");
		
		Assert.assertEquals(urlMappingDTO.toString(), urlMappingDTOTest.toString());
	}

	/**
	 * Test get next id.
	 *
	 * @throws SQLException the sQL exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	@Test
	public void testGetNextID() throws SQLException, ClassNotFoundException {
		
		URLMappingDTO urlMappingDTO = new URLMappingDTO(101, "MTe=", "/test/data/url/mapping/to/hello/world");
		URLMappingDAO urlMappingDAO = new URLMappingDAO();
		
		Integer nextID = urlMappingDAO.getNextID();
		
		Assert.assertEquals(Integer.toString(urlMappingDTO.getId()+1), Integer.toString(nextID));
	}

}
