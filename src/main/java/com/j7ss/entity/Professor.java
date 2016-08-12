package com.j7ss.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.j7ss.core.DAO;
import com.j7ss.core.DAOException;
import com.j7ss.core.IGenericEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "professor")
@ToString @EqualsAndHashCode(of="id")
public class Professor implements IGenericEntity<Professor> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String qualificacao;
	
	@Getter @Setter
	private Integer idade;
	
	/*
	 * Foi necessário o uso do EAGER por conta da não utilização do padrão
	 * OpenEntityManagerInView. A não utilização desse padrão muitas vezes
	 * inviabiliza a utilização do LAZY.
	 * 
	 * O EAGER deve ser usado em casos bem específicos.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "professor_curso", joinColumns = {@JoinColumn(name = "professor_id")}, 
		inverseJoinColumns = {@JoinColumn(name = "curso_id")})
	@Getter @Setter
	private List<Curso> cursosDarAula;

	//******************************************************************************************************************************
	//## Getters Setters
	
	@Override
	public boolean isNew() {
		return id == null;
	}

	//******************************************************************************************************************************
	//## DAO
	private static DAO<Professor> dao = new DAO<Professor>(Professor.class);
	
	@Override
	public Professor save() throws DAOException {
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
	public static List<Professor> findAll(){
		return dao.findByQuery("SELECT p FROM Professor p"); 
	}
	
}
