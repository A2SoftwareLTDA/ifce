/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.core;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
public class Messages {

	private Messages() {
		throw new AssertionError();
	}
	
	private static ResourceBundle getResourceMap(){
		return ResourceBundle.getBundle( "com.j7ss.i18n.messages", WebContext.getLocale() );  
	}
	
	private static String getResourceValue( String key ){
		return getResourceMap().containsKey(key) ? getResourceMap().getString(key) : key;
	}
	
	public static String formatMessage( String key, String...param ){
		List<String> listParam = new ArrayList<String>();
		for (String string : param) {
			listParam.add( getResourceValue(string) );
		}
		return MessageFormat.format( getResourceValue( key ), listParam.toArray());   
	}
	
	private static void showMessage( Severity severity, String title, String key, String...param ){
		WebContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, formatMessage(title), formatMessage(key, param)));
	}
	
	private static void showGrowl( Severity severity, String title, String key, String...param ){
		WebContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, formatMessage(title), formatMessage(key, param)));
	}
	
	
// ## Messages
	public static void showMessageErro( String title, String key, String...param ){
		showMessage(FacesMessage.SEVERITY_ERROR, title, key, param);
	}
	
	public static void showMessageInfo( String title, String key, String...param ){
		showMessage(FacesMessage.SEVERITY_INFO, title, key, param);
	}
	
	public static void showMessageFatal( String title, String key, String...param ){
		showMessage(FacesMessage.SEVERITY_FATAL, title, key, param);
	}
	
	public static void showMessageWarn( String title, String key, String...param ){
		showMessage(FacesMessage.SEVERITY_WARN, title, key, param);
	}	
	
// ## Growl
	public static void showGrowlErro( String title, String key, String...param ){
		showGrowl(FacesMessage.SEVERITY_ERROR, title, key, param);
	}
	
	public static void showGrowlInfo( String title, String key, String...param ){
		showGrowl(FacesMessage.SEVERITY_INFO, title, key, param);
	}
	
	public static void showGrowlFatal( String title, String key, String...param ){
		showGrowl(FacesMessage.SEVERITY_FATAL, title, key, param);
	}
	
	public static void showGrowlWarn( String title, String key, String...param ){
		showGrowl(FacesMessage.SEVERITY_WARN, title, key, param);
	}
}
