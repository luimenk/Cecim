package com.demo.repository.operacion.metodos.fra02di;

import com.demo.model.operacion.metodos.fra02di.FRA_DI_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRA_DI_001_Repository extends JpaRepository<FRA_DI_001, Long>{
    FRA_DI_001 findByIdFRADI(Long id);
    FRA_DI_001 findByMetodoMuestra_MetodoMuestraId(Long id);
    FRA_DI_001 findByIdInternoMuestra(String id);
}
