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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.j7ss.core.DAO;
import com.j7ss.core.DAOException;
import com.j7ss.core.IGenericEntity;
import com.j7ss.core.MD5;
import com.j7ss.entity.constraint.UsuarioType;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@Entity
@Table(name = "usuario")
@ToString(of={"nome", "email"}) @EqualsAndHashCode(of={"id"})
public class Usuario implements IGenericEntity<Usuario>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@Column(unique=true, length=80)
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String senha;
	
	@Getter @Setter
	private UsuarioType tipoUsuario;
	
	@Getter @Setter
	private Boolean emailValido = false;
	
	@Getter @Setter
	private Boolean ativo = true;
	
	@Getter @Setter
	private String instituicaoFilter;
	
	@OneToOne
	@Setter
	private Instituicao instituicao;
	
	@OneToOne
	@Setter
	private Aluno aluno;
	
	
//******************************************************************************************************************************
//## Builder
	public Usuario id(Integer id){
		this.id = id;
		return this;
	}
	
	public Usuario nome(String nome){
		this.nome = nome;
		return this;
	}
	
	public Usuario email(String email){
		this.email = email;
		return this;
	}
	
	public Usuario senha(String senha){
		this.senha = senha;
		return this;
	}
	
	public Usuario senhaMD5(String senha){
		this.senha = MD5.md5(senha);
		return this;
	}
	
	public Usuario tipoUsuario(UsuarioType tipoUsuario){
		this.tipoUsuario = tipoUsuario;
		return this;
	}
	
	public Usuario emailValido(Boolean emailValido){
		this.emailValido = emailValido;
		return this;
	}
	
	public Usuario ativo(Boolean ativo){
		this.ativo = ativo;
		return this;
	}
	
	public Usuario aluno(Aluno aluno){
		this.aluno = aluno;
		return this;
	}
	
	public Usuario instituicao(Instituicao instituicao){
		this.instituicao = instituicao;
		return this;
	}

	
//******************************************************************************************************************************
//## Getters Setters
	@Override
	public boolean isNew() {
		return id == null;
	}
	
	public boolean isTypeAluno() {
		return tipoUsuario.equals(UsuarioType.ALUNO);
	}
	
	public boolean isTypeInstituicao() {
		return tipoUsuario.equals(UsuarioType.INSTITUICAO);
	}
	
	public boolean isTypeAdmin() {
		return tipoUsuario.equals(UsuarioType.ADMINISTRADOR);
	}
	
	public Aluno getAluno() {
		return aluno == null ? aluno = new Aluno() : aluno;
	}
	
	public Instituicao getInstituicao() {
		return instituicao == null ? instituicao = new Instituicao() : instituicao;
	}
	
	
//******************************************************************************************************************************
//## DAO
	private static DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
	
	@Override
	public Usuario save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
	public static List<Usuario> findAllMinusAluno(){
		return dao.findByQuery("SELECT u FROM Usuario u WHERE u.tipoUsuario != ?1", UsuarioType.ALUNO);
	}
	
	public static List<Usuario> findByEmailAndSenha(String email, String senha){
		return dao.findByQuery("SELECT u FROM Usuario u WHERE u.email = ?1 and u.senha = ?2", email, senha);
	}
	
}
