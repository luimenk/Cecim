package com.demo.repository.operacion.metodos.fra17if.datas;

import com.demo.model.operacion.metodos.fra17if.datas.FRA_IF_001_DATA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FRA_IF_001_DATA_Repository extends JpaRepository<FRA_IF_001_DATA, Long> {
    FRA_IF_001_DATA findByIdFRAIFDATA(Long id);

    @Query(value = "select * from fra_if_001_data where idfraif = :idFRAIF", nativeQuery = true)
    List<FRA_IF_001_DATA> buscarTodosPorEnsayo(@Param("idFRAIF") Long id);
}
