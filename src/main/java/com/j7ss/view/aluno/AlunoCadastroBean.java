/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view.aluno;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Getter;
import lombok.Setter;

import com.j7ss.core.MD5;
import com.j7ss.core.Messages;
import com.j7ss.core.WebContext;
import com.j7ss.core.email.EmailTemplate;
import com.j7ss.core.email.MailApi;
import com.j7ss.entity.Aluno;
import com.j7ss.entity.Campus;
import com.j7ss.entity.Curso;
import com.j7ss.entity.Departamento;
import com.j7ss.entity.Instituicao;
import com.j7ss.entity.constraint.Page;
import com.j7ss.entity.constraint.UsuarioType;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class AlunoCadastroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Setter
	private Aluno aluno;
	
	@Getter @Setter
	private Instituicao instituicao = new Instituicao();
	@Getter @Setter
	private Campus campus = new Campus();
	@Getter @Setter
	private Departamento departamento = new Departamento();
	
	@PostConstruct
	public void init(){
		List<Instituicao> list = Instituicao.findAll();
		if(list != null && list.size() > 0){
			instituicao = list.get(0);
		}
	}
	
	public void save(){
		try {
			String password = aluno.getUsuario().getSenha();
			aluno.getUsuario()
					.senha(MD5.md5(aluno.getUsuario().getSenha()))
					.ativo(true)
					.tipoUsuario(UsuarioType.ALUNO)
					.save();
			aluno.save();
			aluno.getUsuario().aluno(aluno).save();
			
			// Enviar email
			new MailApi()
				.to(aluno.getUsuario().getEmail(), aluno.getUsuario().getNome())
				.subject("Bem vindo ao IFCE Estágios")
				.html(EmailTemplate.confirmEmail(aluno.getUsuario().getNome(), aluno.getUsuario().getEmail(), password, aluno.getId()))
				.send();
			
			
			
//			new MailApi()
//				.to(aluno.getUsuario().getEmail(), aluno.getUsuario().getNome())
//				.message("IFCE Estágio: Confirme seu cadastro!", MailTemplate.confirmEmail(aluno.getUsuario()))
//				.send();
			
			Messages.showGrowlInfo("Cadastro de Alunos", "Cadastrado com sucesso!");
			instituicao = new Instituicao();
			campus = new Campus();
			departamento = new Departamento();
			WebContext.redirect(Page.INDEX);
			
		} catch (Exception e) {
			Messages.showGrowlErro("Cadastro de Alunos", e.getMessage());
		}
	}
	
	public List<Campus> searchCampus(String nome){
		return Campus.findByNomeLike(instituicao, nome);
	}
	
	public List<Departamento> searchDepartamentos(String nome){
		return Departamento.findByNomeLike(campus, nome);
	}
	
	public List<Curso> searchCursos(String nome){
		return Curso.findByNomeLike(departamento, nome);
	}

	
	//########
	// Converts
	public Campus getCampusByNome(String nome){
		if(instituicao != null){
			for (Campus campus : instituicao.getCampus()) {
				if(campus.getNome().equals(nome)) return campus;
			}
		}
		return null;
	}
	
	public Departamento getDepartamentoByNome(String nome){
		if(campus != null){
			for (Departamento departamento : campus.getDepartamentos()) {
				if(departamento.getNome().equals(nome)) return departamento;
			}
		}
		return null;
	}
	
	public Curso getCursoByNome(String nome){
		if(departamento != null){
			for (Curso curso : departamento.getCursos()) {
				if(curso.getNome().equals(nome)) return curso;
			}
		}
		return null;
	}
	
//******************************************************************************************************************************
//## Getters Setters
	public Aluno getAluno() {
		return aluno == null ? aluno = new Aluno() : aluno;
	}
	
}
