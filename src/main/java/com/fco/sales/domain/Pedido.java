package com.fco.sales.domain;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	
	@OneToMany(mappedBy = "id.pedido", cascade = CascadeType.ALL)
	@JsonProperty("itens")
	private Set<ItemPedido> itens = new HashSet<>();
	
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
	
	public void setUpItens() {
		try {
			for (ItemPedido ip : getItens()) {
				ip.setPedido(this);
				ip.setTotal( ip.getPrecoVenda().multiply(ip.getQuantidade()) );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
