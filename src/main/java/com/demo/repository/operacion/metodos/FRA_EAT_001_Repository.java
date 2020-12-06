package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_EAT_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FRA_EAT_001_Repository extends JpaRepository<FRA_EAT_001, Long>{
    FRA_EAT_001 findByIdFRAEAT(Long id);
    FRA_EAT_001 findByMetodoMuestra_MetodoMuestraId(Long id);
}
