package com.demo.repository.operacion.metodos.fra07ppg;

import com.demo.model.operacion.metodos.fra07ppg.FRA_PPG_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRA_PPG_001_Repository extends JpaRepository<FRA_PPG_001, Long>{
    FRA_PPG_001 findByIdFRAPPG(Long id);
    FRA_PPG_001 findByMetodoMuestra_MetodoMuestraId(Long id);
    FRA_PPG_001 findByIdInternoMuestra(String id);
}
