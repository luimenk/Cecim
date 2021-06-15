package com.demo.repository.operacion.metodos.fra08ftir.datas;

import com.demo.model.operacion.metodos.fra08ftir.datas.FRA_FTIR_001_DATA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FRA_FTIR_001_DATA_Repository extends JpaRepository<FRA_FTIR_001_DATA, Long>{
    FRA_FTIR_001_DATA findByIdFRAFTIRDATA(Long id);

    @Query(value = "select * from fra_ftir_001_data where idfraftir = :idFRAFTIR", nativeQuery = true)
    List<FRA_FTIR_001_DATA> buscarTodosPorEnsayo(@Param("idFRAFTIR") Long id);
}
