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
import javax.persistence.OrderBy;
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
@Table(name = "curso")
@ToString(of={"nome"}) @EqualsAndHashCode(of={"id"})
public class Curso implements IGenericEntity<Curso>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String professorOrientador;
	@Getter @Setter
	private String professorOrientadorTelefone;
	@Getter @Setter
	private String professorOrientadorEmail;
	
	@Getter @Setter
	private Integer duracaoEstagio;
	
	@ManyToOne
	@Getter @Setter
	private Departamento departamento;
	
	@OneToMany(mappedBy="curso", cascade=CascadeType.REMOVE)
	@Fetch(FetchMode.JOIN)
	@OrderBy("ordem")
	@Getter @Setter
	private List<DocumentoCurso> documentoCursos;
	
	@OneToMany(mappedBy="curso")
	@Getter @Setter
	private List<Aluno> alunos;
		
	
//******************************************************************************************************************************
//## Builder
	public Curso id(Integer id){
		this.id = id;
		return this;
	}
	
	public Curso nome(String nome){
		this.nome = nome;
		return this;
	}
	
	public Curso departamento(Departamento departamento){
		this.departamento = departamento;
		return this;
	}
	
	public Curso documentoCursos(List<DocumentoCurso> documentoCursos){
		this.documentoCursos = documentoCursos;
		return this;
	}
	
	
//******************************************************************************************************************************
//## Getters Setters
	@Override
	public boolean isNew() {
		return id == null;
	}
	
	public List<Documento> getDocumentos(){
		List<Documento> documentos = new ArrayList<>();
		for (DocumentoCurso dc : documentoCursos) {
			documentos.add(dc.getDocumento());
		}
		return documentos;
	}
	
	public void setDocumentos(List<Documento> documentos) throws DAOException{
		if(documentoCursos != null){
			for (DocumentoCurso dcs : documentoCursos) {
				dcs.remove();
			}
		}
		documentoCursos = new ArrayList<>();
		for (int i = 0; i < documentos.size(); i++) {
			documentoCursos.add( new DocumentoCurso(this).documento(documentos.get(i)).ordem(i).save() );
		}
	}
	
	
//******************************************************************************************************************************
//## DAO
	private static DAO<Curso> dao = new DAO<Curso>(Curso.class);
	
	@Override
	public Curso save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
	public static Curso findById(Integer idCurso){
		return dao.findOne(idCurso);
	}
	
	public static List<Curso> findByNomeLike(Departamento departamento, String nome){
		return dao.findByQuery("SELECT i FROM Curso i WHERE i.departamento = ?1 AND lower(i.nome) like ?2" ,departamento, "%"+nome.toLowerCase()+"%");
	}
}
