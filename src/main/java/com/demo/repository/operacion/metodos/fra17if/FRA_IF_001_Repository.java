package com.demo.repository.operacion.metodos.fra17if;

import com.demo.model.operacion.metodos.fra17if.FRA_IF_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRA_IF_001_Repository extends JpaRepository<FRA_IF_001, Long>{
    FRA_IF_001 findByIdFRAIF(Long id);
    FRA_IF_001 findByFolioTecnica(String folio);
    FRA_IF_001 findByMetodoMuestra_MetodoMuestraId(Long id);
    FRA_IF_001 findByIdInternoMuestra(String id);
}
