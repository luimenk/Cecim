package com.demo.repository.operacion.metodos.fra07ppg.datas;

import com.demo.model.operacion.metodos.fra07ppg.datas.FRA_PPG_001_DATA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FRA_PPG_001_DATA_Repository extends JpaRepository<FRA_PPG_001_DATA, Long> {
    FRA_PPG_001_DATA findByIdFRAPPGDATA(Long id);

    @Query(value = "select * from fra_ppg_001_data where idfrappg = :idFRAPPG", nativeQuery = true)
    List<FRA_PPG_001_DATA> buscarTodosPorEnsayo(@Param("idFRAPPG") Long id);
}
