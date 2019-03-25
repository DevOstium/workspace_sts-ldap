package com.fenix.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fenix.domain.ModuloSistema;
import com.fenix.repositories.ModuloSistemaRepository;
import com.fenix.services.exceptions.DataIntegrityException;
import com.fenix.services.exceptions.ObjectNotFoundException;


@Service
public class ModuloSistemaService {

	@Autowired
	private ModuloSistemaRepository repo;
	
	public ModuloSistema find(Integer id) {
		Optional<ModuloSistema> obj = repo.findById(id);
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + ModuloSistema.class.getName()));
	}

	public List<ModuloSistema> findAll() {
		return repo.findAll();
	}

	public ModuloSistema insert(ModuloSistema obj) {
			obj.setId(null);
		return repo.save(obj);
	}

	public void update(ModuloSistema objParam, Integer id) {
		ModuloSistema obj = find(id);
					  obj.setDescricao(objParam.getDescricao());
		try {
			repo.save(obj);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível editar");
		}
		
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir");
		}
	}
}





























