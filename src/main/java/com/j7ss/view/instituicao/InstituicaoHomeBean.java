/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view.instituicao;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.j7ss.core.BasicView;
import com.j7ss.entity.DocumentoVagaEstagio;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class InstituicaoHomeBean extends BasicView<DocumentoVagaEstagio> {
	
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	public void init(){
//		try {
//			new MailApi().to("edivando7@gmail.com", "Edivando Alves").subject("titulo").content("message").send();
//		} catch (MailApiException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	

}
