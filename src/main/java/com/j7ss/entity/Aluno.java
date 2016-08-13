/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.j7ss.core.DAO;
import com.j7ss.core.DAOException;
import com.j7ss.core.DateUtil;
import com.j7ss.core.IGenericEntity;
import com.j7ss.entity.constraint.AlunoStatus;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@Entity
@Table(name = "aluno")
@ToString @EqualsAndHashCode(of="id")
public class Aluno implements IGenericEntity<Aluno>{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer id;
	
	@Column(length=20) 
	@Getter @Setter
	private String matricula;
	
	@Column(length=20)
	@Getter @Setter
	private String telefone;
	
	@Column(length=20)
	@Getter @Setter
	private String celular;
	
	@Column(length=100)
	@Getter @Setter
	private String cvLattes;
	
	@Getter @Setter
	private Integer semestreAtual;
	
	@Column(length=15)
	@Getter @Setter
	private String cpf;
	
	@Column(length=25)
	@Getter @Setter
	private String rg;
	
	@Getter @Setter
	private Date dataNascimento;
	
	@Getter @Setter
	private String endereco;
	
	@Column(length=10)
	@Getter @Setter
	private String numero;
	
	@Getter @Setter
	private String complemento;
	
	@Getter @Setter
	private String bairro;
	
	@Column(length=10)
	@Getter @Setter
	private String cep;
	
	@Getter @Setter
	private String cidade;
	
	@Column(length=2)
	@Getter @Setter
	private String uf;
	
	@Getter @Setter
	private AlunoStatus status = AlunoStatus.NOVO;
	
	@ManyToOne
	@Setter
	private Curso curso;
	
	@OneToOne(cascade=CascadeType.REMOVE)
	@Setter
	private Usuario usuario;
	
	@OneToMany(mappedBy="aluno", cascade=CascadeType.REMOVE)
	@Fetch(FetchMode.JOIN)
	@Setter
	private List<VagaEstagio> vagasEstagio;

	
//******************************************************************************************************************************	
//## Builder
	public Aluno id(Integer id){
		this.id = id;
		return this;
	}
	
	public Aluno matricula(String matricula){
		this.matricula = matricula;
		return this;
	}
	
	public Aluno telefone(String telefone){
		this.telefone = telefone;
		return this;
	}
	
	public Aluno cvLattes(String cvLattes){
		this.cvLattes = cvLattes;
		return this;
	}
	
	public Aluno semestreAtual(Integer semestreAtual){
		this.semestreAtual = semestreAtual;
		return this;
	}
	
	public Aluno cpf(String cpf){
		this.cpf = cpf;
		return this;
	}
	
	public Aluno rg(String rg){
		this.rg = rg;
		return this;
	}
	
	public Aluno dataNascimento(Date dataNascimento){
		this.dataNascimento = dataNascimento;
		return this;
	}
	
	public Aluno endereco(String endereco){
		this.endereco = endereco;
		return this;
	}
	
	public Aluno bairro(String bairro){
		this.bairro = bairro;
		return this;
	}
	
	public Aluno cep(String cep){
		this.cep = cep;
		return this;
	}
	
	public Aluno cidade(String cidade){
		this.cidade = cidade;
		return this;
	}
	
	public Aluno uf(String uf){
		this.uf = uf;
		return this;
	}
	
	public Aluno vagaEstagio(List<VagaEstagio> vagasEstagio){
		this.vagasEstagio = vagasEstagio;
		return this;
	}
	
	public Aluno curso(Curso curso){
		this.curso = curso;
		return this;
	}
	
	public Aluno usuario(Usuario usuario){
		this.usuario = usuario;
		return this;
	}
	
	public Aluno addVagaEstagio(VagaEstagio vagaEstagio) throws DAOException{
		getVagasEstagio().add(vagaEstagio.aluno(this).save());
		return save();
	}
	
	public Aluno status(AlunoStatus status){
		this.status = status;
		return this;
	}
	
	
//******************************************************************************************************************************
//## Getters Setters
	@Override
	public boolean isNew() {
		return id == null;
	}
	
