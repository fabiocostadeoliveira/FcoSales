package com.fco.sales.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fco.sales.beans.ResumoPedidoBean;

@Entity
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	private boolean finalizado;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "id.pedido", cascade = CascadeType.ALL)
	@JsonProperty("itens")
	private Set<ItemPedido> itens = new HashSet<>();
	
	@Transient
	@JsonProperty
	private ResumoPedidoBean resumo;
	
	public Pedido() {
	
	}
	
	public Pedido(Integer id, Date data) {
		this.data = data;
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

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public ResumoPedidoBean getResumo() {

		Integer qtdProdutos = 0;
		
		BigDecimal qtdItens = new BigDecimal(0);
		
		BigDecimal totalPedido = new BigDecimal(0);
		
		for (ItemPedido itemPedido : this.getItens()) {
			
			qtdProdutos +=1;
			
			totalPedido = totalPedido.add(itemPedido.getTotal());
			
			qtdItens = qtdItens.add(itemPedido.getQuantidade());
		}

		ResumoPedidoBean obj = new ResumoPedidoBean();
		
		obj.setUsuario(this.getUsuario());
		
		obj.setQtdItens(qtdItens);
		
		obj.setQtdProdutos(qtdProdutos);
		
		obj.setTotalPedido(totalPedido);
		
		return obj;
	}

	public void setUpItens() {
		try {
			for (ItemPedido ip : getItens()) {
				ip.setPedido(this);
				ip.setProduto(ip.getProduto());
				ip.setTotal( ip.getPrecoVenda().multiply(ip.getQuantidade()) );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
