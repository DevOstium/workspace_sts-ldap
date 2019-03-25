package com.ibrace.police.compras;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibrace.police.services.UsuarioPoliceService;

@RestController
@RequestMapping(value = "/compras")
public class ComprasResource {

	@PreAuthorize("hasAnyRole('ADMIN', 'COMPRADOR')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> findAll(Authentication auth ) {
		
		String t = " Acessando as Compras com o Usuario do Police : " +   
				     UsuarioPoliceService.getUsuarioLogado(). getUsername() + " " +  
					 UsuarioPoliceService.getUsuarioLogado().getAuthorities().contains("ADMIN");

		return ResponseEntity.ok().body(t);
	}

}
