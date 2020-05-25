package com.fco.sales.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fco.sales.domain.Usuario;

public class ResumoPedidoBean implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	
	private boolean situacao;
	
	private Integer qtdProdutos;
	
	private BigDecimal qtdItens;
	
	private BigDecimal totalPedido;
	
	
	public ResumoPedidoBean() {
	}
	
	public ResumoPedidoBean(Usuario usuario, boolean situacao, Integer qtdProdutos, BigDecimal qtdItens, BigDecimal totalPedido) {
		this.usuario = usuario;
		this.situacao= situacao;
		this.qtdItens = qtdItens;
		this.qtdProdutos = qtdProdutos;
		this.totalPedido = totalPedido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public BigDecimal getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(BigDecimal totalPedido) {
		this.totalPedido = totalPedido;
	}

	public Integer getQtdProdutos() {
		return qtdProdutos;
	}

	public void setQtdProdutos(Integer qtdProdutos) {
		this.qtdProdutos = qtdProdutos;
	}

	public BigDecimal getQtdItens() {
		return qtdItens;
	}

	public void setQtdItens(BigDecimal qtdItens) {
		this.qtdItens = qtdItens;
	}
	
	
}
