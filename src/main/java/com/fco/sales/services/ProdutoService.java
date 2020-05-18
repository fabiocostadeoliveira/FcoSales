package com.fco.sales.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fco.sales.domain.Produto;
import com.fco.sales.dtos.ProdutoDTO;
import com.fco.sales.repositories.ProdutoRepository;
import com.fco.sales.services.exceptions.ObjectNotFoundException;


@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public Produto findWithoutValidation(Integer id) {

		Optional<Produto> produto = repository.findById(id);
		
		return produto.orElse(null);
	}

	
	public Produto find(Integer id) {

		Optional<Produto> produto = repository.findById(id);
		
		return produto.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrada - id: " + id));
	}
	

	public Produto insert(Produto obj) {
		
		obj.setId(null);
		
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		
		find(id);
		
		repository.deleteById(id);
	}
	
	
	public List<Produto> findAll() {
		
		return repository.findAll();
	}
	
	
	public Produto fromDTO(ProdutoDTO produtoDTO) {
		
		return new Produto(produtoDTO);
	}

}
