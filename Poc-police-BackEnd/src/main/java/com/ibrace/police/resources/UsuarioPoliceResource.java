package com.ibrace.police.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibrace.police.domain.UsuarioPolice;
import com.ibrace.police.services.UsuarioPoliceService;

@RestController
@RequestMapping(value = "/usuarios-police")
public class UsuarioPoliceResource {

	@Autowired
	private UsuarioPoliceService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioPolice>> findAll() {

		List<UsuarioPolice> users = service.findAll();

		return ResponseEntity.ok().body(users);
	}

}
