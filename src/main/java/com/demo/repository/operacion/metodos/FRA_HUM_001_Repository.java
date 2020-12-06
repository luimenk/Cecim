package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_HUM_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FRA_HUM_001_Repository extends JpaRepository<FRA_HUM_001, Long> {
    FRA_HUM_001 findByIdFRAHUM(Long id);
    FRA_HUM_001 findByMetodoMuestra_MetodoMuestraId(Long id);
}
