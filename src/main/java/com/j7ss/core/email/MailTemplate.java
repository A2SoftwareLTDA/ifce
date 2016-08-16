package com.j7ss.core.email;

import com.j7ss.entity.Usuario;

public class MailTemplate {
	
	public static String confirmEmail(Usuario usuario){
		StringBuilder builder = new StringBuilder();
		builder.append("<h3>IFCE Estágios: Confirme seu cadastro</h3>")
			.append("<br/>")
			.append("<a href='www.google.com.br'>Click Aqui</a><br/>")
			.append("<a href='www.google.com.br'>www.google.com.br</a><br/>");
		return builder.toString();
	}
	
	public static String recoverPassword(){
		return "";
	}
	
	

}
