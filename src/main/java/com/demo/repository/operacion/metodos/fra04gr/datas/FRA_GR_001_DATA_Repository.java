package com.demo.repository.operacion.metodos.fra04gr.datas;

import com.demo.model.operacion.metodos.fra04gr.datas.FRA_GR_001_DATA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FRA_GR_001_DATA_Repository extends JpaRepository<FRA_GR_001_DATA, Long> {
    FRA_GR_001_DATA findByIdFRAGRDATA(Long id);

    @Query(value = "select * from fra_gr_001_data where idfragr = :idFRAGR", nativeQuery = true)
    List<FRA_GR_001_DATA> buscarTodosPorEnsayo(@Param("idFRAGR") Long id);
}
