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
import lombok.Setter;

/**
 * 
 * @author Edivando Alves
 * @date  11/02/2016
 * 
 */
public enum DocumentoKey {
	
	// Usuario
	USUARIO_NOME(	"USUARIO", "`&&USUARIO_NOME&&`" ),
	USUARIO_EMAIL(	"USUARIO", "`&&USUARIO_EMAIL&&`" ),
	
	// Aluno
	ALUNO_MATRICULA(			"ALUNO", "`&&ALUNO_MATRICULA&&`" ),
	ALUNO_TELEFONE(				"ALUNO", "`&&ALUNO_TELEFONE&&`" ),
	ALUNO_CELULAR(				"ALUNO", "`&&ALUNO_CELULAR&&`" ),
	ALUNO_CV_LATTES(			"ALUNO", "`&&ALUNO_CV_LATTES&&`" ),
	ALUNO_CPF(					"ALUNO", "`&&ALUNO_CPF&&`" ),
	ALUNO_RG(					"ALUNO", "`&&ALUNO_RG&&`" ),
	ALUNO_DATA_NASCIMENTO(		"ALUNO", "`&&ALUNO_DATA_NASCIMENTO&&`" ),
	ALUNO_ENDERECO_ENDERECO(	"ALUNO", "`&&ALUNO_ENDERECO_ENDERECO&&`" ),
	ALUNO_ENDERECO_NUMERO(		"ALUNO", "`&&ALUNO_ENDERECO_NUMERO&&`" ),
	ALUNO_ENDERECO_COMPLEMENTO(	"ALUNO", "`&&ALUNO_ENDERECO_COMPLEMENTO&&`" ),
	ALUNO_ENDERECO_BAIRRO(		"ALUNO", "`&&ALUNO_ENDERECO_BAIRRO&&`" ),
	ALUNO_ENDERECO_CEP(			"ALUNO", "`&&ALUNO_ENDERECO_CEP&&`" ),
	ALUNO_ENDERECO_CIDADE(		"ALUNO", "`&&ALUNO_ENDERECO_CIDADE&&`" ),
	ALUNO_ENDERECO_UF(			"ALUNO", "`&&ALUNO_ENDERECO_UF&&`" ),
	
	// Empresa
	EMPRESA_NOME(				"EMPRESA", "`&&EMPRESA_NOME&&`" ),
	EMPRESA_EMAIL(				"EMPRESA", "`&&EMPRESA_EMAIL&&`" ),
	EMPRESA_TELEFONE(			"EMPRESA", "`&&EMPRESA_TELEFONE&&`" ),
	EMPRESA_FAX(				"EMPRESA", "`&&EMPRESA_FAX&&`" ),
	EMPRESA_SUPERVISOR(			"EMPRESA", "`&&EMPRESA_SUPERVISOR&&`" ),
	EMPRESA_CARGO_SUPERVISOR(	"EMPRESA", "`&&EMPRESA_CARGO_SUPERVISOR&&`" ),
	EMPRESA_TELEFONE_SUPERVISOR("EMPRESA", "`&&EMPRESA_TELEFONE_SUPERVISOR&&`" ),
	EMPRESA_CNPJ(				"EMPRESA", "`&&EMPRESA_CNPJ&&`" ),
	EMPRESA_SITE(				"EMPRESA", "`&&EMPRESA_SITE&&`" ),
	EMPRESA_RAMO_ATIVIDADE(		"EMPRESA", "`&&EMPRESA_RAMO_ATIVIDADE&&`" ),
	EMPRESA_ENDERECO_ENDERECO(	"EMPRESA", "`&&EMPRESA_ENDERECO_ENDERECO&&`" ),
	EMPRESA_ENDERECO_NUMERO(	"EMPRESA", "`&&EMPRESA_ENDERECO_NUMERO&&`" ),
	EMPRESA_ENDERECO_BAIRRO(	"EMPRESA", "`&&EMPRESA_ENDERECO_BAIRRO&&`" ),
	EMPRESA_ENDERECO_CEP(		"EMPRESA", "`&&EMPRESA_ENDERECO_CEP&&`" ),
	EMPRESA_ENDERECO_UF(		"EMPRESA", "`&&EMPRESA_ENDERECO_UF&&`" ),
	EMPRESA_ENDERECO_CIDADE(	"EMPRESA", "`&&EMPRESA_ENDERECO_CIDADE&&`" ),
	
