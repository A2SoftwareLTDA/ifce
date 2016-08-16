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

import lombok.Getter;
import lombok.Setter;

import org.primefaces.context.RequestContext;

import com.j7ss.core.DAOException;
import com.j7ss.core.MD5;
import com.j7ss.core.Messages;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class MudarSenhaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private String senhaAtual = "";
	@Getter @Setter
	private String novaSenha = "";
	@Getter @Setter
	private String confirmSenha = "";
	
	@Setter
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
	
	public void mudarSenha(){
		if(!senhaAtual.equals("") && MD5.md5(senhaAtual).equals(loginBean.getUsuario().getSenha())){
			if(!novaSenha.equals("") && !confirmSenha.equals("") && novaSenha.equals(confirmSenha)){
				try {
					loginBean.getUsuario().senha(MD5.md5(novaSenha)).save();
					Messages.showGrowlInfo("Mudar Senha", "Senha alterada com sucesso!");
					RequestContext.getCurrentInstance().execute("PF('modalMudarSenha').hide();");
				} catch (DAOException e) {
					Messages.showGrowlErro("Mudar Senha", e.getMessage());
				}
			}else{
				Messages.showGrowlWarn("Mudar senha", "<strong>Nova Senha</strong> deve ser igual ao <strong>Confirme Senha</strong>");
			}
		}else{
			Messages.showGrowlWarn("Mudar senha", "<strong>Senha Atual</strong> informada esta incorreta!");
		}
	}
}