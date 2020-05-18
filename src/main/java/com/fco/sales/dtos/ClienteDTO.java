package com.fco.sales.dtos;

import com.fco.sales.domain.Cliente;

public class ClienteDTO extends AbstractPessoaDTO{
	
	
	private String cnpj;
	
	public ClienteDTO() {
		super();
	}
	
	public ClienteDTO(Integer id, String nome, String telefone, String email, String cnpj) {
		super(id,nome,telefone,email);
		this.cnpj = cnpj;
	}
	
	public ClienteDTO(Cliente cliente) {
		this.setId(cliente.getId());
		this.setNome(cliente.getNome());
		this.setTelefone(cliente.getTelefone());
		this.setEmail(cliente.getEmail());
		this.cnpj = cliente.getCnpj();
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
