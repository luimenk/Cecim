package com.demo.repository.operacion.metodos.fra20rter;

import com.demo.model.operacion.metodos.fra20rter.FRA_RTER_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRA_RTER_001_Repository extends JpaRepository<FRA_RTER_001, Long>{
    FRA_RTER_001 findByResistenciaTensionElongacionRupturaId(Long id);
    FRA_RTER_001 findByMetodoMuestra_MetodoMuestraId(Long id);
}
