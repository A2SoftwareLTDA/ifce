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
@Table(name = "empresa")
@ToString(of={"nome"}) @EqualsAndHashCode(of={"id"})
public class Empresa implements IGenericEntity<Empresa>{

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
	
	@Column(length=20)
	@Getter @Setter
	private String fax;
	
	@Column(length=20)
	@Getter @Setter
	private String cnpj;
	
	@Getter @Setter
	private String site;
	
	@Getter @Setter
	private String ramoAtividade;
	
	@Getter @Setter
	private String endereco;
	
	@Column(length=10)
	@Getter @Setter
	private String numero;
	
	@Getter @Setter
	private String bairro;
	
	@Column(length=10)
	@Getter @Setter
	private String cep;
	
	@Column(length=2)
	@Getter @Setter
	private String uf;
	
	@Getter @Setter
	private String cidade;
	
	@OneToMany(mappedBy="empresa")
	@Getter @Setter
	private List<VagaEstagio> vagaEstagios;
	
	@OneToMany(mappedBy="empresa")
	@Getter @Setter
	private List<EmpresaSupervisor> empresaSupervisors;

	
//******************************************************************************************************************************
//## Builder
	public Empresa id(Integer id){
		this.id = id;
		return this;
	}
	
	public Empresa nome(String nome){
		this.nome = nome;
		return this;
	}
	
	public Empresa email(String email){
		this.email = email;
		return this;
	}
	
	public Empresa telefone(String telefone){
		this.telefone = telefone;
		return this;
	}
	
	public Empresa cnpj(String cnpj){
		this.cnpj = cnpj;
		return this;
	}
	
	public Empresa site(String site){
		this.site = site;
		return this;
	}
	
	public Empresa ramoAtividade(String ramoAtividade){
		this.ramoAtividade = ramoAtividade;
		return this;
	}

	public Empresa endereco(String endereco){
		this.endereco = endereco;
		return this;
	}
	
	public Empresa bairro(String bairro){
		this.bairro = bairro;
		return this;
	}
	
	public Empresa cep(String cep){
		this.cep = cep;
		return this;
	}
	
	public Empresa cidade(String cidade){
		this.cidade = cidade;
		return this;
	}
	
	public Empresa uf(String uf){
		this.uf = uf;
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
	private static DAO<Empresa> dao = new DAO<Empresa>(Empresa.class);
	
	@Override
	public Empresa save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
	public static List<Empresa> findAll(){
		return dao.findAll();
	}
	
	public static Empresa findByIdUsuario(Integer idUsuario){
		return dao.findOneByQuery("SELECT e FROM Empresa e WHERE e.idUsuario = ?1" , idUsuario);
	}
}
