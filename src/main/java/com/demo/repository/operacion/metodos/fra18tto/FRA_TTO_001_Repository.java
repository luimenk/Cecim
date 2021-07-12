package com.demo.repository.operacion.metodos.fra18tto;

import com.demo.model.operacion.metodos.fra18tto.FRA_TTO_001;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRA_TTO_001_Repository extends JpaRepository<FRA_TTO_001, Long> {
    FRA_TTO_001 findByIdFRATTO(Long id);
    FRA_TTO_001 findByFolioTecnica(String id);
    FRA_TTO_001 findByMetodoMuestra_MetodoMuestraId(Long id);
    FRA_TTO_001 findByIdInternoMuestra(String id);
}
