package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.fra03es.FRA_ES_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRA_ES_001_Repository extends JpaRepository<FRA_ES_001, Long>{
    FRA_ES_001 findByIdFRAES(Long id);
    FRA_ES_001 findByMetodoMuestra_MetodoMuestraId(Long id);
    FRA_ES_001 findByIdInternoMuestra(String id);
}
