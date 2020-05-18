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

import com.fco.sales.domain.Pedido;
import com.fco.sales.dtos.PedidoDTO;
import com.fco.sales.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResouce {
	
	@Autowired
	private PedidoService service;
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		
		Pedido pedido = service.findWithoutValidation(id);
		
		if (pedido == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.ok().body(pedido);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PedidoDTO objDto){
		
		Pedido obj = service.fromDTO(objDto);
		
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(objDto.getId())
				.toUri(); 
		
		return ResponseEntity.created(uri).build();
	}
	
	/*
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj){
		
		//Pedido obj = service.fromDTO(objDto);
		
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri(); 
		
		return ResponseEntity.created(uri).build();
	}
	*/
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PedidoDTO>> findAll() {
		
		List<Pedido> list = service.findAll();
		
		List<PedidoDTO> listDTO = list.stream().map(
				obj -> new PedidoDTO(obj)).collect(Collectors.toList()
		);	
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete (@PathVariable Integer id){
		
		Pedido pedido = service.findWithoutValidation(id);
		
		if (pedido == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}


}
