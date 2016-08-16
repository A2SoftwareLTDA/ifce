/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.j7ss.core.DAO;
import com.j7ss.core.DAOException;
import com.j7ss.core.IGenericEntity;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@Entity
@Table(name = "departamento")
@ToString(of={"nome"}) @EqualsAndHashCode(of={"id"})
public class Departamento implements IGenericEntity<Departamento> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@ManyToOne
	@Getter @Setter
	private Campus campus;
	
	@OneToMany(mappedBy="departamento", cascade=CascadeType.REMOVE)
	@Fetch(FetchMode.JOIN)
	@Getter @Setter
	private List<Curso> cursos;

	
//******************************************************************************************************************************
//## Builder
	public Departamento id(Integer id){
		this.id = id;
		return this;
	}
	
	public Departamento nome(String nome){
		this.nome = nome;
		return this;
	}
	
	public Departamento campus(Campus campus){
		this.campus = campus;
		return this;
	}
	
	public Departamento addCurso(Curso curso){
		if(cursos == null){
			cursos = new ArrayList<>();
		}
		this.cursos.add(curso);
		return this;
	}
	
	public Departamento removeCurso(Curso curso){
		if(cursos != null){
			this.cursos.remove(curso);
		}
		return this;
	}
	
	
//******************************************************************************************************************************
//## Getters Setters
	@Override
	public boolean isNew() {
		return id == null;
	}
	
	
//******************************************************************************************************************************
//## DAO
	private static DAO<Departamento> dao = new DAO<Departamento>(Departamento.class);
	
	@Override
	public Departamento save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
	public static List<Departamento> findByNomeLike(Campus campus, String nome){
		return dao.findByQuery("SELECT i FROM Departamento i WHERE i.campus = ?1 AND lower(i.nome) like ?2" , campus, "%"+nome.toLowerCase()+"%");
	}
}
