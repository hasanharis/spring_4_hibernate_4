package com.umarashfaq.tyrion.util;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;


/**
 * This is wrapper class which will provide the utility functions to get the messages
 * from the resource properties files
 * 
 * @author muhammad.javeed
 *
 */

@Component
public class MessageBundleWrapper {
	
	@Autowired
	MessageSource messageSource;
	
	/**
	 * This function returns the message provided in the resource bundle/properties files
	 * against a "key" passed as parameter. Default Locale is US.   
	 * @param key
	 * @return
	 */
	public String getMessage(String key) {
		return getMessage(key, null);
	}
	
	/**
	 * This function returns the message provided in the resource bundle/properties files
	 * against a "key" passed as parameter and the arguments passed also added in the message 
	 * accordingly. Default Locale is US.     
	 * @param key
	 * @param arguments
	 * @return
	 */
	public String getMessage(String key, Object[] arguments) {
		return getMessage(key, arguments, Locale.US);
	}
	
	/**
	 * This function returns the message provided in the resource bundle/properties files
	 * against a "key" passed as parameter, the arguments passed also added in the message 
	 * accordingly and message will be according to the passed Locale type.      
	 * @param key
	 * @param arguments
	 * @param locale
	 * @return
	 */
	public String getMessage(String key, Object[] arguments, Locale locale) {
		String msg = this.messageSource.getMessage(key, arguments, locale);
		if (msg != null) {
			return msg.trim();
		}
		else {
			return null;
		}
	}
	
}

