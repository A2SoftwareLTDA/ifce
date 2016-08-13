package com.j7ss.entity;

import java.util.List;

import org.junit.Test;

public class UsuarioTest {
	
	@Test
	public void testEmailPassword(){
		List<Usuario> users = Usuario.findByEmailAndSenha("ed@gmail.com", "d8578edf8458ce06fbc5bb76a58c5ca4");
		
		System.out.println(users);
	}

}
