package com.demo.repository.operacion.metodos.fra16rsc;

import com.demo.model.operacion.metodos.fra16rsc.FRA_RSC_001;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRA_RSC_001_Repository extends JpaRepository<FRA_RSC_001, Long> {
    FRA_RSC_001 findByIdFRARSC(Long id);
    FRA_RSC_001 findByMetodoMuestra_MetodoMuestraId(Long id);
    FRA_RSC_001 findByIdInternoMuestra(String id);
}
