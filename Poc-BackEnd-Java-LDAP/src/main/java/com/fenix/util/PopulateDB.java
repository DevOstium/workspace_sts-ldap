package com.fenix.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenix.domain.ModuloSistema;
import com.fenix.repositories.ModuloSistemaRepository;

@Service
public class PopulateDB {

	@Autowired
	private ModuloSistemaRepository repo;
	
	
	public void insertDB () {
		
		List<ModuloSistema> modulos = Arrays.asList(	
														new ModuloSistema(null, "Comercial"),
														new ModuloSistema(null, "Laboratório"),
														new ModuloSistema(null, "Qualidade")
													);
		repo.saveAll(modulos);
	}
}
