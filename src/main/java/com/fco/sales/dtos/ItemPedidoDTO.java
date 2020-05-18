package com.fco.sales.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Valid
public class ItemPedidoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer pedidoId;
	
	@NotNull(message = "Id do produto nao informado!")
	private Integer produtoId;
	
	@Valid
	@NotNull(message = "Deve ser informada a quantidade da venda.")
	private BigDecimal quantidade;
	
	public ItemPedidoDTO() {
		
	}
	
	public ItemPedidoDTO(Integer pedidoId, Integer produtoId, BigDecimal quantidade) {
		this.pedidoId = pedidoId;
		this.produtoId = produtoId;
		this.quantidade = quantidade;
	}

	public Integer getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Integer pedidoId) {
		this.pedidoId = pedidoId;
	}

	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	
	
	

}
