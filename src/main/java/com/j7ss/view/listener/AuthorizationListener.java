/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view.listener;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.j7ss.entity.constraint.Page;
import com.j7ss.view.LoginBean;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
public class AuthorizationListener implements PhaseListener {

	private static final long serialVersionUID = 1L;
	private List<String> publicPages;
	
	public AuthorizationListener() {
		publicPages = Arrays.asList(Page.INDEX, Page.CADASTRO, Page.ERRO_404, Page.ERRO_500, Page.ERRO_ACESSO_NEGADO);
	}
	
	@Override
	public void beforePhase(PhaseEvent event) {	
		FacesContext ctx = event.getFacesContext();	
		if (ctx.getViewRoot() != null) {
			String paginaDestino = ctx.getViewRoot().getViewId();
			
			// imprime a URL destino
			if (PhaseId.RENDER_RESPONSE.equals(event.getPhaseId())) {
				
				System.out.println(event.getPhaseId()+" - "+paginaDestino);
				paginaDestino = getNamePage(paginaDestino);
				HttpSession session = (HttpSession) event.getFacesContext().getExternalContext().getSession(false);

				LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");			
				if( !isPermissionPublicPage( paginaDestino ) ){
					if(loginBean == null || loginBean.getUsuario() == null || loginBean.getUsuario().isNew()){	
						redirect(Page.INDEX);
					}else if( !loginBean.isPagePermission( paginaDestino ) ){
						redirect(Page.ERRO_ACESSO_NEGADO);
					}	
				}		
			}
		}
	}
	
	private void redirect(String page){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(page);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//.getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, page);
	}
	
	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext ctx = event.getFacesContext();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();

		String paginaDestino = getNamePage( ctx.getViewRoot().getViewId() );
	}
	
	private String getNamePage(String page){
		return page.replaceAll("xhtml", "html").replaceAll("/", "");
	}
	
	private boolean isPermissionPublicPage(String page){
		return isPermission(publicPages, page);
	}
	
	private boolean isPermission(List<String> listPage, String page){
		for (String string : listPage) 
			if(string.equals(page)){
				return true;
			}
		return false;
	}
	

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
	
	

}