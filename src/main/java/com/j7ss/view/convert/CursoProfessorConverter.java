package com.j7ss.view.convert;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.j7ss.entity.Curso;
import com.j7ss.view.admin.AdminProfessorBean;

import lombok.Setter;

@ManagedBean
@ViewScoped
public class CursoProfessorConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Setter
	@ManagedProperty(value="#{adminProfessorBean}")
	private AdminProfessorBean adminProfessorBean;

	/*
	 * Método responsável por receber o valor vindo da tela e
	 * a partir desse valor, procura-se o curso pelo nome e assim
	 * retorna o objeto referente ao nome. Nesse caso o Converter está
	 * específico às telas que usam o AdminProfessorBean. 
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return adminProfessorBean.getCursoByNome(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return (value != null) ? ((Curso) value).getNome() : null;
	}

}
