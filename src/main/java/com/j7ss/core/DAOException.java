/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.core;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public DAOException() {
		super();
	}
	
	public DAOException(String message){
		super(message);
	}
	
	public DAOException(Throwable e){
		super(e);
	}
	
	public DAOException(String message, Throwable e){
		super(message, e);
	}
}
