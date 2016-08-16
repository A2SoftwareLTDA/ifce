/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@Table(name = "documento_curso")
@ToString @EqualsAndHashCode(of={"id"})
public class DocumentoCurso implements IGenericEntity<DocumentoCurso> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	private Integer ordem;

	@ManyToOne
	@Getter @Setter
	private Curso curso = new Curso();
	
	@ManyToOne
	@Getter @Setter
	private Documento documento = new Documento();
	
	public DocumentoCurso() { }
	
	public DocumentoCurso(Curso curso) {
		this.curso = curso;
	}

	
//******************************************************************************************************************************
//## Builder
	public DocumentoCurso id(Integer id){
		this.id = id;
		return this;
	}
	
	public DocumentoCurso ordem(Integer ordem){
		this.ordem = ordem;
		return this;
	}
	
	public DocumentoCurso curso(Curso curso){
		this.curso = curso;
		return this;
	}
	
	public DocumentoCurso documento(Documento documento){
		this.documento = documento;
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
	private static DAO<DocumentoCurso> dao = new DAO<DocumentoCurso>(DocumentoCurso.class);
	
	@Override
	public DocumentoCurso save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
}