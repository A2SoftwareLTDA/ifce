package com.j7ss.view.admin;

import com.j7ss.core.BasicView;
import com.j7ss.core.Messages;
import com.j7ss.entity.Curso;
import com.j7ss.entity.Professor;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Bruno Barbosa
 */

@ManagedBean
@ViewScoped
public class AdminProfessorBean extends BasicView<Professor>{
    
    private static final long serialVersionUID = 1L;
    
//******************************************************************************************************************************
//## Growl Messages

    @Override
    public void onSave() {
        Messages.showGrowlInfo("Professor", "Professor <strong>{0}</strong> adicionado com sucesso!", entity.getNome());
    }
    
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
    
    //Usado para listar no form os cursos existentes
    public List<Curso> getCursos(){
        return Curso.findAll();
    }
    
    public Curso getCursoNome(String nome){
        for(Curso c : getCursos()){
            if(c.getNome().equals(nome)){
                return c;
            }
        }
        return null;
    }
}
