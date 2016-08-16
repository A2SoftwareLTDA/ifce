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

import javax.persistence.Column;
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
import com.j7ss.entity.constraint.DocumentoStatus;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@Entity
@Table(name = "documento_vaga_estagio_message")
@ToString @EqualsAndHashCode(of={"id"})
public class DocumentoVagaEstagioMessage implements IGenericEntity<DocumentoVagaEstagioMessage> {

	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer id;
	
	@Column(columnDefinition="text")
	@Getter @Setter
	private String message;
	
	@Getter @Setter
	private Date date;
	
	@Getter @Setter
	private DocumentoStatus status;
	
	@ManyToOne
	@Getter @Setter
	private Usuario usuario;
	
	@ManyToOne
	@Setter
	private DocumentoVagaEstagio documentoVagaEstagio;
		

//******************************************************************************************************************************		
//## Builder
	public DocumentoVagaEstagioMessage id(Integer id){
		this.id = id;
		return this;
	}
	
	public DocumentoVagaEstagioMessage message(String message){
		this.message = message;
		return this;
	}
	
	public DocumentoVagaEstagioMessage date(Date date){
		this.date = date;
		return this;
	}
	
	public DocumentoVagaEstagioMessage documentoVagaEstagio(DocumentoVagaEstagio documentoVagaEstagio){
		this.documentoVagaEstagio = documentoVagaEstagio;
		return this;
	}
	
	public DocumentoVagaEstagioMessage usuario(Usuario usuario){
		this.usuario = usuario;
		return this;
	}
	
	public DocumentoVagaEstagioMessage status(DocumentoStatus status){
		this.status = status;
		return this;
	}
	
	
//******************************************************************************************************************************
//## Getters Setters
	@Override
	public boolean isNew() {
		return id == null;
	}
	
	public DocumentoVagaEstagio getDocumentoVagaEstagio() {
		return documentoVagaEstagio == null ? documentoVagaEstagio = new DocumentoVagaEstagio() : documentoVagaEstagio;
	}
	

//******************************************************************************************************************************		
//## DAO
	private static DAO<DocumentoVagaEstagioMessage> dao = new DAO<DocumentoVagaEstagioMessage>(DocumentoVagaEstagioMessage.class);
	
	@Override
	public DocumentoVagaEstagioMessage save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
}