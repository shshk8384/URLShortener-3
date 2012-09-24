package com.digitalmorphosis.encode;

import static org.junit.Assert.*;

import org.junit.Test;

import com.digitalmorphosis.encoder.Base64;

/**
 * The Class Base64Test.
 * 
 * @author cooperc
 * @version 0.1
 * @date 21/09/2012
 */
public class Base64Test {
	
	/** The Constant EQUALS. */
	public static final String EQUALS = "=";
	
	/** The Constant ALPHABET. */
	private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/-".toCharArray();
	
	/** The to int. */
	private static int[] toInt = new int[128];
	
	static{
		for (int i = 0;i < ALPHABET.length;i++){
			toInt[ALPHABET[i]] = i;
		}
	}

	/**
	 * Test.
	 */
	@Test
	public void testEncode() {
		
		String testString = "testString";
		
		String encodedString = Base64.getInstance().encode(testString);
		
		String decodedString = new String(decode(encodedString));
		
		assertEquals(testString, decodedString);
	}
	
	/**
	 * Decode.
	 *
	 * @param string the string
	 * @return the byte[]
	 */
	public byte[] decode(String string){
		
		int delta = string.endsWith(EQUALS + EQUALS) ? 2 : string.endsWith(EQUALS) ? 1 : 0;
		byte[] decodedSequence = new byte[string.length()*3/4 - delta];
		int mask = 0xFF;
		int index = 0;
		
		for (int i = 0 ; i < string.length(); i += 4){
			
			int c0 = toInt[string.charAt(i)];
			
			int c1 = toInt[string.charAt(i + 1)];
			decodedSequence[index++] = (byte) (((c0 << 2) | (c1 >> 4)) & mask);
			if(index >= decodedSequence.length){
				return decodedSequence;
			}
			
			int c2 = toInt[string.charAt(i + 2)];
			decodedSequence[index++] = (byte) (((c1 << 4) | (c2 >> 2)) & mask);
			if (index >= decodedSequence.length){
				return decodedSequence;
			}
			
			int c3 = toInt[string.charAt(i + 3)];
			decodedSequence[index++] = (byte) (((c2 << 6) | c3) & mask);
		}
		
		return decodedSequence;
	}

}