	// VagaEstagio
	ESTAGIO_CARGA_HORARIA(			"ESTAGIO", "`&&ESTAGIO_CARGA_HORARIA&&`"),
	ESTAGIO_REMUNERACAO(			"ESTAGIO", "`&&ESTAGIO_REMUNERACAO&&`"),
	ESTAGIO_TURNO(					"ESTAGIO", "`&&ESTAGIO_TURNO&&`"),
	ESTAGIO_HORA_INICIO_ESTAGIO(	"ESTAGIO", "`&&ESTAGIO_HORA_INICIO_ESTAGIO&&`"),
	ESTAGIO_HORA_FIM_INTERVALO(		"ESTAGIO", "`&&ESTAGIO_HORA_FIM_INTERVALO&&`"),
	ESTAGIO_HORA_RETORNO(			"ESTAGIO", "`&&ESTAGIO_HORA_RETORNO&&`"),
	ESTAGIO_HORA_FIM_ESTAGIO(		"ESTAGIO", "`&&ESTAGIO_HORA_FIM_ESTAGIO&&`"),
	ESTAGIO_VIGENCIA_INICIO(		"ESTAGIO", "`&&ESTAGIO_VIGENCIA_INICIO&&`"),
	ESTAGIO_VIGENCIA_FIM(			"ESTAGIO", "`&&ESTAGIO_VIGENCIA_FIM&&`"),
	ESTAGIO_VALOR_TRANSPORTE(		"ESTAGIO", "`&&ESTAGIO_VALOR_TRANSPORTE&&`"),
	ESTAGIO_APOLICE_NUMERO(			"ESTAGIO", "`&&ESTAGIO_APOLICE_NUMERO&&`"),
	ESTAGIO_APOLICE_EMPRESA(		"ESTAGIO", "`&&ESTAGIO_APOLICE_EMPRESA&&`"),
	ESTAGIO_ATIVIDADES(				"ESTAGIO", "`&&ESTAGIO_ATIVIDADES&&`"),
	ESTAGIO_RESULTADOS(				"ESTAGIO", "`&&ESTAGIO_RESULTADOS&&`"),
	ESTAGIO_SETOR(					"ESTAGIO", "`&&ESTAGIO_SETOR&&`"),
	
	// Instituicao
	INSTITUICAO_NOME("INSTITUICAO",	"`&&INSTITUICAO_NOME&&`" ),
	
	// Campus
	CAMPUS_NOME(				"CAMPUS", "`&&CAMPUS_NOME&&`" ),
	CAMPUS_EMAIL(				"CAMPUS", "`&&CAMPUS_EMAIL&&`" ),
	CAMPUS_TELEFONE(			"CAMPUS", "`&&CAMPUS_TELEFONE&&`" ),
	CAMPUS_ENDERECO_ENDERECO(	"CAMPUS", "`&&CAMPUS_ENDERECO_ENDERECO&&`" ),
	CAMPUS_ENDERECO_NUMERO(		"CAMPUS", "`&&CAMPUS_ENDERECO_NUMERO&&`" ),
	CAMPUS_ENDERECO_BAIRRO(		"CAMPUS", "`&&CAMPUS_ENDERECO_BAIRRO&&`" ),
	CAMPUS_ENDERECO_CEP(		"CAMPUS", "`&&CAMPUS_ENDERECO_CEP&&`" ),
	CAMPUS_ENDERECO_UF(			"CAMPUS", "`&&CAMPUS_ENDERECO_UF&&`" ),
	CAMPUS_ENDERECO_CIDADE(		"CAMPUS", "`&&CAMPUS_ENDERECO_CIDADE&&`" ),
	
	// Departamento
	DEPARTAMENTO_NOME("DEPARTAMENTO", "`&&DEPARTAMENTO_NOME&&`" ),
	
	// Curso
	ALUNO_CURSO_SEMESTRE_ATUAL(			"CURSO", "`&&ALUNO_CURSO_SEMESTRE_ATUAL&&`" ),
	ALUNO_CURSO_NOME(					"CURSO", "`&&ALUNO_CURSO_NOME&&`" ),
	ALUNO_CURSO_ORIENTADOR(				"CURSO", "`&&ALUNO_CURSO_ORIENTADOR&&`"),
	ALUNO_CURSO_ORIENTADOR_TELEFONE(	"CURSO", "`&&ALUNO_CURSO_ORIENTADOR_TELEFONE&&`"),
	ALUNO_CURSO_ORIENTADOR_EMAIL(		"CURSO", "`&&ALUNO_CURSO_ORIENTADOR_EMAIL&&`");
	
	@Getter @Setter
	private String professorOrientador;
	@Getter @Setter
	private String professorOrientadorTelefone;
	@Getter @Setter
	private String professorOrientadorEmail;
	
	@Getter @Setter
	private Integer duracaoEstagio;
	
	
	@Getter
	private String key;
	private String group;
	
	private DocumentoKey(String group, String key) {
		this.key = key;
		this.group = group;
	}
	
	private static String getByGroup(String group){
		StringBuilder builder = new StringBuilder();
		for(DocumentoKey v : values()){
			if(v.group.equals(group)){
				builder.append(v.key).append("<br/>");
			}
		}
		return builder.toString();
	}
	
	public static String getUsuario(){
		return getByGroup("USUARIO");
	}
	
	public static String getAluno(){
		return getByGroup("ALUNO");	}
	
	public static String getEmpresa(){
		return getByGroup("EMPRESA");
	}
	
	public static String getVagaEstagio(){
		return getByGroup("ESTAGIO");
	}
	
	public static String getInstituicao(){
		return getByGroup("INSTITUICAO");
	} 
	
	public static String getCampus(){
		return getByGroup("CAMPUS");
	}
	
	public static String getDepartamento(){
		return getByGroup("DEPARTAMENTO");
	}
	
	public static String getCurso(){
		return getByGroup("CURSO");
	}
	
}
