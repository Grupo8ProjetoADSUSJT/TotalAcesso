package br.grupo8.service;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	
	public UsuarioService() {
	}
	
	public boolean isValid(String user, String password){

		if(user.equalsIgnoreCase("vinicius")&& 
				password.equalsIgnoreCase("123456")){
			return true;
		}else{
			return false;
		}
		
	}

}