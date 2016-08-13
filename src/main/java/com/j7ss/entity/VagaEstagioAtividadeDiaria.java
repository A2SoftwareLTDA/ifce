/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.entity;

import java.util.Date;
import java.util.List;

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
import com.j7ss.entity.constraint.VagaEstagioAtividadeDiariaStatus;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@Entity
@Table(name = "vaga_estagio_atividade_diaria")
@ToString @EqualsAndHashCode(of={"id"})
public class VagaEstagioAtividadeDiaria implements IGenericEntity<VagaEstagioAtividadeDiaria>{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	private Date date;
	
	@Getter @Setter
	private String descricao;
	
	@Getter @Setter
	private Integer quantidadeHoras;
	
	@ManyToOne
	@Getter @Setter
	private VagaEstagio vagaEstagio;
	
	@Getter @Setter
	private VagaEstagioAtividadeDiariaStatus status = VagaEstagioAtividadeDiariaStatus.NOVA;
	
	
//******************************************************************************************************************************
//## Builder
	public VagaEstagioAtividadeDiaria id(Integer id){
		this.id = id;
		return this;
	}
	
	public VagaEstagioAtividadeDiaria date(Date date){
		this.date = date;
		return this;
	}
	
	public VagaEstagioAtividadeDiaria descricao(String descricao){
		this.descricao = descricao;
		return this;
	}
	
	public VagaEstagioAtividadeDiaria quantidadeHoras(Integer quantidadeHoras){
		this.quantidadeHoras = quantidadeHoras;
		return this;
	}
	
	public VagaEstagioAtividadeDiaria vagaEstagio(VagaEstagio vagaEstagio){
		this.vagaEstagio = vagaEstagio;
		return this;
	}
	
	public VagaEstagioAtividadeDiaria status(VagaEstagioAtividadeDiariaStatus status){
		this.status = status;
		return this;
	}
	
//******************************************************************************************************************************
//## Getters Setters
	@Override
	public boolean isNew() {
		return id == null;
	}
	
	public boolean isStatusNova(){
		return VagaEstagioAtividadeDiariaStatus.NOVA.equals(status);
	}
	
	public boolean isStatusOk(){
		return VagaEstagioAtividadeDiariaStatus.OK.equals(status);
	}
	
	
//******************************************************************************************************************************
//## DAO
	private static DAO<VagaEstagioAtividadeDiaria> dao = new DAO<VagaEstagioAtividadeDiaria>(VagaEstagioAtividadeDiaria.class);
	
	@Override
	public VagaEstagioAtividadeDiaria save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
	public static List<VagaEstagioAtividadeDiaria> findByVagaEstagio(VagaEstagio vagaEstagio){
		return dao.findByQuery("Select v From VagaEstagioAtividadeDiaria v Where v.vagaEstagio = ?1", vagaEstagio);
	}
}
