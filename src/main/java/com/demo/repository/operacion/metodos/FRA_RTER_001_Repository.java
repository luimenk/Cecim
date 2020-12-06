package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_RTER_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FRA_RTER_001_Repository extends JpaRepository<FRA_RTER_001, Long>{
    FRA_RTER_001 findByResistenciaTensionElongacionRupturaId(Long id);
    FRA_RTER_001 findByMetodoMuestra_MetodoMuestraId(Long id);
}
