package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_AT_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FRA_AT_001_Repository extends JpaRepository<FRA_AT_001, Long>{
    FRA_AT_001 findByIdFRAAT(Long id);
}
