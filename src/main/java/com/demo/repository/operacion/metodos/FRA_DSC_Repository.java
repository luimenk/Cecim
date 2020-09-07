package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_DSC;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FRA_DSC_Repository extends JpaRepository<FRA_DSC, Long>{
    FRA_DSC findByIdFRADSC(Long id);
}
