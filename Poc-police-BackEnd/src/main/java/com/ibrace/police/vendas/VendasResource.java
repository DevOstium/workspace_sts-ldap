package com.ibrace.police.vendas;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibrace.police.services.UsuarioPoliceService;

@RestController
@RequestMapping(value = "/vendas")
public class VendasResource {


	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> findAll( ) {
		
		String t = "Acessando as vendas com o Usuario do Police : " + UsuarioPoliceService.getUsuarioLogado().getUsername();

		return ResponseEntity.ok().body(t);
	}

}
