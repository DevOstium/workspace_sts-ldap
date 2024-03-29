package com.fenix.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fenix.domain.ModuloSistema;
import com.fenix.services.ModuloSistemaService;


@RestController
@RequestMapping(value = "/modulosistema")
public class ModuloSistemaResource {

	@Autowired
	private ModuloSistemaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ModuloSistema> find(@PathVariable Integer id) {
		ModuloSistema obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<ModuloSistema>> findAll() {
		List<ModuloSistema> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert( @RequestBody ModuloSistema objParam) {
			ModuloSistema obj = service.insert(objParam);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update( @RequestBody ModuloSistema objParam, @PathVariable Integer id) {
			service.update(objParam, id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
			service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
























