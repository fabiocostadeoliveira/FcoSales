package com.fco.sales.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fco.sales.domain.Pedido;
import com.fco.sales.domain.Usuario;
import com.fco.sales.services.validations.PedidoSave;

@PedidoSave
public class PedidoDTO {
	
	private Integer id;
	
	private Date data;
	
	@NotNull( message = "Cliente deve ser informado.")
	private Integer clienteId;
	
	private boolean finalizado;
	
	@NotNull( message = "Usuario deve ser informado.")
	private Usuario usuario;
	
	@Valid
	@NotEmpty(message = "Pedido sem item, favor informar pelo menos um item.")
	private Set<ItemPedidoDTO> itens = new HashSet<>();
	
	public PedidoDTO() {

	}

	public PedidoDTO(Pedido pedido) {
		this.id = pedido.getId();
		this.data = pedido.getData();
		this.clienteId = pedido.getCliente().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setCliente(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public Set<ItemPedidoDTO> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedidoDTO> itens) {
		this.itens = itens;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
