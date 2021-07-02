package com.demo.repository.operacion.metodos.fra14oit.datas;

import com.demo.model.operacion.metodos.fra14oit.datas.FRA_OIT_001_CONDITION;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRA_OIT_001_CONDITION_Repository extends JpaRepository<FRA_OIT_001_CONDITION, Long> {
    FRA_OIT_001_CONDITION findByIdFRAOITCONDITION(Long id);
}
