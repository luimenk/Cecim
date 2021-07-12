package com.demo.repository.operacion.metodos.fra19prr.datas;

import com.demo.model.operacion.metodos.fra19prr.datas.FRA_PRR_001_DATA_01;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FRA_PRR_001_DATA_01_Repository extends JpaRepository<FRA_PRR_001_DATA_01, Long> {
    FRA_PRR_001_DATA_01 findByIdFRAPRRDATA01(Long id);

    @Query(value = "select * from fra_prr_001_data_01 where idfraprr = :idFRAPRR", nativeQuery = true)
    List<FRA_PRR_001_DATA_01> buscarTodosPorEnsayo(@Param("idFRAPRR") Long id);
}
