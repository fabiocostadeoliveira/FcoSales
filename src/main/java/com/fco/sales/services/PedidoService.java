package com.fco.sales.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fco.sales.domain.Cliente;
import com.fco.sales.domain.ItemPedido;
import com.fco.sales.domain.Pedido;
import com.fco.sales.domain.Produto;
import com.fco.sales.dtos.ItemPedidoDTO;
import com.fco.sales.dtos.PedidoDTO;
import com.fco.sales.repositories.ItemPedidoRepository;
import com.fco.sales.repositories.PedidoRepository;
import com.fco.sales.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido findWithoutValidation(Integer id) {

		Optional<Pedido> pedido = repository.findById(id);
		
		return pedido.orElse(null);
	}

	
	public Pedido find(Integer id) {

		Optional<Pedido> pedido = repository.findById(id);
		
		return pedido.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado - id: " + id));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		
		obj.setId(null);
		
		obj.setData(new Date());
		
		obj = repository.save(obj);
		
		for (ItemPedido ip : obj.getItens()) {
			ip.setPedido(obj);
			
			ip.setTotal( ip.getPrecoVenda().multiply(ip.getQuantidade()) );
		}

		itemPedidoRepository.saveAll(obj.getItens());
		
		return obj;
	}

	
/*
	@Transactional
	public Pedido insert(Pedido obj) {
		
		obj.setId(null);
		
		obj.setData(new Date());
		
		Cliente cliente = clienteService.find(obj.getCliente().getId());
		
		obj.setCliente(cliente);
		
		obj = repository.save(obj);
		
		for (ItemPedido ip : obj.getItens()) {
			
			Produto produto = produtoService.find(ip.getProduto().getId());
			
			ip.setProduto(produto);
			
			ip.setPrecoVenda(produto.getPreco());
			
			ip.setQuantidade(ip.getQuantidade());
			
			ip.setTotal( ip.getPrecoVenda().multiply(ip.getQuantidade()) );
			
			ip.setPedido(obj);
			
		}

		itemPedidoRepository.saveAll(obj.getItens());
		
		return obj;
	}
*/	
	
	public void delete(Integer id) {
		
		find(id);
		
		repository.deleteById(id);
	}
	
	
	public List<Pedido> findAll() {
		
		return repository.findAll();
	}
	
	public Pedido fromDTO(PedidoDTO pedidoDTO) {
		
		Pedido pedido = new Pedido(pedidoDTO.getId(), pedidoDTO.getData());
		
		Cliente cliente = clienteService.find(pedidoDTO.getClienteId());
		
		pedido.setCliente(cliente);
		
		for (ItemPedidoDTO ip : pedidoDTO.getItens()) {
			
			Produto produto = produtoService.find(ip.getProdutoId());
			
			ItemPedido itemPedido = new ItemPedido();
			
			itemPedido.getId().setPedido(pedido);
			
			itemPedido.setProduto(produto);
			
			itemPedido.setPrecoVenda(produto.getPreco());

			itemPedido.setQuantidade(ip.getQuantidade());
			
			itemPedido.setTotal( itemPedido.getPrecoVenda().multiply(itemPedido.getQuantidade()) );
			
			pedido.getItens().add(itemPedido);
			
		}
		
		return pedido;
	}

}
