package com.fco.sales.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fco.sales.domain.Cliente;
import com.fco.sales.dtos.ClienteDTO;
import com.fco.sales.repositories.ClienteRepository;
import com.fco.sales.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente findWithoutValidation(Integer id) {

		Optional<Cliente> cliente = repository.findById(id);
		
		return cliente.orElse(null);
	}

	
	public Cliente find(Integer id) {

		Optional<Cliente> cliente = repository.findById(id);
		
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrada - id: " + id));
	}
	

	public Cliente insert(Cliente obj) {
		
		obj.setId(null);
		
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		
		find(id);
		
		repository.deleteById(id);
	}
	
	
	public List<Cliente> findAll() {
		
		return repository.findAll();
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		
		return new Cliente(clienteDTO.getId(),
							clienteDTO.getNome(),
							clienteDTO.getTelefone(),
							clienteDTO.getEmail(),
							clienteDTO.getCnpj());
	}


}
