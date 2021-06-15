package com.demo.repository.operacion.metodos.fra10ico;

import com.demo.model.operacion.metodos.fra10ico.FRA_ICO_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRA_ICO_001_Repository extends JpaRepository<FRA_ICO_001, Long>{
    FRA_ICO_001 findByIdFRAICO(Long id);
    FRA_ICO_001 findByFolioTecnica(String folio);
    FRA_ICO_001 findByMetodoMuestra_MetodoMuestraId(Long id);
    FRA_ICO_001 findByIdInternoMuestra(String id);
}
