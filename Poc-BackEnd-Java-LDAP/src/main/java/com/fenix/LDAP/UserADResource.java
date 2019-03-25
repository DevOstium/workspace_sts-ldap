package com.fenix.LDAP;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user-ad")
public class UserADResource {

	@Autowired
	private UserADService service;

	@GetMapping
	public ResponseEntity<List<UserAD>> findAll() {
		List<UserAD> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/{login}", method = RequestMethod.GET)
	public ResponseEntity<List<UserAD>> find(@PathVariable String login) {
		
		List<UserAD> obj = service.findUserByLogin(login);
		
		return ResponseEntity.ok().body(obj);
	}
	
}
