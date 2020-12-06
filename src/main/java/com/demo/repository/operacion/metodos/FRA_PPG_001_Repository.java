package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_PPG_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FRA_PPG_001_Repository extends JpaRepository<FRA_PPG_001, Long>{
    FRA_PPG_001 findByIdFRAPPG(Long id);
    FRA_PPG_001 findByMetodoMuestra_MetodoMuestraId(Long id);
}
