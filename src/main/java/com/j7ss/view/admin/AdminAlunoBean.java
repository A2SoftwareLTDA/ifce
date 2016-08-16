/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view.admin;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.j7ss.core.BasicView;
import com.j7ss.core.Messages;
import com.j7ss.entity.Aluno;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class AdminAlunoBean extends BasicView<Aluno>{
	private static final long serialVersionUID = 1L;
	
	
	
	
	
//******************************************************************************************************************************
//## Growl Messages
	@Override
	public void onRemove(Aluno entity) {
		Messages.showGrowlInfo("Aluno", "Aluno <strong>{0}</strong> removido com sucesso!", entity.getUsuario().getNome());
	}
	
	
//******************************************************************************************************************************
//## Getters Setters
	@Override
	public Aluno getEntity() {
		return entity == null ? entity = new Aluno() : entity;
	}
	
	@Override
	public List<Aluno> getEntitys() {
		return entitys == null ? entitys = Aluno.findAll() : entitys;
	}
}
