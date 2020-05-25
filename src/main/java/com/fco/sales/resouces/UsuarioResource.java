package com.fco.sales.resouces;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fco.sales.domain.Usuario;
import com.fco.sales.dtos.UsuarioDTO;
import com.fco.sales.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> insert(@Valid @RequestBody UsuarioDTO objDto){
		
		Usuario obj = service.fromDTO(objDto);
		
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri(); 
		
		obj.setSenha("");
		obj.setTelefone("");
		obj.setEmail("");
		
		return ResponseEntity.created(uri).body(obj);
	}
	
	
	@RequestMapping(value = "/autenticate", method = RequestMethod.GET)
	public ResponseEntity<Usuario> autenticate(@RequestParam(value="login", defaultValue = "") String login,
											@RequestParam(value="senha", defaultValue = "") String senha) {
		
		UsuarioDTO objDTO = new UsuarioDTO(null, null, null, null, login, senha);
		
		Usuario obj = service.fromDTO(objDTO);
		
		obj = service.autenticate(obj);
		
		if(obj == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		return ResponseEntity.ok().body(obj);
	}

}
