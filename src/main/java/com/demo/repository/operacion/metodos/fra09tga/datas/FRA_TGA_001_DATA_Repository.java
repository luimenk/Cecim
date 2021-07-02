package com.demo.repository.operacion.metodos.fra09tga.datas;

import com.demo.model.operacion.metodos.fra09tga.datas.FRA_TGA_001_DATA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface FRA_TGA_001_DATA_Repository extends JpaRepository<FRA_TGA_001_DATA, Long>{
    FRA_TGA_001_DATA findByIdFRATGADATA(Long id);

    @Query(value = "select * from fra_tga_001_data where idfratga = :idFRATGA", nativeQuery = true)
    List<FRA_TGA_001_DATA> buscarTodosPorEnsayo(@Param("idFRATGA") Long id);

}
