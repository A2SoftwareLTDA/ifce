/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.core.email;

import java.util.Properties;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
public class MailProperties extends Properties{
	private static final long serialVersionUID = 1L;
	
	public MailProperties() {
		put("mail.smtp.port", "587");
		put("mail.smtp.auth", "true");
		put("mail.smtp.starttls.enable", "true");
	}
}
