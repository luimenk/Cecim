package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.fra14oit.FRA_OIT_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRA_OIT_001_Repository extends JpaRepository<FRA_OIT_001, Long>{
    FRA_OIT_001 findByIdFRAOIT(Long id);
    FRA_OIT_001 findByMetodoMuestra_MetodoMuestraId(Long id);
    FRA_OIT_001 findByIdInternoMuestra(String id);
}
