package com.demo.repository.operacion.metodos.fra16rsc.datas;

import com.demo.model.operacion.metodos.fra16rsc.datas.FRA_RSC_001_DATA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FRA_RSC_001_DATA_Repository extends JpaRepository<FRA_RSC_001_DATA, Long> {
    FRA_RSC_001_DATA findByIdFRARSCDATA(Long id);

    @Query(value = "select * from fra_rsc_001_data where idfrarsc = :idFRARSC", nativeQuery = true)
    List<FRA_RSC_001_DATA> buscarTodosPorEnsayo(@Param("idFRARSC") Long id);
}
