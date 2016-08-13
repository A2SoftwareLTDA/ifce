package com.j7ss.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.j7ss.core.DAO;
import com.j7ss.core.DAOException;
import com.j7ss.core.IGenericEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author markswell
 * 
 */

@Entity
@Table(name="professor")
@ToString @EqualsAndHashCode(of="id")
public class Professor implements IGenericEntity<Professor> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer id;
	
	@Column(length=20)
	@Getter @Setter
	private String nome;

	@Column(length=20)
	@Getter @Setter
	private String qualificacao;
	
	@Column(length=20)
	@Getter @Setter
	private Integer idade;
	
	@ManyToMany
	@Getter @Setter
	private List<Curso> cursoDarAula; 
	
	
	private static DAO<Professor> dao = new DAO<Professor>(Professor.class);

	@Override
	public boolean isNew() {
		return id == null;
	}

	@Override
	public Professor save() throws DAOException {
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}

}
