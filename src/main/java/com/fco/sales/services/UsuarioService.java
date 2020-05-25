package com.fco.sales.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fco.sales.domain.Usuario;
import com.fco.sales.dtos.UsuarioDTO;
import com.fco.sales.repositories.UsuarioRepository;
import com.fco.sales.services.exceptions.IntegrityViolationException;
import com.fco.sales.services.exceptions.ObjectNotFoundException;
import com.fco.sales.services.exceptions.UserNotFoundException;
import com.fco.sales.services.exceptions.UserOrPassworIncorretException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	
	public Usuario find(Integer id) {
		
		Optional<Usuario> usuario = repository.findById(id);
		
		return usuario.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado - id: " + id));
	}
	
	public Usuario findByLogin(String login) {
		
		Usuario usuario = repository.findByLoginIgnoreCase(login);
		
		return usuario;
	}
	
	public Usuario autenticate(Usuario obj) {
		
		Usuario usuario = findByLogin(obj.getLogin());
		
		if (usuario == null)
			throw new UserNotFoundException("Usuario nao encontrado!");
		
		if (!usuario.getSenha().equals(obj.getSenha()))
			throw new UserOrPassworIncorretException("Usuario ou senha incorreto");
				
		return usuario;
	}
	
	
	public Usuario insert(Usuario obj) {
		
		Usuario usuario = findByLogin(obj.getLogin());
		
		if (usuario != null) {
			throw new IntegrityViolationException("Ja existe usuario com esse login"); 
		}
		
		obj.setId(null);
		
		obj = repository.save(obj);
		
		return obj;
	}
	
	public Usuario fromDTO(UsuarioDTO objDto) {
		Usuario usuario = new Usuario(objDto.getId(), objDto.getNome(), objDto.getTelefone(), objDto.getEmail(), objDto.getLogin(), objDto.getSenha());
		return usuario;
	}
	
	
	
}
