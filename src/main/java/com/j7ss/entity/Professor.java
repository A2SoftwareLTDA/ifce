package com.j7ss.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.junit.Test;

import com.j7ss.core.DAO;
import com.j7ss.core.DAOException;
import com.j7ss.core.IGenericEntity;
import com.j7ss.entity.constraint.AlunoStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author Jocélio Lima
 * @date  12/08/2016
 * 
 */
@Entity
@Table(name = "professor")
@ToString @EqualsAndHashCode(of="id")
public class Professor implements IGenericEntity<Professor>{
	
	private static final long serialVersionUID = -5768342187540784605L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer id;
	
	@Column(length=60) 
	@Getter @Setter
	private String nome;
	
	@Column(length=60) 
	@Getter @Setter
	private String qualificacao;
	
	@Column(length=2) 
	@Getter @Setter
	private Integer idade;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinTable(name="professores_cursos", joinColumns={@JoinColumn(name="professor_id")}, inverseJoinColumns={@JoinColumn(name="curso_id")})
	@Getter @Setter
	private List<Curso> cursosDarAula = new ArrayList<>();
	
	public String getLabelCursos(){
		String cursos = "";
		for (Curso curso : cursosDarAula) {
			cursos = cursos.concat(curso.getNome());
		}
		return cursos.replaceAll(", $", "");
	}
	
	@Override
	public boolean isNew() {
		return this.id == null;
	}

	private static DAO<Professor> dao = new DAO<Professor>(Professor.class);
	
	private static DAO<Curso> cursoDao = new DAO<Curso>(Curso.class);
	
	@Override
	public Professor save() throws DAOException {
		return isNew() ? dao.add(this) : dao.update(this);
	}
	
	@Test
	public void testeSave(){
		Professor p = new Professor();
		p.setNome("Francisco");
		p.setQualificacao("Graduado");
		p.setIdade(20);
		
		Curso c = new Curso();
		c.setId(1);
				
		p.getCursosDarAula().add(c);
		
		try {
			p.save();
		} catch (DAOException e) {
			e.printStackTrace();
		}
				
	}
	
	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
	public static List<Professor> findAll(){
		return dao.findAll(); 
	}
	
	public static Professor findById(Integer id){
		List<Professor> list = dao.findByQuery("SELECT c FROM Professor c where c.id ="+id); 
		if(list != null && !list.isEmpty())
			return list.get(0);
		return null;
	}
	
	public static List<Curso> findAllCursos(){
		return cursoDao.findByQuery("SELECT c FROM Curso c"); 
	}
	

}
