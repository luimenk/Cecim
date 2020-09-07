package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_CST_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FRA_CST_001_Repository extends JpaRepository<FRA_CST_001, Long>{
    FRA_CST_001 findByCurvaSelladoId(Long id);
}
