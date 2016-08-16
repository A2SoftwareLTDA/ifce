/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view.instituicao;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Setter;

import com.j7ss.core.BasicView;
import com.j7ss.core.DAOException;
import com.j7ss.core.Messages;
import com.j7ss.core.email.EmailTemplate;
import com.j7ss.core.email.MailApi;
import com.j7ss.entity.DocumentoVagaEstagio;
import com.j7ss.entity.DocumentoVagaEstagioMessage;
import com.j7ss.entity.constraint.DocumentoParse;
import com.j7ss.entity.constraint.DocumentoStatus;
import com.j7ss.view.LoginBean;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class InstituicaoDocumentoBean extends BasicView<DocumentoVagaEstagio> {
	
	private static final long serialVersionUID = 1L;
	
	@Setter
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
	
	@Setter
	private DocumentoVagaEstagioMessage docMessage;
	
	public void saveErro(){
		try {
			entity.status(DocumentoStatus.VERIFICADO_COM_ERRO).save();
			getDocMessage().date(new Date()).usuario(loginBean.getUsuario()).documentoVagaEstagio(entity).save();
			entity.save();
			// Enviar email para aluno
			new MailApi()
				.to(entity.getVagaEstagio().getAluno().getUsuario().getEmail(), entity.getVagaEstagio().getAluno().getUsuario().getNome())
				.subject("Bem vindo ao IFCE Estágios")
				.html(EmailTemplate.documentoPendenteErro(entity.getVagaEstagio().getAluno().getUsuario().getNome(), 
														  entity.getVagaEstagio().getAluno().getUsuario().getEmail()))
			.send();
			Messages.showGrowlInfo("Documento", "Este documento ficará aguardando a correção para ser liberado para download.");
		} catch (DAOException e) {
			Messages.showGrowlErro("Documento", e.getMessage());
		}
		grid();
	}
	
	public void saveDownload(){
		try {
			entity.status(DocumentoStatus.DISPONIVEL_DOWNLOAD).save();
			getDocMessage().date(new Date()).usuario(loginBean.getUsuario()).documentoVagaEstagio(entity).save();
			entity.save();
			// Enviar email para aluno
			new MailApi()
				.to(entity.getVagaEstagio().getAluno().getUsuario().getEmail(), entity.getVagaEstagio().getAluno().getUsuario().getNome())
				.subject("Bem vindo ao Almoço Já")
				.html(EmailTemplate.documentoLiberadoParaDowwnload(entity.getVagaEstagio().getAluno().getUsuario().getNome(), 
															   	   entity.getVagaEstagio().getAluno().getUsuario().getEmail()))
				.send();
			
			Messages.showGrowlInfo("Documento", "Este documento estará disponível para download.");
		} catch (DAOException e) {
			Messages.showGrowlErro("Documento", e.getMessage());
		}
		grid();
	}
	
	@Override
	public void grid() {
		super.grid();
		this.docMessage = null;
	}
	
//******************************************************************************************************************************
//## Getters Setters
	public String getDocPage(){
		return new DocumentoParse(getEntity()).toPage();
	}
	
	public DocumentoVagaEstagioMessage getDocMessage() {
		return docMessage == null ? docMessage = new DocumentoVagaEstagioMessage() : docMessage;
	}
	
	@Override
	public List<DocumentoVagaEstagio> getEntitys() {
		return entitys == null ? entitys = DocumentoVagaEstagio.findByDocumentoStatus(DocumentoStatus.AGUARDANDO_VERIFICACAO) : entitys;
	}
}
