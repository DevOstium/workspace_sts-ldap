package com.ibrace.police.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.ibrace.police.domain.ModuloSistema;
import com.ibrace.police.domain.Perfil;
import com.ibrace.police.domain.UsuarioPolice;
import com.ibrace.police.repositories.ModuloSistemaRepository;
import com.ibrace.police.repositories.PerfilRepository;
import com.ibrace.police.repositories.UsuarioPoliceRepository;

@Service
@Configuration
public class PopulateDB {
	
	@Autowired
	private UsuarioPoliceRepository usuario;
	
	@Autowired
	private ModuloSistemaRepository modulo; 

	@Autowired
	private PerfilRepository perfil;

	
	@Bean
	public void DB() {
		
		UsuarioPolice usuarioDiretor         = new UsuarioPolice(null, "jose.diretor");
		UsuarioPolice usuarioComprador       = new UsuarioPolice(null, "flavio.comprador");
		UsuarioPolice usuarioGerenteVendas   = new UsuarioPolice(null, "carmem.gerente.vendas");
		UsuarioPolice usuarioVendedor        = new UsuarioPolice(null, "fernanda.vendedora");

		Perfil perfilAdmin          = new Perfil(null, "ROLE_ADMIN");
		Perfil perfilComprador      = new Perfil(null, "ROLE_COMPRADOR");
		Perfil perfilGerenteVendas  = new Perfil(null, "ROLE_GERENTE_VENDAS");
		Perfil perfilVendedor       = new Perfil(null, "ROLE_VENDEDOR");

			   perfilComprador.setUsuarios(Arrays.asList(usuarioComprador));
			   perfilAdmin.setUsuarios(Arrays.asList(usuarioDiretor));
			   perfilGerenteVendas.setUsuarios(Arrays.asList(usuarioGerenteVendas));
			   perfilVendedor.setUsuarios(Arrays.asList(usuarioGerenteVendas, usuarioVendedor));
			   
			   
		ModuloSistema moduloCompras = new ModuloSistema(null, "Compras");
		ModuloSistema moduloVendas  = new ModuloSistema(null, "Vendas");
		
					  moduloCompras.setUsuarios(Arrays.asList(usuarioComprador, usuarioDiretor));
					  moduloVendas.setUsuarios(Arrays.asList(usuarioVendedor, usuarioGerenteVendas, usuarioDiretor));
		
	    usuario.saveAll(Arrays.asList( usuarioDiretor , usuarioComprador, usuarioGerenteVendas, usuarioVendedor));
		
		modulo.saveAll(Arrays.asList( moduloCompras , moduloVendas));
		
		perfil.saveAll( Arrays.asList(perfilAdmin, perfilComprador , perfilGerenteVendas, perfilVendedor));

	
	
	}

	
	
	
	
	
	
	
	
	
	
	
	
	/*
	private Set<Perfil> toSet(Perfil perfilAdmin, Perfil perfilVendedor) {
		List<Perfil> s1 = Arrays.asList( perfilAdmin, perfilVendedor );
		Set<Perfil> setVendas = s1.stream().collect(Collectors.toSet());
		return setVendas;
	}
	*/
	
	public static void main(String[] args) {
		
		Set<String> li = Arrays.asList("ADMIN",  "VENDEDOR", "COMPRADOR").stream().map( x -> x ).collect(Collectors.toSet());
					li.forEach( System.out::println );

					Perfil p1  = new Perfil(null, "ROLE_ADMIN");
					Perfil p2  = new Perfil(null, "ROLE_VENDEDOR");
					Perfil p3  = new Perfil(null, "ROLE_COMPRADOR");
					
					List<Perfil> sets = Arrays.asList( p1, p2, p3 );
					Set<Perfil> lista = sets.stream().collect(Collectors.toSet());
								lista.forEach( c ->  System.out.println(c.getNome()) );
					
					
	}
	
	
	
	
}















