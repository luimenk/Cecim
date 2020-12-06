package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_ES_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FRA_ES_001_Repository extends JpaRepository<FRA_ES_001, Long>{
    FRA_ES_001 findByDeterminacionEspesorId(Long id);
    FRA_ES_001 findByMetodoMuestra_MetodoMuestraId(Long id);
}
