package com.digitalmorphosis.dto;

import java.io.Serializable;

/**
 * The Class URLMappingDTO.
 * 
 * @author cooperc
 * @version 0.1
 * @date 21/09/2012
 */
public class URLMappingDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3193842455462876592L;
	
	/**
	 * Instantiates a new uRL mapping dto.
	 */
	public URLMappingDTO() {
		
	}
	
	/**
	 * Instantiates a new uRL mapping dto.
	 *
	 * @param id the id
	 * @param shortURL the short url
	 * @param longURL the long url
	 */
	public URLMappingDTO(Integer id, String shortURL, String longURL){
		this.id = id;
		this.shortURL = shortURL;
		this.longURL = longURL;
	}
	
	/** The id. */
	private Integer id;
	
	/** The short url. */
	private String shortURL;
	
	/** The long url. */
	private String longURL;
	
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Gets the short url.
	 *
	 * @return the short url
	 */
	public String getShortURL() {
		return shortURL;
	}
	
	/**
	 * Sets the short url.
	 *
	 * @param shortURL the new short url
	 */
	public void setShortURL(String shortURL) {
		this.shortURL = shortURL;
	}
	
	/**
	 * Gets the long url.
	 *
	 * @return the long url
	 */
	public String getLongURL() {
		return longURL;
	}
	
	/**
	 * Sets the long url.
	 *
	 * @param longURL the new long url
	 */
	public void setLongURL(String longURL) {
		this.longURL = longURL;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("URLMappingDTO[");
		builder.append("ID:" + Integer.toString(getId()));
		builder.append(", Short URL:" + getShortURL());
		builder.append(", Long URL:" + getLongURL());
		builder.append("]");
		
		return builder.toString();
	}
}
