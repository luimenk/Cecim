package com.demo.repository.operacion.metodos.fra01at;

import com.demo.model.operacion.metodos.fra01at.FRA_AT_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRA_AT_001_Repository extends JpaRepository<FRA_AT_001, Long>{
    FRA_AT_001 findByIdFRAAT(Long id);
    FRA_AT_001 findByMetodoMuestra_MetodoMuestraId(Long id);
}
