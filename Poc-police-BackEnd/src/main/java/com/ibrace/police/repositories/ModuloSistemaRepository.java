package com.ibrace.police.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibrace.police.domain.ModuloSistema;

@Repository
public interface ModuloSistemaRepository extends JpaRepository<ModuloSistema, Integer> {


}
