package com.demo.repository.operacion.metodos.fra13eaxe;

import com.demo.model.operacion.metodos.fra13eaxe.FRA_EAXE_013;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRA_EAXE_013_Repository extends JpaRepository<FRA_EAXE_013, Long>{
    FRA_EAXE_013 findByIdFRAEAXE(Long id);
    FRA_EAXE_013 findByFolioTecnica(String folio);
    FRA_EAXE_013 findByMetodoMuestra_MetodoMuestraId(Long id);
}
