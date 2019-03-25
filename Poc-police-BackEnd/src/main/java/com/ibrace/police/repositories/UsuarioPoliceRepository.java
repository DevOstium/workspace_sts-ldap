package com.ibrace.police.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibrace.police.domain.UsuarioPolice;

@Repository
public interface UsuarioPoliceRepository extends JpaRepository<UsuarioPolice, Integer> {
	
	@Transactional
	UsuarioPolice findByLogin(String login);

}
