/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.entity.constraint;

import com.github.rjeschke.txtmark.Processor;
import com.j7ss.entity.DocumentoVagaEstagio;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
public class DocumentoParse {

	private DocumentoVagaEstagio documentoVagaEstagio;

	private String docPage;
	
	public DocumentoParse(DocumentoVagaEstagio documentoVagaEstagio) {
		this.documentoVagaEstagio = documentoVagaEstagio;
		this.docPage = documentoVagaEstagio.getDocumento().getHtmlPage();
		if(documentoVagaEstagio.getDocumento().getKeys() != null){
			
			for (String key : documentoVagaEstagio.getDocumento().getKeys().split(",")) {
				if(key.contains("||[")){
					String value = key.substring(key.indexOf("[")+1, key.indexOf("]"));
					int lenght = Integer.parseInt(value);	
					replace(lenght, DocumentoKey.valueOf(key.substring(0, key.indexOf("|")).replaceAll("&", "").replaceAll("`", "")));
				}else{
					replace(0, DocumentoKey.valueOf(key.replaceAll("&", "").replaceAll("`", "")));
				}
				
			}
		}
	}
	
	public String toPage(){
		return Processor.process(docPage, true);
	}
	
	private void replace(int lenght, DocumentoKey key){
		switch (key) {
		// Usuario
		case USUARIO_NOME:
			replace(lenght, DocumentoKey.USUARIO_NOME, documentoVagaEstagio.getVagaEstagio().getAluno().getUsuario().getNome());
			break;
		case USUARIO_EMAIL:
			replace(lenght, DocumentoKey.USUARIO_EMAIL, documentoVagaEstagio.getVagaEstagio().getAluno().getUsuario().getEmail());
			break;
			
		// Aluno
		case ALUNO_MATRICULA:	
			replace(lenght, DocumentoKey.ALUNO_MATRICULA, documentoVagaEstagio.getVagaEstagio().getAluno().getMatricula());
			break;
		case ALUNO_TELEFONE:
			replace(lenght, DocumentoKey.ALUNO_TELEFONE, documentoVagaEstagio.getVagaEstagio().getAluno().getTelefone());
			break;
		case ALUNO_CELULAR:	
			replace(lenght, DocumentoKey.ALUNO_CELULAR, documentoVagaEstagio.getVagaEstagio().getAluno().getCelular());
			break;
		case ALUNO_CV_LATTES:
			replace(lenght, DocumentoKey.ALUNO_CV_LATTES, documentoVagaEstagio.getVagaEstagio().getAluno().getCvLattes());
			break;
		case ALUNO_CPF:
			replace(lenght, DocumentoKey.ALUNO_CPF, documentoVagaEstagio.getVagaEstagio().getAluno().getCpf());
			break;
		case ALUNO_RG:
			replace(lenght, DocumentoKey.ALUNO_RG, documentoVagaEstagio.getVagaEstagio().getAluno().getRg());
			break;
		case ALUNO_DATA_NASCIMENTO:
			replace(lenght, DocumentoKey.ALUNO_DATA_NASCIMENTO, documentoVagaEstagio.getVagaEstagio().getAluno().getDataNascimentoFormat());
			break;
		case ALUNO_ENDERECO_ENDERECO:
			replace(lenght, DocumentoKey.ALUNO_ENDERECO_ENDERECO, documentoVagaEstagio.getVagaEstagio().getAluno().getEndereco());
			break;
		case ALUNO_ENDERECO_NUMERO:
			replace(lenght, DocumentoKey.ALUNO_ENDERECO_NUMERO, documentoVagaEstagio.getVagaEstagio().getAluno().getNumero());
			break;
		case ALUNO_ENDERECO_COMPLEMENTO:
			replace(lenght, DocumentoKey.ALUNO_ENDERECO_COMPLEMENTO, documentoVagaEstagio.getVagaEstagio().getAluno().getComplemento());
			break;
		case ALUNO_ENDERECO_BAIRRO:
			replace(lenght, DocumentoKey.ALUNO_ENDERECO_BAIRRO, documentoVagaEstagio.getVagaEstagio().getAluno().getBairro());
			break;
		case ALUNO_ENDERECO_CEP:
			replace(lenght, DocumentoKey.ALUNO_ENDERECO_CEP, documentoVagaEstagio.getVagaEstagio().getAluno().getCep());
			break;
		case ALUNO_ENDERECO_CIDADE:
			replace(lenght, DocumentoKey.ALUNO_ENDERECO_CIDADE, documentoVagaEstagio.getVagaEstagio().getAluno().getCidade());
			break;
		case ALUNO_ENDERECO_UF:
			replace(lenght, DocumentoKey.ALUNO_ENDERECO_UF, documentoVagaEstagio.getVagaEstagio().getAluno().getUf());
			break;
			
			// Vaga Estagio
		case ESTAGIO_CARGA_HORARIA:
			replace(lenght, DocumentoKey.ESTAGIO_CARGA_HORARIA, documentoVagaEstagio.getVagaEstagio().getCargaHoraria());
			break;
		case ESTAGIO_REMUNERACAO:
			replace(lenght, DocumentoKey.ESTAGIO_REMUNERACAO, documentoVagaEstagio.getVagaEstagio().getRemuneracao());
			break;
		case ESTAGIO_TURNO:
			replace(lenght, DocumentoKey.ESTAGIO_TURNO, documentoVagaEstagio.getVagaEstagio().getTurno());
			break;
		case ESTAGIO_HORA_INICIO_ESTAGIO:
			replace(lenght, DocumentoKey.ESTAGIO_HORA_INICIO_ESTAGIO, documentoVagaEstagio.getVagaEstagio().getHoraInicioEstagioFormat());
			break;
		case ESTAGIO_HORA_FIM_INTERVALO:
			replace(lenght, DocumentoKey.ESTAGIO_HORA_FIM_INTERVALO, documentoVagaEstagio.getVagaEstagio().getHoraFimOuIntervaloFormat());
			break;
		case ESTAGIO_HORA_RETORNO:
			replace(lenght, DocumentoKey.ESTAGIO_HORA_RETORNO, documentoVagaEstagio.getVagaEstagio().getHoraRetornoFormat());
			break;
		case ESTAGIO_HORA_FIM_ESTAGIO:
			replace(lenght, DocumentoKey.ESTAGIO_HORA_FIM_ESTAGIO, documentoVagaEstagio.getVagaEstagio().getHoraFimEstagioFormat());
			break;
		case ESTAGIO_VIGENCIA_INICIO:
			replace(lenght, DocumentoKey.ESTAGIO_VIGENCIA_INICIO, documentoVagaEstagio.getVagaEstagio().getVigenciaInicioFormat());
			break;
		case ESTAGIO_VIGENCIA_FIM:
			replace(lenght, DocumentoKey.ESTAGIO_VIGENCIA_FIM, documentoVagaEstagio.getVagaEstagio().getVigenciaFimFormat());
			break;
		case ESTAGIO_VALOR_TRANSPORTE:
			replace(lenght, DocumentoKey.ESTAGIO_VALOR_TRANSPORTE, documentoVagaEstagio.getVagaEstagio().getValorTransporte());
			break;
		case ESTAGIO_APOLICE_NUMERO:
			replace(lenght, DocumentoKey.ESTAGIO_APOLICE_NUMERO, documentoVagaEstagio.getVagaEstagio().getApoliceNumero());
			break;
		case ESTAGIO_APOLICE_EMPRESA:
			replace(lenght, DocumentoKey.ESTAGIO_APOLICE_EMPRESA, documentoVagaEstagio.getVagaEstagio().getApoliceEmpresa());
			break;
		case ESTAGIO_ATIVIDADES:
			replace(lenght, DocumentoKey.ESTAGIO_ATIVIDADES, documentoVagaEstagio.getVagaEstagio().getAtividades());
			break;
		case ESTAGIO_RESULTADOS:
			replace(lenght, DocumentoKey.ESTAGIO_RESULTADOS, documentoVagaEstagio.getVagaEstagio().getResultados());
			break;
		case ESTAGIO_SETOR:
			replace(lenght, DocumentoKey.ESTAGIO_SETOR, documentoVagaEstagio.getVagaEstagio().getSetor());
			break;
		
		// Empresa
		case EMPRESA_NOME:
			replace(lenght, DocumentoKey.EMPRESA_NOME, documentoVagaEstagio.getVagaEstagio().getEmpresa().getNome());
			break;
		case EMPRESA_EMAIL:
			replace(lenght, DocumentoKey.EMPRESA_EMAIL, documentoVagaEstagio.getVagaEstagio().getEmpresa().getEmail());
			break;
		case EMPRESA_TELEFONE:
			replace(lenght, DocumentoKey.EMPRESA_TELEFONE, documentoVagaEstagio.getVagaEstagio().getEmpresa().getTelefone());
			break;
		case EMPRESA_FAX:
			replace(lenght, DocumentoKey.EMPRESA_FAX, documentoVagaEstagio.getVagaEstagio().getEmpresa().getFax());
			break;
		case EMPRESA_SUPERVISOR:
			replace(lenght, DocumentoKey.EMPRESA_SUPERVISOR, documentoVagaEstagio.getVagaEstagio().getEmpresaSupervisor().getSupervisor());
			break;
		case EMPRESA_CARGO_SUPERVISOR:
			replace(lenght, DocumentoKey.EMPRESA_CARGO_SUPERVISOR, documentoVagaEstagio.getVagaEstagio().getEmpresaSupervisor().getCargoSupervisor());
			break;
		case EMPRESA_TELEFONE_SUPERVISOR:
			replace(lenght, DocumentoKey.EMPRESA_TELEFONE_SUPERVISOR, documentoVagaEstagio.getVagaEstagio().getEmpresaSupervisor().getTelefoneSupervisor());
			break;
		case EMPRESA_CNPJ:
			replace(lenght, DocumentoKey.EMPRESA_CNPJ, documentoVagaEstagio.getVagaEstagio().getEmpresa().getCnpj());
			break;
		case EMPRESA_SITE:
			replace(lenght, DocumentoKey.EMPRESA_SITE, documentoVagaEstagio.getVagaEstagio().getEmpresa().getSite());
			break;
		case EMPRESA_RAMO_ATIVIDADE:
			replace(lenght, DocumentoKey.EMPRESA_RAMO_ATIVIDADE, documentoVagaEstagio.getVagaEstagio().getEmpresa().getRamoAtividade());
			break;
		case EMPRESA_ENDERECO_ENDERECO:
			replace(lenght, DocumentoKey.EMPRESA_ENDERECO_ENDERECO, documentoVagaEstagio.getVagaEstagio().getEmpresa().getEndereco());
			break;
		case EMPRESA_ENDERECO_NUMERO:
			replace(lenght, DocumentoKey.EMPRESA_ENDERECO_NUMERO, documentoVagaEstagio.getVagaEstagio().getEmpresa().getNumero());
			break;
		case EMPRESA_ENDERECO_BAIRRO:
			replace(lenght, DocumentoKey.EMPRESA_ENDERECO_BAIRRO, documentoVagaEstagio.getVagaEstagio().getEmpresa().getBairro());
			break;
		case EMPRESA_ENDERECO_CEP:
			replace(lenght, DocumentoKey.EMPRESA_ENDERECO_CEP, documentoVagaEstagio.getVagaEstagio().getEmpresa().getCep());
			break;
		case EMPRESA_ENDERECO_UF:
			replace(lenght, DocumentoKey.EMPRESA_ENDERECO_UF, documentoVagaEstagio.getVagaEstagio().getEmpresa().getUf());
			break;
		case EMPRESA_ENDERECO_CIDADE:
			replace(lenght, DocumentoKey.EMPRESA_ENDERECO_CIDADE, documentoVagaEstagio.getVagaEstagio().getEmpresa().getCidade());
			break;
			
		// Instituicao
		case INSTITUICAO_NOME:
			replace(lenght, DocumentoKey.INSTITUICAO_NOME, documentoVagaEstagio.getVagaEstagio().getAluno().getCurso().getDepartamento().getCampus().getInstituicao().getNome());
			break;
			
		// Campus
		case CAMPUS_NOME:
			replace(lenght, DocumentoKey.CAMPUS_NOME, documentoVagaEstagio.getVagaEstagio().getAluno().getCurso().getDepartamento().getCampus().getNome());
			break;
		case CAMPUS_EMAIL:
			replace(lenght, DocumentoKey.CAMPUS_EMAIL, documentoVagaEstagio.getVagaEstagio().getAluno().getCurso().getDepartamento().getCampus().getEmail());
			break;
		case CAMPUS_TELEFONE:
			replace(lenght, DocumentoKey.CAMPUS_TELEFONE, documentoVagaEstagio.getVagaEstagio().getAluno().getCurso().getDepartamento().getCampus().getTelefone());
			break;
		case CAMPUS_ENDERECO_ENDERECO:
			replace(lenght, DocumentoKey.CAMPUS_ENDERECO_ENDERECO, documentoVagaEstagio.getVagaEstagio().getAluno().getCurso().getDepartamento().getCampus().getEndereco());
			break;
		case CAMPUS_ENDERECO_NUMERO:
			replace(lenght, DocumentoKey.CAMPUS_ENDERECO_NUMERO, documentoVagaEstagio.getVagaEstagio().getAluno().getCurso().getDepartamento().getCampus().getNumero());
			break;
		case CAMPUS_ENDERECO_BAIRRO:
			replace(lenght, DocumentoKey.CAMPUS_ENDERECO_BAIRRO, documentoVagaEstagio.getVagaEstagio().getAluno().getCurso().getDepartamento().getCampus().getBairro());
			break;
		case CAMPUS_ENDERECO_CEP:
			replace(lenght, DocumentoKey.CAMPUS_ENDERECO_CEP, documentoVagaEstagio.getVagaEstagio().getAluno().getCurso().getDepartamento().getCampus().getCep());
			break;
		case CAMPUS_ENDERECO_UF:
			replace(lenght, DocumentoKey.CAMPUS_ENDERECO_UF, documentoVagaEstagio.getVagaEstagio().getAluno().getCurso().getDepartamento().getCampus().getUf());
			break;
		case CAMPUS_ENDERECO_CIDADE:
			replace(lenght, DocumentoKey.CAMPUS_ENDERECO_CIDADE, documentoVagaEstagio.getVagaEstagio().getAluno().getCurso().getDepartamento().getCampus().getCidade());
			break;
	
		// Departamento
		case DEPARTAMENTO_NOME:
			replace(lenght, DocumentoKey.DEPARTAMENTO_NOME, documentoVagaEstagio.getVagaEstagio().getAluno().getCurso().getDepartamento().getNome());
			break;
			
		// Curso
		case ALUNO_CURSO_SEMESTRE_ATUAL:
			replace(lenght, DocumentoKey.ALUNO_CURSO_SEMESTRE_ATUAL, documentoVagaEstagio.getVagaEstagio().getAluno().getSemestreAtual().toString());
			break;
		case ALUNO_CURSO_NOME:
			replace(lenght, DocumentoKey.ALUNO_CURSO_NOME, documentoVagaEstagio.getVagaEstagio().getAluno().getCurso().getNome());
			break;
		case ALUNO_CURSO_ORIENTADOR:
			replace(lenght, DocumentoKey.ALUNO_CURSO_ORIENTADOR, documentoVagaEstagio.getVagaEstagio().getAluno().getCurso().getProfessorOrientador());
			break;
		case ALUNO_CURSO_ORIENTADOR_TELEFONE:
			replace(lenght, DocumentoKey.ALUNO_CURSO_ORIENTADOR_TELEFONE, documentoVagaEstagio.getVagaEstagio().getAluno().getCurso().getProfessorOrientadorTelefone());
			break;
		case ALUNO_CURSO_ORIENTADOR_EMAIL:
			replace(lenght, DocumentoKey.ALUNO_CURSO_ORIENTADOR_EMAIL, documentoVagaEstagio.getVagaEstagio().getAluno().getCurso().getProfessorOrientadorEmail());
			break;
		}
	}
	
	
	private void replace(int length, DocumentoKey key, Object value){
		if(value != null){
			StringBuilder build = new StringBuilder(value.toString());
			if(value.toString().length() < length){
				for(int i = 0; i < length - value.toString().length(); i++){
					build.append("&nbsp;");
				}
				docPage = docPage.replace(key.getKey()+"||["+length+"]", build.toString());
			}else{
				docPage = docPage.replace(key.getKey(), build.toString());
			}
		}
	}
	
}
