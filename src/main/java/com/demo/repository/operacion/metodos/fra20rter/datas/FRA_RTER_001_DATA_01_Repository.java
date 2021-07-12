package com.demo.repository.operacion.metodos.fra20rter.datas;

import com.demo.model.operacion.metodos.fra20rter.datas.FRA_RTER_001_DATA_01;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FRA_RTER_001_DATA_01_Repository extends JpaRepository<FRA_RTER_001_DATA_01, Long> {
    FRA_RTER_001_DATA_01 findByIdFRARTERDATA01(Long id);

    @Query(value = "select * from fra_rter_001_data_01 where idfrarter = :idFRARTER", nativeQuery = true)
    List<FRA_RTER_001_DATA_01> buscarTodosPorEnsayo(@Param("idFRARTER") Long id);
}
