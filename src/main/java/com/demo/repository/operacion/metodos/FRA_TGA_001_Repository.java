package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_TGA_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FRA_TGA_001_Repository extends JpaRepository<FRA_TGA_001, Long>{
    FRA_TGA_001 findByIdFRATGA(Long id);
}
