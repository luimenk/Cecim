package com.demo.repository.operacion.metodos.fra04gr;

import com.demo.model.operacion.metodos.fra04gr.FRA_GR_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRA_GR_001_Repository extends JpaRepository<FRA_GR_001, Long>{
    FRA_GR_001 findByIdFRAGR(Long id);
    FRA_GR_001 findByMetodoMuestra_MetodoMuestraId(Long id);
    FRA_GR_001 findByIdInternoMuestra(String id);
}
