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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Setter;

import org.primefaces.event.FlowEvent;

import com.j7ss.core.Messages;
import com.j7ss.core.WebContext;
import com.j7ss.entity.Aluno;
import com.j7ss.entity.VagaEstagio;
import com.j7ss.entity.constraint.AlunoStatus;
import com.j7ss.entity.constraint.Page;
import com.j7ss.view.LoginBean;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class AlunoCompleteCadastroBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Setter
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
	
	@Setter
	private VagaEstagio vagaEstagio;
	

	public void concluir(){
		try {
			if(!loginBean.getUsuario().getAluno().getStatus().equals(AlunoStatus.VALIDO)){
				loginBean.getUsuario().getAluno().status(AlunoStatus.VERIFICAR).save();
			}
			WebContext.redirect(Page.ALUNO_HOME);
		} catch (Exception e) {
			Messages.showGrowlErro("Cadastro de Aluno", e.getMessage());
		}
	}
	
	public String onFlowProcess(FlowEvent event) {
		try {
			// Aluno/Usuario informacoes gerais
			if(event.getOldStep().equals("aluno") || event.getOldStep().equals("endereco")){
				loginBean.getUsuario().getAluno().save();
				
			// Vaga de estagio	
			}else if(event.getOldStep().equals("vagaEstagio") || event.getOldStep().equals("vagaEstagioConcluir")){
				if(loginBean.getUsuario().getAluno().getVagasEstagio().size() == 0){
					loginBean.getUsuario().getAluno().addVagaEstagio(vagaEstagio);
				}else{
					vagaEstagio.save();
				}
			
			// Empresa
			}else if(event.getOldStep().equals("empresa")){
				vagaEstagio.getEmpresa().save();
				vagaEstagio.empresa(vagaEstagio.getEmpresa()).save();
				
			// Empresa Supervisor
			}else if(event.getOldStep().equals("empresaSupervisor")){
				vagaEstagio.getEmpresaSupervisor().empresa(vagaEstagio.getEmpresa()).save();	
				vagaEstagio.empresaSupervisor(vagaEstagio.getEmpresaSupervisor()).save();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return event.getNewStep(); 
    }
	
//******************************************************************************************************************************
//## Getters Setters
	public Aluno getAluno(){
		return loginBean.getUsuario().getAluno();
	}
	
	public VagaEstagio getVagaEstagio() {
		if(vagaEstagio != null){
			return vagaEstagio;
		}else{
			if(loginBean.getUsuario().getAluno().getVagasEstagio().size() == 0){
				vagaEstagio = new VagaEstagio();
			}else{
				vagaEstagio = loginBean.getUsuario().getAluno().getVagasEstagio().get(0);
			}
			return vagaEstagio;
		}
	}

}
