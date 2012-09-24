package com.digitalmorphosis.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import com.digitalmorphosis.constants.ApplicationConstants;

/**
 * The Class ExceptionHelper.
 * @author cooperc
 * @version 0.1
 * @date 21/09/2012
 */
public class ExceptionHelper {
	
	/**
	 * Prints the stack trace.
	 *
	 * @param throwable the throwable
	 * @return the string
	 */
	public static String printStackTrace(Throwable throwable){
		
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		throwable.printStackTrace(printWriter);
		
		return replaceNewLineCharacters(writer.toString());
	}
	
	/**
	 * Replace new line characters.
	 *
	 * @param string the string
	 * @return the string
	 */
	private static String replaceNewLineCharacters(String string){
		return string.replaceAll(ApplicationConstants.NEWLINE_REGEX, ApplicationConstants.PIPE);
	}

}
