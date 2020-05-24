package com.fco.sales;

import java.math.BigDecimal;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fco.sales.domain.Cliente;
import com.fco.sales.domain.Produto;
import com.fco.sales.domain.Usuario;
import com.fco.sales.repositories.UsuarioRepository;
import com.fco.sales.services.ClienteService;
import com.fco.sales.services.ProdutoService;

@SpringBootApplication
public class FcoSalesApplication implements CommandLineRunner{

	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(FcoSalesApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		
		Produto produto1 = new Produto();
		produto1.setDescricao("Televisao 32");
		produto1.setPreco(new BigDecimal(1320.00));
		produtoService.insert(produto1);
		
		Produto produto2 = new Produto();
		produto2.setDescricao("Barbeador");
		produto2.setPreco(new BigDecimal(320.00));
		produtoService.insert(produto2);
		
		Produto produto3 = new Produto();
		produto3.setDescricao("Notebook");
		produto3.setPreco(new BigDecimal(5500.20));
		produtoService.insert(produto3);
		
		
		Cliente cliente1 = new Cliente();
		cliente1.setNome("Joao Alberdo da Silva LTDA");
		cliente1.setCnpj("51538285000150");
		cliente1.setTelefone("45334249136");
		cliente1.setEmail("joaoadalberto@gmail.com");
		clienteService.insert(cliente1);
		
		
		Cliente cliente2 = new Cliente();
		cliente2.setNome("Posto de Combustivel Delta");
		cliente2.setCnpj("97604533000114");
		cliente2.setTelefone("4533425825");
		cliente2.setEmail("postodelta@gmail.com");
		clienteService.insert(cliente2);
		
		
		Usuario usuario1 = new Usuario();
		usuario1.setLogin("admin");
		usuario1.setSenha(Base64.getEncoder().encodeToString("123".getBytes()));
		usuarioRepository.save(usuario1);
		
		
	}

}
