/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.entity.constraint;

import lombok.Getter;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
public enum DocumentoStatus {

	// Documento não disponível para submeter
	INDISPONIVEL("fa-times","", "Não Disponível"), 
	
	// Disponível para edição e submissão
	DISPONIVEL("fa-check", "blue", "Disponível"), 
	
	// Aguardando verificação/Validação do setor de estágios do IFCE
	AGUARDANDO_VERIFICACAO("fa-clock-o", "yellow", "Aguardando verificação da instituição"), 
	
	// Setor de estágios está verificando este documento
	VERIFICANDO("fa-play-circle-o", "purple", "Verificando"), 
	
	// Após verificação do setor de estágio, foi detectado erros
	VERIFICADO_COM_ERRO("fa-warning", "red", "Verificado com erros"), 
	
	// Disponível para downloads e assinaturas
	DISPONIVEL_DOWNLOAD("fa-download", "green", "Disponível para Download"),
	
	// Documento concluído
	CONCLUIDO("fa-check-circle", "", "Concluído");

	
	@Getter
	private String icon;
	@Getter
	private String titulo;
	@Getter
	private String cor;
	
	private DocumentoStatus(String icon, String cor, String titulo) {
		this.icon = icon;
		this.cor = cor;
		this.titulo = titulo;
	}
}
