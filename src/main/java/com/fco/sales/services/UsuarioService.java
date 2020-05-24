package com.fco.sales.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fco.sales.domain.Usuario;
import com.fco.sales.repositories.UsuarioRepository;
import com.fco.sales.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	
	public Usuario find(Integer id) {
		
		Optional<Usuario> usuario = repository.findById(id);
		
		return usuario.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado - id: " + id));
	}
	
	public boolean autenticate(Usuario obj) {
		
		Usuario usuario = find(obj.getId());
		
		if (usuario.getSenha().equals(obj.getSenha()))
			return true;
				
		return false;
	}
	
	
	public Usuario insert(Usuario obj) {
		
		obj.setId(null);
		
		obj = repository.save(obj);
		
		return obj;
	}
	
	
	
}
