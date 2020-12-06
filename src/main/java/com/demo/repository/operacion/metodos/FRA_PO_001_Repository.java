package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_PO_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FRA_PO_001_Repository extends JpaRepository<FRA_PO_001, Long>{
    FRA_PO_001 findByIdFRAPO(Long id);
    FRA_PO_001 findByMetodoMuestra_MetodoMuestraId(Long id);
}
