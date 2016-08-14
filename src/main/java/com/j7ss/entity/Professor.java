package com.j7ss.entity;

import com.j7ss.core.DAO;
import com.j7ss.core.DAOException;
import com.j7ss.core.IGenericEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Bruno Barbosa
 */

@Entity
@Table(name = "professor")
@ToString @EqualsAndHashCode(of="id")
public class Professor implements IGenericEntity<Professor>{
    
    private static final long serialVersionUID = 1L;
    
//******************************************************************************************************************************	
//## Banco de Dados
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter @Setter
    private Integer id;
    
    @Getter @Setter
    private String nome;
    
    @Getter @Setter
    private String qualificacao;
    
    @Getter @Setter
    private Integer idade;
    
    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @Getter @Setter
    private List<Curso> cursosDarAula;

    
//******************************************************************************************************************************	
//## Builder
    
    public Professor id(Integer id){
        this.id = id;
        return this;
    }
    
    public Professor nome(String nome){
        this.nome = nome;
        return this;
    }
    
    public Professor idade(Integer idade){
        this.idade = idade;
        return this;
    }
    
    public Professor qualificacao(String qualificacao){
        this.qualificacao = qualificacao;
        return this;
    }
    
    public Professor cursos(List<Curso> cursos){
        this.cursosDarAula = cursos;
        return this;
    }
    
    public Professor addCurso(Curso curso) throws DAOException{
        getCursosDarAula().add(curso);
        return save();
    }
    
//******************************************************************************************************************************	
//## Getter e Setters

    @Override
    public boolean isNew() {
        return id == null;
    }
    
    /*public List<Curso> getCursosDarAula() {
    return cursosDarAula == null ? cursosDarAula = new ArrayList<>() : cursosDarAula;
    }*/
    
    
//******************************************************************************************************************************	
//## DAO
    
    private static DAO<Professor> dao = new DAO<>(Professor.class);
    
    @Override
    public Professor save() throws DAOException {
        return isNew() ? dao.add(this) : dao.update(this);
    }

    @Override
    public boolean remove() throws DAOException {
        return dao.remove(this);
    }
    
    public static List<Professor> findAll(){
        return dao.findByQuery("SELECT p FROM Professor p");
    }
    
    public static Professor findById(Integer id){
        return dao.findOneByQuery("SELECT p FROM Professor p WHERE p.id = ?1", id);
    }
    
}
