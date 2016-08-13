/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.core.email;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
public class MailApiException extends Exception{

	private static final long serialVersionUID = 1L;
	
	
	public MailApiException(String msg) {
		super(msg);
	}
	
	public MailApiException(Throwable t) {
		super(t);
	}
	
	public MailApiException(String msg, Throwable t) {
		super(msg, t);
	}

}
