package com.demo.repository;

import com.demo.model.InformacionMuestrasOSC;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface InformacionMuestrasOSCRepository extends JpaRepository<InformacionMuestrasOSC, Long>{
    InformacionMuestrasOSC findByInformacionMuestrasOSCId(Long id);
}
