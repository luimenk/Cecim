package com.demo.repository.operacion.metodos.fra13eaxe.datas;

import com.demo.model.operacion.metodos.fra13eaxe.datas.FRA_EAXE_013_DATA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FRA_EAXE_013_DATA_Repository extends JpaRepository<FRA_EAXE_013_DATA, Long> {
    FRA_EAXE_013_DATA findByIdFRAEAXEDATA(Long id);

    @Query(value = "select * from fra_eaxe_013_data where idfraeaxe = :idFRAEAXE", nativeQuery = true)
    List<FRA_EAXE_013_DATA> buscarTodosPorEnsayo(@Param("idFRAEAXE") Long id);
}
