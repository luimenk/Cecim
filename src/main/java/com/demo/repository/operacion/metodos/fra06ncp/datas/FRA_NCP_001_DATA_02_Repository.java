package com.demo.repository.operacion.metodos.fra06ncp.datas;

import com.demo.model.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_02;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FRA_NCP_001_DATA_02_Repository extends JpaRepository<FRA_NCP_001_DATA_02, Long>{
    FRA_NCP_001_DATA_02 findByIdFRANCPDATA02(Long id);

    @Query(value = "select * from fra_ncp_001_data_02 where idfrancp = :idFRANCP", nativeQuery = true)
    List<FRA_NCP_001_DATA_02> buscarTodosPorEnsayo(@Param("idFRANCP") Long id);
}
