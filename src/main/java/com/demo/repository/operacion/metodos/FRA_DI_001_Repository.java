package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_DI_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FRA_DI_001_Repository extends JpaRepository<FRA_DI_001, Long>{
    FRA_DI_001 findByDeterminacionDimensionesId(Long id);
    FRA_DI_001 findByMetodoMuestra_MetodoMuestraId(Long id);
}
