/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Setter;

import com.j7ss.core.DAOException;
import com.j7ss.core.Messages;
import com.j7ss.entity.Usuario;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class PerfilBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Setter
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
	
	public void update(){
		try {
			if(getUsuario().isTypeAluno()){
				getUsuario().getAluno().save();
			}
			loginBean.getUsuario().save();
			Messages.showGrowlInfo("Meu Perfil", "Meu Perfil salvo com sucesso!");
		} catch (DAOException e) {
			Messages.showGrowlInfo("Meu Perfil", e.getMessage());
		}
	}
	
	
//******************************************************************************************************************************
//## Getters Setters
	public Usuario getUsuario(){
		return loginBean.getUsuario();
	}
	
}
