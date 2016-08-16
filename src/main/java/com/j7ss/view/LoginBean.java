/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lombok.Setter;

import com.j7ss.core.MD5;
import com.j7ss.core.Messages;
import com.j7ss.core.WebContext;
import com.j7ss.entity.Usuario;
import com.j7ss.entity.constraint.Page;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Setter
	private Usuario usuario;

	public void login(){	
		try {
			List<Usuario> usuarios = Usuario.findByEmailAndSenha(usuario.getEmail(), MD5.md5(usuario.getSenha()));
			if(usuarios != null && usuarios.size() > 0 ){
				usuario = usuarios.get(0);
				WebContext.redirect(getHomeLink());
			}else{
				usuario = new Usuario();
				Messages.showGrowlWarn("Desculpe!", "Mas o email ou a senha informada não confere. Tente novamente!");
			}
		} catch (Exception e) {
			Messages.showGrowlErro("Erro ao fazer Login.", e.getMessage());
		}
	}
	
	public void logoff(){
		WebContext.invalidateSession();
		try {
			WebContext.redirect(Page.INDEX);
		} catch (IOException e) {
			Messages.showGrowlErro("Erro ao fazer Logoff.", e.getMessage());
		}
	}

	public boolean isPagePermission(String paginaDestino) {
		if(usuario != null && usuario.getTipoUsuario() != null){
			for (String page : usuario.getTipoUsuario().getPages()) {
				if(page.equals(paginaDestino)) return true;
			}
		}
		return false;
	}
	
	public String getHomeLink(){
		String homePage = "";
		if(usuario.isTypeAluno()){
			homePage = usuario.getAluno().isWizardCompleted() ? Page.ALUNO_HOME : Page.ALUNO_COMPLETE_CADASTRO;
		}else if(usuario.isTypeInstituicao()){
			homePage = Page.INSTITUICAO_HOME;
		}else if(usuario.isTypeAdmin()){
			homePage = Page.ADMIN_HOME;
		}
		return homePage;
	}
	
//******************************************************************************************************************************
//## Getters Setters
	public Usuario getUsuario() {
		return usuario == null ? usuario = new Usuario() : usuario;
	}
}
