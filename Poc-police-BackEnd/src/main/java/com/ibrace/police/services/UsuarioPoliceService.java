package com.ibrace.police.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ibrace.police.domain.UsuarioPolice;
import com.ibrace.police.repositories.UsuarioPoliceRepository;
import com.ibrace.police.security.UserDetailsImpl;

@Service
public class UsuarioPoliceService {

	@Autowired
	private UsuarioPoliceRepository repo;

	public static UserDetailsImpl getUsuarioLogado() {
		try {
			return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

	public List<UsuarioPolice> findAll() {
		return repo.findAll();
	}
	
	
	public UsuarioPolice findByLogin(String login) {
		return repo.findByLogin(login);
	}
	
}
