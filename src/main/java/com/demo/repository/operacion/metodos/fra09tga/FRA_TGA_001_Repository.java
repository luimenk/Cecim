package com.demo.repository.operacion.metodos.fra09tga;

import com.demo.model.operacion.metodos.fra09tga.FRA_TGA_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRA_TGA_001_Repository extends JpaRepository<FRA_TGA_001, Long>{
    FRA_TGA_001 findByIdFRATGA(Long id);
    FRA_TGA_001 findByMetodoMuestra_MetodoMuestraId(Long id);
    FRA_TGA_001 findByIdInternoMuestra(String id);
}
