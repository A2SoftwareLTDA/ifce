/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
@Table(name = "documento")
@ToString(of={"nome"}) @EqualsAndHashCode(of={"id"})
public class Documento implements IGenericEntity<Documento> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String titulo;
	
	@Column(columnDefinition="text")
	@Getter @Setter
	private String descricao;
	
	@Column(columnDefinition="text")
	@Getter @Setter
	private String htmlPage;
	
	@Getter @Setter
	private Boolean extra = false;
	
	@Getter @Setter
	private Boolean obrigatorio = true;
	
	@Column(columnDefinition="text")
	@Getter @Setter
	private String keys;
	
	
	@OneToMany(mappedBy="documento")
	@OrderBy("ordem")
	@Getter @Setter
	private List<DocumentoCurso> documentoCursos;
	
	@OneToMany(mappedBy="documento")
	@OrderBy("ordem")
	@Getter @Setter
	private List<DocumentoVagaEstagio> documentoVagasEstagio;
	
	
//******************************************************************************************************************************
//## Builder
	public Documento id(Integer id){
		this.id = id;
		return this;
	}
	
	public Documento nome(String nome){
		this.nome = nome;
		return this;
	}
	
	public Documento titulo(String titulo){
		this.titulo = titulo;
		return this;
	}
	
	public Documento descricao(String descricao){
		this.descricao = descricao;
		return this;
	}
	
	public Documento extra(Boolean extra){
		this.extra = extra;
		return this;
	}
	
	public Documento obrigatorio(Boolean obrigatorio){
		this.obrigatorio = obrigatorio;
		return this;
	}
	
	public Documento htmlPage(String htmlPage){
		this.htmlPage = htmlPage;
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
	private static DAO<Documento> dao = new DAO<Documento>(Documento.class);
	
	@Override
	public Documento save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
	public static List<Documento> findAll(){
		return dao.findAll();
	}
	
}