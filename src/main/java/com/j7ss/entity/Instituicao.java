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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "instituicao")
@ToString(of={"nome"}) @EqualsAndHashCode(of={"id"})
public class Instituicao implements IGenericEntity<Instituicao>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@Column(length=80)
	@Getter @Setter
	private String email;
	
	@Column(length=20)
	@Getter @Setter
	private String telefone;
	
	@Getter @Setter
	private String responsavel;
	
	@OneToMany(mappedBy="instituicao", cascade=CascadeType.REMOVE)
	@Fetch(FetchMode.JOIN)
	@Getter @Setter
	private List<Campus> campus;
	
	@OneToMany(mappedBy="instituicao")
	@Getter @Setter
	private List<Usuario> usuarios;
	
	
//******************************************************************************************************************************
//## Builder
	public Instituicao id(Integer id){
		this.id = id;
		return this;
	}
	
	public Instituicao nome(String nome){
		this.nome = nome;
		return this;
	}
	
	public Instituicao email(String email){
		this.email = email;
		return this;
	}
	
	public Instituicao telefone(String telefone){
		this.telefone = telefone;
		return this;
	}
	
	public Instituicao responsavel(String responsavel){
		this.responsavel = responsavel;
		return this;
	}
	
	public Instituicao addCampus(Campus campu){
		if(campus == null){
			campus = new  ArrayList<>();
		}
		campus.add(campu);
		return this;
	}
	
	public Instituicao removeCampus(Campus campu){
		if(campus != null){
			campus.remove(campu);
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
	private static DAO<Instituicao> dao = new DAO<Instituicao>(Instituicao.class);
	
	@Override
	public Instituicao save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
	public static List<Instituicao> findAll(){
		return dao.findByQuery("SELECT i FROM Instituicao i"); //JOIN FETCH i.campus c
	}
	
	public static Instituicao findById(Integer idInstituicao){
		return dao.findOne(idInstituicao);
	}
	
	public static List<Instituicao> findByNomeLike(String nome){
		return dao.findByQuery("SELECT i FROM Instituicao i WHERE lower(i.nome) like ?1" , "%"+nome.toLowerCase()+"%");
	}
	
	public static List<Instituicao> findByNome(String nome){
		return dao.findByQuery("SELECT i FROM Instituicao i WHERE i.nome = ?1" , nome);
	}
	
	public static Instituicao findByIdUsuario(Integer idUsuario){
		return dao.findOneByQuery("SELECT i FROM Instituicao i WHERE i.idUsuario = ?1" , idUsuario);
	}
}