	public boolean isStatusValido() {
		return status.equals(AlunoStatus.VALIDO);
	}
	
	public boolean isStatusInvalido(){
		return status.equals(AlunoStatus.INVALIDO);
	}
	
	public boolean isWizardAlunoInfo(){
		return (
				semestreAtual != null 	&& !semestreAtual.equals("") &&
				telefone != null 		&& !telefone.equals("") &&
				celular != null 		&& !celular.equals("") &&
				cpf != null				&& !cpf.equals("") &&
				rg != null 				&& !rg.equals("") &&
				dataNascimento != null 	&& !dataNascimento.equals("")
			);
	}
	
	public boolean isWizardAlunoEndereco(){
		return (
				endereco != null 		&& !endereco.equals("") &&
				numero != null 			&& !numero.equals("") &&
				bairro != null 			&& !bairro.equals("") &&
				cep != null 			&& !cep.equals("") &&
				cidade != null 			&& !cidade.equals("") &&
				uf != null 				&& !uf.equals("") 
			);
	}
	
	public boolean isWizardVagaEstagio(){
		if(getVagasEstagio().size() == 0){
			return false;
		}
		VagaEstagio vaga = getVagasEstagio().get(0);
		return (
				vaga.getTurno() != null 			&& !vaga.getTurno().equals("") &&
				vaga.getRemuneracao() != null 		&& !vaga.getRemuneracao().equals("") &&
				vaga.getValorTransporte() != null 	&& !vaga.getValorTransporte().equals("") &&
				vaga.getCargaHoraria() != null 		&& !vaga.getCargaHoraria().equals("") &&
				vaga.getVigenciaInicio() != null 	&& !vaga.getVigenciaInicio().equals("") &&
				vaga.getVigenciaFim() != null 		&& !vaga.getVigenciaFim().equals("") &&
				vaga.getHoraInicioEstagio() != null && !vaga.getHoraInicioEstagio().equals("") &&
				vaga.getHoraFimEstagio() != null 	&& !vaga.getHoraFimEstagio().equals("")
			);
	}
	
	public boolean isWizardVagaEstagioConcluir(){
		if(getVagasEstagio().size() == 0) return false;
		VagaEstagio vaga = getVagasEstagio().get(0);
		return (
				vaga.getAtividades() != null 	    && !vaga.getAtividades().equals("") &&
				vaga.getResultados() != null 	    && !vaga.getResultados().equals("") &&
				vaga.getSetor() != null 	        && !vaga.getSetor().equals("") &&
				vaga.getApoliceNumero() != null 	&& !vaga.getApoliceNumero().equals("") &&
				vaga.getApoliceEmpresa() != null 	&& !vaga.getApoliceEmpresa().equals("") 
			);
	}
	
	public boolean isWizardVagaEstagioEmpresa(){
		if(getVagasEstagio().size() == 0) return false;
		if(getVagasEstagio().get(0).getEmpresa().isNew()) return false;
		Empresa empresa = getVagasEstagio().get(0).getEmpresa();
		return (
			empresa.getNome() != null 			&& !empresa.getNome().equals("") &&
			empresa.getEmail() != null 			&& !empresa.getEmail().equals("") &&
			empresa.getTelefone() != null		&& !empresa.getTelefone().equals("") &&
			empresa.getFax() != null 			&& !empresa.getFax().equals("") &&
			empresa.getCnpj() != null 			&& !empresa.getCnpj().equals("") &&
			empresa.getSite() != null 			&& !empresa.getSite().equals("") &&
			empresa.getRamoAtividade() != null 	&& !empresa.getRamoAtividade().equals("") &&
			empresa.getEndereco() != null 		&& !empresa.getEndereco().equals("") &&
			empresa.getNumero() != null 		&& !empresa.getNumero().equals("") &&
			empresa.getBairro() != null 		&& !empresa.getBairro().equals("") &&
			empresa.getCep() != null 			&& !empresa.getCep().equals("") &&
			empresa.getUf() != null 			&& !empresa.getUf().equals("") &&
			empresa.getCidade() != null 		&& !empresa.getCidade().equals("")
		);
	}
	
