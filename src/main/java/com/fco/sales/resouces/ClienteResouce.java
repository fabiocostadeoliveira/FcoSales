package com.fco.sales.resouces;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fco.sales.domain.Cliente;
import com.fco.sales.dtos.ClienteDTO;
import com.fco.sales.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResouce {
	
	@Autowired
	private ClienteService service;
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		
		Cliente produto = service.findWithoutValidation(id);
		
		if (produto == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.ok().body(produto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO objDto){
		
		Cliente obj = service.fromDTO(objDto);
		
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(objDto.getId())
				.toUri(); 
		
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		
		List<Cliente> list = service.findAll();
		
		List<ClienteDTO> listDTO = list.stream().map(
				obj -> new ClienteDTO(obj)).collect(Collectors.toList()
		);	
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete (@PathVariable Integer id){
		
		Cliente produto = service.findWithoutValidation(id);
		
		if (produto == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}


}
