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
import com.j7ss.entity.Professor;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class ProfessorBean extends BasicView<Professor>{
	
	private static final long serialVersionUID = 1L;
	
	
//******************************************************************************************************************************
//## Growl Messages
	@Override
	public void onRemove(Professor entity) {
		Messages.showGrowlInfo("Professor", "Professor <strong>{0}</strong> removido com sucesso!", entity.getNome());
	}
	
	
//******************************************************************************************************************************
//## Getters Setters
	@Override
	public Professor getEntity() {
		return entity == null ? entity = new Professor() : entity;
	}
	
	@Override
	public List<Professor> getEntitys() {
		return entitys == null ? entitys = Professor.findAll() : entitys;
	}
	
	@Override
	public void setEntity(Professor entity) {
		super.setEntity(Professor.findById(entity.getId()));
	}
}
