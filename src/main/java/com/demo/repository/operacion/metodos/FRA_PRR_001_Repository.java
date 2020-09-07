package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_PRR_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FRA_PRR_001_Repository extends JpaRepository<FRA_PRR_001, Long>{
    FRA_PRR_001 findByIdFRAPRR(Long id);
}
