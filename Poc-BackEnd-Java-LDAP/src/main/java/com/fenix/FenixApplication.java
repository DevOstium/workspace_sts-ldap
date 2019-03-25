package com.fenix;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fenix.domain.ModuloSistema;
import com.fenix.repositories.ModuloSistemaRepository;

@SpringBootApplication
public class FenixApplication  implements CommandLineRunner {
	
	@Autowired
	private ModuloSistemaRepository repo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(FenixApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {	
		
		List<ModuloSistema> modulos = Arrays.asList(	
				new ModuloSistema(null, "Comercial"),
				new ModuloSistema(null, "Laboratório"),
				new ModuloSistema(null, "Qualidade")
			);
		repo.saveAll(modulos);
		
	}	

}

