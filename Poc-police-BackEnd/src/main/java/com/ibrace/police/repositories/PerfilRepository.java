package com.ibrace.police.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibrace.police.domain.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {


}
