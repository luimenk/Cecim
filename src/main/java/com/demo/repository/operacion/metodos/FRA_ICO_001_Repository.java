package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_ICO_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FRA_ICO_001_Repository extends JpaRepository<FRA_ICO_001, Long>{
    FRA_ICO_001 findByIdFRAICO(Long id);
    FRA_ICO_001 findByMetodoMuestra_MetodoMuestraId(Long id);
}
