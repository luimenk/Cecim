package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.fra11eat.datas.FRA_EAT_001_DATA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FRA_EAT_001_DATA_Repository extends JpaRepository<FRA_EAT_001_DATA, Long>{
    FRA_EAT_001_DATA findByIdFRAEATDATA(Long id);

    //List<FRA_EAT_001_DATA> findByFra_eat_001(FRA_EAT_001 fra_eat_001);
    //SELECT * FROM fra_eat_001_data WHERE idfraeat = 448;

    @Query(value = "select * from fra_eat_001_data where idfraeat = :idFRAEAT", nativeQuery = true)
    List<FRA_EAT_001_DATA> findRandPreguntas(@Param("idFRAEAT") Long id);
}
