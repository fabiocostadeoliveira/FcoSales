package com.fco.sales.resouces;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fco.sales.domain.Usuario;
import com.fco.sales.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> insert(@RequestBody Usuario obj){
		
		Usuario usuario = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(usuario.getId())
				.toUri(); 
		
		usuario.setSenha("");
		
		return ResponseEntity.created(uri).body(obj);
	}
	
	
	@RequestMapping(value = "/autenticate", method = RequestMethod.GET)
	public ResponseEntity<Void> findAllOrderByDataDesc(@RequestBody Usuario obj) {
		
		boolean autorized = service.autenticate(obj);
		
		if(!autorized) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		return ResponseEntity.ok().build();
	}

}
