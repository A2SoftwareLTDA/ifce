/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.core;

import java.io.Serializable;
import java.util.List;

import com.j7ss.core.DAOException;
import com.j7ss.core.IGenericEntity;
import com.j7ss.core.Messages;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
public abstract class BasicView<T extends IGenericEntity<T>> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Getter @Setter 
	protected T entity;
	@Getter
	protected List<T> entitys;

	@Getter
	public boolean form = false;
	
	public void grid(){
		entity = null;
		entitys = null;
		form = false;
	}
	
	public void form(){
		form = true;
	}
	
	public void save(){
		try {
			entity.save();
			onSave();
		} catch (DAOException e) {
			onError(e);
		}
		grid();
	}
	
	public void remove(T entity){
		try {
			if (entity.remove()){
				onRemove(entity);
			}
		} catch (DAOException e) {
			onError(e);
		}
		grid();
	}
	
	
	public void onSave(){
		Messages.showGrowlInfo("Entidade", "Entidade salva com sucesso!");
	}
	
	public void onRemove(T entity){
		Messages.showGrowlInfo("Entidade", "Entidade removido com sucesso!");
	}
	
	public void onError(Exception e){
		Messages.showGrowlErro("Entidade", e.getMessage());
	}
}
