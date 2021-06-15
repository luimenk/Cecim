package com.demo.repository.operacion.metodos.fra06ncp;

import com.demo.model.operacion.metodos.fra06ncp.FRA_NCP_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRA_NCP_001_Repository extends JpaRepository<FRA_NCP_001, Long> {
    FRA_NCP_001 findByIdFRANCP(Long id);
    FRA_NCP_001 findByMetodoMuestra_MetodoMuestraId(Long id);
    FRA_NCP_001 findByIdInternoMuestra(String id);
}
