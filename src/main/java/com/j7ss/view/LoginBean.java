package com.j7ss.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.j7ss.entity.Aluno;
import com.j7ss.entity.Empresa;
import com.j7ss.entity.Instituicao;
import com.j7ss.entity.TipoUsuario;
import com.j7ss.entity.Usuario;
import com.j7ss.util.DAOException;
import com.j7ss.util.MD5;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 
 * 
 * @author Edivando Alves
 *
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Setter
	private Usuario usuario;
	@Getter
	private Aluno aluno;
	@Getter
	private Instituicao instituicao;
	@Getter
	private Empresa empresa;
	
	public void login(){	
		try {
			List<Usuario> usuarios = Usuario.findByEmailAndSenha(usuario.getEmail(), MD5.md5(usuario.getSenha()));
			if(usuarios != null && usuarios.size() > 0 ){
				usuario = usuarios.get(0);
				if(usuario.getTipoUsuario().equals(TipoUsuario.INSTITUICAO)){
					instituicao = Instituicao.findByIdUsuario( usuario.getIdUsuario() );
				}else if(usuario.getTipoUsuario().equals(TipoUsuario.ALUNO)){
					aluno = Aluno.findByIdUsuario( usuario.getIdUsuario() );
				}
				
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.html");
			}else{
				usuario = new Usuario();
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage(FacesMessage.SEVERITY_WARN, "Desculpe!", "Mas o email ou a senha informada não confere. Tente novamente!")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void logoff(){
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.html");
		} catch (IOException e) {
			
		}
	}
	
	public String beginRegister(){
		try {
			usuario.setSenha(MD5.md5(usuario.getSenha()));
			usuario.setEmailValido(false);
			usuario.setAtivo(true);
			usuario.save();
			if(usuario.getTipoUsuario().equals(TipoUsuario.ALUNO)){
				aluno = new Aluno(usuario.getIdUsuario(), usuario.getNome());
				aluno.save();
			}
			usuario = null;
			return "login";
		} catch (DAOException e) {
			return "cadastro";
		}
	}
	
	public Usuario getUsuario() {
		return usuario == null ? usuario = new Usuario() : usuario;
	}

	public boolean isPagePermission(String paginaDestino) {
		if(usuario != null && usuario.getTipoUsuario() != null){
			for (String page : usuario.getTipoUsuario().getPages()) {
				if(page.equals(paginaDestino)) return true;
			}
		}
		return false;
	}
	
	public String getNome(){
//		if(usuario.getTipoUsuario().equals(TipoUsuario.ALUNO) && aluno != null){
//			return aluno.getNome();
//		}else{
//			return usuario.getNome();
//		}
		
		return usuario.getNome();
	}
}
