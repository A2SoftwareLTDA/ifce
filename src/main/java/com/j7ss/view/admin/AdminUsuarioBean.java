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
import com.j7ss.core.MD5;
import com.j7ss.core.Messages;
import com.j7ss.entity.Instituicao;
import com.j7ss.entity.Usuario;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class AdminUsuarioBean extends BasicView<Usuario>{
	private static final long serialVersionUID = 1L;
	
	private List<Instituicao> instituicaos;

	
	@Override
	public void save() {
		if(entity.isTypeAdmin()){
			entity.setInstituicao(null);
		}
		entity.senhaMD5(entity.getSenha());
		super.save();
	}
	
	public void save(Usuario usuario) {
		if(usuario.isTypeAdmin()){
			usuario.setInstituicao(null);
		}else if(usuario.isTypeInstituicao() && usuario.getInstituicao().isNew() && getInstituicaos().size() > 0){
			usuario.setInstituicao(getInstituicaos().get(0));
		}
		entity = usuario;
		super.save();
	}

	
//******************************************************************************************************************************
//## Growl Messages
	@Override
	public void onSave() {
		Messages.showGrowlInfo("Usuario", "Usuario <strong>{0}</strong> salvo com sucesso!", entity.getNome());
	}
	
	@Override
	public void onRemove(Usuario usuario) {
		Messages.showGrowlInfo("Usuario", "Usuario <strong>{0}</strong> removido com sucesso!", usuario.getNome());
	}
	
	
//******************************************************************************************************************************
//## Getters Setters
	@Override
	public Usuario getEntity() {
		return entity == null ? entity = new Usuario() : entity;
	}
	
	@Override
	public List<Usuario> getEntitys() {
		return entitys == null ? entitys = Usuario.findAllMinusAluno() : entitys;
	}
	
	public List<Instituicao> getInstituicaos() {
		return instituicaos == null ? Instituicao.findAll() : instituicaos;
	}
	
	public Instituicao getInstituicaoByNome(String nome){
		for (Instituicao instituicao : getInstituicaos()) {
			if(instituicao.getNome().equals(nome)) return instituicao;
		}
		return null;
	}
}
