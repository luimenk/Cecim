package com.demo.repository.operacion.metodos.fra05hum.datas;

import com.demo.model.operacion.metodos.fra05hum.datas.FRA_HUM_001_DATA_01;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FRA_HUM_001_DATA_01_Repository extends JpaRepository<FRA_HUM_001_DATA_01, Long> {
    FRA_HUM_001_DATA_01 findByIdFRAHUMDATA01(Long id);

    @Query(value = "select * from fra_hum_001_data_01 where idfrahum = :idFRAHUM", nativeQuery = true)
    List<FRA_HUM_001_DATA_01> buscarTodosPorEnsayo(@Param("idFRAHUM") Long id);
}
