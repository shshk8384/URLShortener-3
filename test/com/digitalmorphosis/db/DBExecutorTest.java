package com.digitalmorphosis.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.Test;

import com.digitalmorphosis.dto.URLMappingDTO;
import com.digitalmorphosis.exception.ConnectionException;

/**
 * The Class DBExecutorTest.
 * 
 * @author cooperc
 * @version 0.1
 * @date 21/09/2012
 */
public class DBExecutorTest {

	/**
	 * Test insert.
	 */
	@Test
	public void testInsert() {
		URLMappingDTO urlMappingDTO = new URLMappingDTO(100, "shortURL", "a Longer than Short URL");
		String insertStatement = "insert into url_mapping values(?,?,?)";
		
		try {
			boolean successful = DBExecutor.getInstance().insert(insertStatement, urlMappingDTO);
			Assert.assertEquals(true, successful);
			
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test select.
	 */
	@Test
	public void testSelect() {
		String selectStatement = "select * from url_mapping where id=100";
		ResultSet resultSet = null;
		try {
			resultSet = DBExecutor.getInstance().select(selectStatement);
			while(resultSet.next()){
				
				Assert.assertEquals("shortURL", resultSet.getString("short_url"));
			}
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
