package com.demo.repository.operacion.metodos.fra18tto.datas;

import com.demo.model.operacion.metodos.fra18tto.datas.FRA_TTO_001_DATA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FRA_TTO_001_DATA_Repository extends JpaRepository<FRA_TTO_001_DATA, Long> {
    FRA_TTO_001_DATA findByIdFRATTODATA(Long id);

    @Query(value = "select * from fra_tto_001_data where idfratto = :idFRATTO", nativeQuery = true)
    List<FRA_TTO_001_DATA> buscarTodosPorEnsayo(@Param("idFRATTO") Long id);
}
