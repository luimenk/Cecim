package com.demo.repository.operacion.metodos.fra20rter.datas;

import com.demo.model.operacion.metodos.fra20rter.datas.FRA_RTER_001_DATA_02;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FRA_RTER_001_DATA_02_Repository extends JpaRepository<FRA_RTER_001_DATA_02, Long> {
    FRA_RTER_001_DATA_02 findByIdFRARTERDATA02(Long id);

    @Query(value = "select * from fra_rter_001_data_02 where idfrarter = :idFRARTER", nativeQuery = true)
    List<FRA_RTER_001_DATA_02> buscarTodosPorEnsayo(@Param("idFRARTER") Long id);
}
