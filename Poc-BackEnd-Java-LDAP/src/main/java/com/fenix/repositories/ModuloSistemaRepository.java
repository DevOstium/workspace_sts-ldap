package com.fenix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fenix.domain.ModuloSistema;

@Repository
public interface ModuloSistemaRepository extends JpaRepository<ModuloSistema, Integer> {

}
