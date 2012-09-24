package com.digitalmorphosis.encoder;

import org.apache.log4j.Logger;

import com.digitalmorphosis.constants.ApplicationConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class Base64.
 * 
 * @author cooperc
 * @version 0.1
 * @date 21/09/2012
 */
public class Base64 {
	
	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());
	
	/** The instance. */
	private static Base64 instance;
	
	/** The Constant ALPHABET. */
	private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/-".toCharArray();
	
	/** The Constant EQUALS_CHAR. */
	private static final char EQUALS_CHAR = '=';
	
	/** The to int. */
	private static int[] toInt = new int[128];
	
	static{
		for (int i = 0;i < ALPHABET.length;i++){
			toInt[ALPHABET[i]] = i;
		}
	}
	
	/**
	 * Gets the single instance of Base64.
	 *
	 * @return single instance of Base64
	 */
	public static Base64 getInstance(){
		
		if (instance == null){
			instance = new Base64();
		}
		return instance;
	}
	
	/**
	 * Encode.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String encode(String id){
		
		log.info(ApplicationConstants.STARTED + "encode");
		
		byte[] buff = id.getBytes();
		int size = buff.length;
		char[] encodedSequence = new char[((size + 2) / 3) * 4];
		int a = 0;
		int i = 0;
		
		while(i < size){
			
			byte b0 = buff[i++];
			byte b1 = (i < size) ? buff[i++] : 0;
			byte b2 = (i < size) ? buff[i++] : 0;
			
			int mask = 0x3F;
			encodedSequence[a++] = ALPHABET[(b0 >> 2) & mask];
			encodedSequence[a++] = ALPHABET[((b0 << 4) | ((b1 & 0xFF) >> 4)) & mask];
			encodedSequence[a++] = ALPHABET[((b1 << 2) | ((b2 & 0xFF) >> 6)) & mask];
			encodedSequence[a++] = ALPHABET[b2 & mask];
			
		}
		
		switch(size % 3){
		
		case 1 : encodedSequence[--a] = EQUALS_CHAR;
		case 2 : encodedSequence[--a] = EQUALS_CHAR;
		}
		
		log.info(ApplicationConstants.FINISHED + "encode");
		return new String(encodedSequence);
	}
}
