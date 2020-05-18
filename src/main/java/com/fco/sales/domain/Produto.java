package com.fco.sales.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fco.sales.dtos.ProdutoDTO;

@Entity
public class Produto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	private BigDecimal preco;
	
	@JsonIgnore
	@OneToMany(mappedBy="id.produto")
	private Set<ItemPedido> itens = new HashSet<>();

	
	public Produto() {
	}
	
	public Produto(Integer id, String descricao, BigDecimal preco) {
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public Produto(ProdutoDTO produtoDTO) {
		this.id = produtoDTO.getId();
		this.descricao = produtoDTO.getDescricao();
		this.preco = produtoDTO.getPreco();
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
