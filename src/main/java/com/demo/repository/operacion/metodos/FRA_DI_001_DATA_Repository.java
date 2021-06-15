package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.fra02di.datas.FRA_DI_001_DATA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FRA_DI_001_DATA_Repository extends JpaRepository<FRA_DI_001_DATA, Long> {
    FRA_DI_001_DATA findByIdFRADIDATA(Long id);

    @Query(value = "select * from fra_di_001_data where idfradi = :idFRADI", nativeQuery = true)
    List<FRA_DI_001_DATA> buscarTodosPorEnsayo(@Param("idFRADI") Long id);
}
