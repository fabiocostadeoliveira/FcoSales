package com.fco.sales.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fco.sales.domain.Produto;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String descricao;
	
	private BigDecimal preco;
	
	public ProdutoDTO() {

	}
	
	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
}
