/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.j7ss.entity.constraint.DocumentoKey;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ApplicationScoped
public class DocumentoKeyBean {

	public String getUsuario(){
		return DocumentoKey.getUsuario();
	}
	
	public String getAluno(){
		return DocumentoKey.getAluno();
	}
	
	public String getEmpresa(){
		return DocumentoKey.getEmpresa();
	}
	
	public String getVagaEstagio(){
		return DocumentoKey.getVagaEstagio();
	}
	
	public String getInstituicao(){
		return DocumentoKey.getInstituicao();
	}
	
	public String getCampus(){
		return DocumentoKey.getCampus();
	}
	
	public String getDepartamento(){
		return DocumentoKey.getDepartamento();
	}
	
	public String getCurso(){
		return DocumentoKey.getCurso();
	}
}
