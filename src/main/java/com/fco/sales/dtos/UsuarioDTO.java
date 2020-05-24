package com.fco.sales.dtos;

import javax.validation.constraints.NotEmpty;

import com.fco.sales.services.validations.UsuarioInsert;

@UsuarioInsert
public class UsuarioDTO extends AbstractPessoaDTO{
	
	private String login;
	
	@NotEmpty( message = "A senha deve ser informada")	
	private String senha;
	
	public UsuarioDTO() {
		super();
	}
	
	public UsuarioDTO(Integer id, String nome, String telefone, String email, String login, String senha) {
		super(id,nome,telefone,email);
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
