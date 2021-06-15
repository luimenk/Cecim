package com.demo.repository.operacion.metodos.fra05hum;

import com.demo.model.operacion.metodos.fra05hum.FRA_HUM_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRA_HUM_001_Repository extends JpaRepository<FRA_HUM_001, Long> {
    FRA_HUM_001 findByIdFRAHUM(Long id);
    FRA_HUM_001 findByFolioTecnica(String folio);
    FRA_HUM_001 findByMetodoMuestra_MetodoMuestraId(Long id);
    FRA_HUM_001 findByIdInternoMuestra(String id);
}
