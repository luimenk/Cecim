package com.demo.repository.operacion.metodos.fra12eauv;

import com.demo.model.operacion.metodos.fra12eauv.FRA_EAUV_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRA_EAUV_001_Repository extends JpaRepository<FRA_EAUV_001, Long>{
    FRA_EAUV_001 findByIdFRAEAUV(Long id);
    FRA_EAUV_001 findByFolioTecnica(String folio);
    FRA_EAUV_001 findByMetodoMuestra_MetodoMuestraId(Long id);
}
