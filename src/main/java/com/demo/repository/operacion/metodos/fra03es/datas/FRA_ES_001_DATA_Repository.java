package com.demo.repository.operacion.metodos.fra03es.datas;

import com.demo.model.operacion.metodos.fra03es.datas.FRA_ES_001_DATA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FRA_ES_001_DATA_Repository extends JpaRepository <FRA_ES_001_DATA, Long> {
    FRA_ES_001_DATA findByIdFRAESDATA(Long id);

    @Query(value = "select * from fra_es_001_data where idfraes = :idFRAES", nativeQuery = true)
    List<FRA_ES_001_DATA> buscarTodosPorEnsayo(@Param("idFRAES") Long id);
}
