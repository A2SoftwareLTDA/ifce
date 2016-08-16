/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.core.email;

import com.github.rjeschke.txtmark.Processor;
import com.j7ss.core.MD5;

/**
 * 
 * @author Edivando Alves
 * @date  06/03/2016
 * 
 */
public class EmailTemplate{
	
	private static final String url = "http://"+System.getenv("OPENSHIFT_APP_DNS"); //+"/confirmEmail";
	
	public static String[] welcome(){
		return new String[]{""};
	}
	
	public static String confirmEmail(String name, String email, String password, Integer serverId){
		StringBuilder urlConfirm = new StringBuilder(url);
		urlConfirm.append("/confirmEmail?i=").append(serverId).append("&u=").append(MD5.md5(email+password+serverId));

		StringBuilder page = new StringBuilder();
		page.append("###<center>Bem vindos ao IFCE Estágio ").append("</center>\n");
		page.append("Você precisa confirmar seu email ").append(email).append(" no IFCE Estágios: ");
		page.append("<a href='").append(urlConfirm).append("'>").append("Click here").append("</a><br/>");
		page.append("<a href='").append(urlConfirm).append("'>").append(urlConfirm).append("</a>");
		return Processor.process(page.toString());
	}
	
	public static String confirmCadastroInstituicao(String name, String email){
		StringBuilder page = new StringBuilder();
		page.append("###<center>Seu cadastro foi confirmado pela Instituição").append("</center>\n\n");
		page.append("Agora você pode concluír e submeter os primeiros documentos");
		return Processor.process(page.toString());
	}
	
	public static String documentoLiberadoParaDowwnload(String name, String email){
		StringBuilder page = new StringBuilder();
		page.append("###<center>Documento esta liberado para download").append("</center>\n\n");
		page.append("Faça o download, imprima, pege as assinaturas e leve ao setor de estágios do IFCE.");
		return Processor.process(page.toString());
	}
	
	public static String documentoPendenteErro(String name, String email){
		StringBuilder page = new StringBuilder();
		page.append("###<center>Existe alguns erros nos seus documentos, favor verificar").append("</center>\n\n");
		page.append("Entre no IFCE Estágios e corriga os documentos.");
		return Processor.process(page.toString());
	}
	
//	public static void main(String[] args) {
//		try {
//			UserInfo user = UserInfo.findByServerId(156);
//			System.out.println(EmailTemplate.confirmEmail(user.getName(), user.getEmail(), user.getPassword(), user.getServerId())[1]);
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
