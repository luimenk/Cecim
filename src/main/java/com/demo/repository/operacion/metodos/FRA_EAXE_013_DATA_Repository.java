package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_EAXE_013;
import com.demo.model.operacion.metodos.FRA_EAXE_013_DATA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FRA_EAXE_013_DATA_Repository extends JpaRepository<FRA_EAXE_013_DATA, Long> {
    FRA_EAXE_013_DATA findByIdFRAEAXEDATA(Long id);
    //List<FRA_EAXE_013_DATA> findAllByFra_eaxe_013(FRA_EAXE_013 fra_eaxe_013);

    @Query(value = "select * from fra_eaxe_013_data where idfraeaxe = :idFRAEAXE", nativeQuery = true)
    List<FRA_EAXE_013_DATA> buscarTodosPorEnsayo(@Param("idFRAEAXE") Long id);
}
