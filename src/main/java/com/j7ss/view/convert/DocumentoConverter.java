/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view.convert;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import lombok.Setter;

import com.j7ss.entity.Documento;
import com.j7ss.view.admin.AdminInstituicaoBean;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class DocumentoConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Setter
	@ManagedProperty(value="#{adminInstituicaoBean}")
	private AdminInstituicaoBean adminInstituicaoBean;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return adminInstituicaoBean.getDocumentoByNome(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return (value != null) ? ((Documento) value).getNome() : null;
	}

}
