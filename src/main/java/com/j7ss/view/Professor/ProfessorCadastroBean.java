package com.j7ss.view.Professor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.j7ss.core.DAO;
import com.j7ss.core.DAOException;
import com.j7ss.entity.Curso;
import com.j7ss.entity.Professor;
import lombok.Getter;
import lombok.Setter;

/**
 * Gerenciador da telade cadastro do professor
 * @author markswell
 *
 */
@ManagedBean
@ViewScoped
public class ProfessorCadastroBean implements Serializable{
	
	
	@Setter @Getter
	private Professor professor; 

	private DAO professorDao;

	List<Curso> cursos = new ArrayList<>();
	
	List<Curso> cursosDarAula = new ArrayList<>();

	
	
	
	/**
	 * Método que carrega os dados necessários no momento que o bean é carregado
	 */
	@PostConstruct
	private void init(){
		
		DAO cursos = new DAO<>(Curso.class);
		this.cursos = cursos.findAll();
		this.professorDao = new DAO<>(Professor.class);
		
	}
	/**
	 * método que salva ou atualiza o professor o banco
	 */
	public void save() {
			try {
				professor.save();
			} catch (DAOException e) {
				e.printStackTrace();
			}
	}
	/**
	 * Método que deleta o professor do banco
	 */
	public void remove(){
		try {
			professor.remove();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public Professor buscarPorNome(String nome){
		professor = (Professor) professorDao.findByQuery(nome, nome);
		return professor;
	}
	
}