	public boolean isWizardVagaEstagioEmpresaSupervisor(){
		if(getVagasEstagio().size() == 0) return false;
		if(getVagasEstagio().get(0).getEmpresaSupervisor().isNew()) return false;
		EmpresaSupervisor empSupervisor = getVagasEstagio().get(0).getEmpresaSupervisor();
		return (
			empSupervisor.getSupervisor() != null 		&& !empSupervisor.getSupervisor().equals("") &&
			empSupervisor.getCargoSupervisor() != null 	&& !empSupervisor.getCargoSupervisor().equals("") &&
			empSupervisor.getTelefoneSupervisor()!=null && !empSupervisor.getTelefoneSupervisor().equals("")
		);
	}
	
	
	public String getWizardStep(){
		if(!isWizardAlunoInfo()){
			return "aluno";
		}else if(!isWizardAlunoEndereco()){
			return "endereco";
		}else if(!isWizardVagaEstagio()){
			return "vagaEstagio";
		}else if(!isWizardVagaEstagioConcluir()){
			return "vagaEstagioConcluir";
		}else if(!isWizardVagaEstagioEmpresa()){
			return "empresa";
		}else if(!isWizardVagaEstagioEmpresaSupervisor()){
			return "empresaSupervisor";
		}
		return null;
	}
	
	public boolean isWizardCompleted(){
		return getWizardStep() == null;
	}
	
	public String getDescricaoCurso(){
		String cursoName = "";
		String departamentoName = "";
		String campusName = "";
		String instituicaoName = "";
		if(curso != null){
			cursoName = curso.getNome();
			if(curso.getDepartamento() != null){
				departamentoName = curso.getDepartamento().getNome();
				if(curso.getDepartamento().getCampus() != null){
					campusName = curso.getDepartamento().getCampus().getNome();
					if(curso.getDepartamento().getCampus().getInstituicao() != null){
						instituicaoName = curso.getDepartamento().getCampus().getInstituicao().getNome();
					}
				}
			}
		}
		return String.format("%s <br/> Departamento de  %s <br/> Campus %s <br/> %s", cursoName, departamentoName, campusName, instituicaoName);
	}
	
	public Curso getCurso() {
		return curso == null ? curso = new Curso() : curso;
	}
	
	public Usuario getUsuario() {
		return usuario == null ? usuario = new Usuario() : usuario;
	}
	
	public List<VagaEstagio> getVagasEstagio() {
		return vagasEstagio == null ? vagasEstagio = new ArrayList<>() : vagasEstagio;
	}
	
	public String getDataNascimentoFormat(){
		return DateUtil.format(dataNascimento);
	}
	
//	public String getComplemento() {
//		return complemento == null ? "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" : complemento;
//	}

	
//******************************************************************************************************************************
//## DAO
	private static DAO<Aluno> dao = new DAO<Aluno>(Aluno.class);
	
	@Override
	public Aluno save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
	public static List<Aluno> findAll(){
		return dao.findByQuery("SELECT a FROM Aluno a LEFT JOIN FETCH a.vagasEstagio ve "); 
	}
	
	public static List<Aluno> findByInstituicaoNotStatus(Instituicao instituicao, AlunoStatus status){
		return dao.findByQuery("SELECT a FROM Aluno a JOIN a.curso c JOIN c.departamento d JOIN d.campus c WHERE c.instituicao = ?1 AND a.status != ?2 ", instituicao, status); 
	}
	
	public static Aluno findByUsuario(Usuario usuario){
		return dao.findOneByQuery("SELECT a FROM Aluno a WHERE a.usuario = ?1" , usuario);
	}
	
	public static List<Aluno> findByNotStatus(AlunoStatus status){
		return dao.findByQuery("Select a From Aluno a WHERE a.status != ?1", status);
	}
}
