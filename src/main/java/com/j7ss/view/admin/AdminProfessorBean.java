package com.j7ss.view.admin;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.j7ss.core.BasicView;
import com.j7ss.core.Messages;
import com.j7ss.entity.Curso;
import com.j7ss.entity.Professor;

@ManagedBean
@ViewScoped
public class AdminProfessorBean extends BasicView<Professor>{
	private static final long serialVersionUID = 1L;
	
	private List<Curso> cursos;
	
//******************************************************************************************************************************
//## Growl Messages
	@Override
	public void onSave() {
		Messages.showGrowlInfo("Professor", "Professor <strong>{0}</strong> salvo com sucesso!", entity.getNome());
	}
	
	@Override
	public void onRemove(Professor professor) {
		Messages.showGrowlInfo("Professor", "Professor <strong>{0}</strong> removido com sucesso!", professor.getNome());
	}
	
	/*
	 * Método reponsável por obeter o curso pelo nome
	 * utilizado no CursoProfessorConverter.
	 */
	public Curso getCursoByNome(String nome){
		for (Curso curso : getCursos()) {
			if(curso.getNome().equals(nome)) return curso;
		}
		return null;
	}
	
//******************************************************************************************************************************
//## Getters Setters
	@Override
	public Professor getEntity() {
		return entity == null ? entity = new Professor() : entity;
	}
	
	/*
	 * Se a lista de professores for nula, retorna todos os professores
	 * para serem iterados e listados no dataTable da tela layoutPrivateBasic
	 * que é a base do layout para outras telas.
	 */
	@Override
	public List<Professor> getEntitys() {
		return entitys == null ? entitys = Professor.findAll() : entitys;
	}
	
	/*
	 * Quando o atributo cursos for null, retorna todos os cursos
	 * para serem listados no selectManyMenu da tela adminProfessor.
	 */
	public List<Curso> getCursos() {
		return cursos == null ? Curso.findAll() : cursos;
	}
	
}
