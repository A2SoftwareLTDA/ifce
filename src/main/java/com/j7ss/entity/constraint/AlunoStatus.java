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
public enum AlunoStatus {

	// Quando o aluno se cadastra, seu status é 'NOVO'
	NOVO("label-primary", "Novo"), 		
	
	// Após concluir todo o wizard de cadastro, seu status passa a ser 'VERIFICAR'
	VERIFICAR("label-warning", "Verificar"),	
	
	// Um funcionário do IFCE, deve verificar se os dados deste aluno está correto, caso ok, seu status será 'VALIDO'
	VALIDO("label-success", "Válido"), 			
	
	// Se durante a validação do cadastro do aluno, o funcionário do IFCE detectar algum erro, seu status será 'INVALIDO'
	INVALIDO("label-danger", "Inválido");		
	
	@Getter
	private String color; 
	@Getter
	private String title;
	
	private AlunoStatus(String color, String title) {
		this.color = color;
		this.title = title;
	}
}
