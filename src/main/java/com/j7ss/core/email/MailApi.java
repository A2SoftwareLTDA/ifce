/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.core.email;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;


/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
public class MailApi{
	
	private static final String API = "key-12319d671287729839092d7d3a7c3cf7";
	private static final String URL = "https://api.mailgun.net/v3/j7ss.com/messages";
	private static final String EMAIL = "IFCE Estágios <postmaster@j7ss.com>";
	private WebResource webResource;
	private MultivaluedMapImpl formData;
	
	public MailApi(){
		Client client = Client.create();
	    client.addFilter(new HTTPBasicAuthFilter("api", API));
	    webResource = client.resource(URL);
	    formData = new MultivaluedMapImpl();
	    formData.add("from", EMAIL);
	}
	
	public MailApi subject(String subject){
		formData.add("subject", subject);
		return this;
	}
	
	public MailApi text(String text){
		formData.add("text", text);
		return this;
	}
	
	public MailApi html(String html){
		formData.add("html", html);
		return this;
	}
	
	public MailApi to(String email, String name){
		if(email != null){
			formData.add("to", (name==null?"":name)+" <"+email+">");
		}
		return this;
	}
	
	public ClientResponse send(){
		return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
	}
	
	
	
	
//	//***************************
//	//Static Methods
//	
//	public static boolean sendEmailNewUserProfile(User user){
//		ClientResponse client = new MailApi()
//		.to(user.getEmail(), user.getName())
//		.subject("Bem vindo ao Almoço Já")
//		.html(EmailTemplate.welcomeProfile(user))
//		.send();
//		return client.getStatus() == 200;
//	}
//	
//	public static boolean sendEmailNewAdmin(User user){
//		ClientResponse client = new MailApi()
//		.to(user.getEmail(), user.getName())
//		.subject("Bem vindo ao Almoço Já")
//		.html("New Admin")
//		.send();
//		return client.getStatus() == 200;
//	}

}