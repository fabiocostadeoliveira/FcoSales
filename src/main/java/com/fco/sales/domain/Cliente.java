package com.fco.sales.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import com.fco.sales.dtos.ClienteDTO;

@Entity
public class Cliente extends AbstractPessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String cnpj;
	
	public Cliente() {
	
	}
	
	public Cliente(Integer id, String nome, String telefone, String email, String cnpj) {
		super(id,nome,telefone,email);
		this.cnpj = cnpj;
	}
	
	public Cliente (ClienteDTO obj) {
		this.setId(obj.getId());
		this.setNome(obj.getNome());
		this.setTelefone(obj.getTelefone());
		this.setEmail(obj.getEmail());
		this.cnpj = obj.getCnpj();
	}
	

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
	
}
