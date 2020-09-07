package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_FTIR_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FRA_FTIR_001_Repository extends JpaRepository<FRA_FTIR_001, Long>{

    FRA_FTIR_001 findByCompuestosEspectrometriaInfrarrojaId(Long id);
}
