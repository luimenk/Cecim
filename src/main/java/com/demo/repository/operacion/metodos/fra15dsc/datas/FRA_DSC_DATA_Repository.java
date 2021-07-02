package com.demo.repository.operacion.metodos.fra15dsc.datas;

import com.demo.model.operacion.metodos.fra15dsc.datas.FRA_DSC_001_DATA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FRA_DSC_DATA_Repository extends JpaRepository<FRA_DSC_001_DATA, Long> {
    FRA_DSC_001_DATA findByIdFRADSCDATA(Long id);

    @Query(value = "select * from fra_dsc_001_data where idfradsc = :idFRADSC", nativeQuery = true)
    List<FRA_DSC_001_DATA> buscarTodosPorEnsayo(@Param("idFRADSC") Long id);
}
