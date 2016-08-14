/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j7ss.view.convert;

import com.j7ss.entity.Curso;
import com.j7ss.view.admin.AdminProfessorBean;
import java.io.Serializable;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import lombok.Setter;

/**
 *
 * @author Bruno Barbosa
 */
public class ProfessorConverter implements Converter, Serializable{
    
    private static final long serialVersionUID = 1L;

    @Setter
    @ManagedProperty(value = "#{adminProfessorBean}")
    private AdminProfessorBean bean;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return bean.getCursoNome(string);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return (o != null) ? ((Curso) o).getNome() : null;
    }
    
    
}
