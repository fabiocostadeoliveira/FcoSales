package com.fco.sales.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Usuario extends AbstractPessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	private String login;
	
	private String senha;
	
	public Usuario() {
		super();
	}
	
	public Usuario(Integer id, String nome, String telefone, String email, String login, String senha) {
		super(id, nome, telefone, email);
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
