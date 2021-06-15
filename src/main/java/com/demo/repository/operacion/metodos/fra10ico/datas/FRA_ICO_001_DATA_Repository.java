package com.demo.repository.operacion.metodos.fra10ico.datas;

import com.demo.model.operacion.metodos.fra10ico.datas.FRA_ICO_001_DATA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FRA_ICO_001_DATA_Repository extends JpaRepository<FRA_ICO_001_DATA, Long> {
    FRA_ICO_001_DATA findByIdFRAICODATA(Long id);

    @Query(value = "select * from fra_ico_001_data where idfraico = :idFRAICO", nativeQuery = true)
    List<FRA_ICO_001_DATA> buscarTodosPorEnsayo(@Param("idFRAICO") Long id);
}
