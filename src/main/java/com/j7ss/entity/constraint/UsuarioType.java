/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.entity.constraint;

import lombok.Getter;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
public enum UsuarioType {

	ADMINISTRADOR(
			Page.PERFIL,
			Page.ADMIN_HOME,
			Page.ADMIN_ALUNO,
			Page.ADMIN_INSTITUICAO,
			Page.ADMIN_USUARIO,
			Page.ADMIN_DOCUMENTO
			), 	
	INSTITUICAO(
			Page.PERFIL,
			Page.INSTITUICAO_HOME,
			Page.INSTITUICAO_ALUNO,
			Page.INSTITUICAO_DOCUMENTO,
			Page.INSTITUICAO_DOCUMENTO_CONCLUIR
			), 
	ALUNO(	
			Page.PERFIL,
			Page.ALUNO_HOME,
			Page.ALUNO_COMPLETE_CADASTRO
			);
	
	@Getter
	private String[] pages;
	
	private UsuarioType(String...pages) {
		this.pages = pages;
	}
}
