package com.demo.repository;

import com.demo.model.Folios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface FoliosRepository extends JpaRepository<Folios, Long>{
    Folios findByFolioId(Long idFolios);
    Folios findByNombreFolio(String nombre);
}
