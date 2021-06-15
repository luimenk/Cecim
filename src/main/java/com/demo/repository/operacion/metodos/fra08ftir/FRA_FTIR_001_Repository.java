package com.demo.repository.operacion.metodos.fra08ftir;

import com.demo.model.operacion.metodos.fra08ftir.FRA_FTIR_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRA_FTIR_001_Repository extends JpaRepository<FRA_FTIR_001, Long>{
    FRA_FTIR_001 findByIdFRAFTIR(Long id);
    FRA_FTIR_001 findByMetodoMuestra_MetodoMuestraId(Long id);
    FRA_FTIR_001 findByIdInternoMuestra(String id);
}
