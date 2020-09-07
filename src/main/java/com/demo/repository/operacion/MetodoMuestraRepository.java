package com.demo.repository.operacion;

import com.demo.model.operacion.MetodoMuestra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MetodoMuestraRepository extends JpaRepository<MetodoMuestra, Long>{
    MetodoMuestra findByMetodoMuestraId(Long id);
    List<MetodoMuestra> findAllByMethod_MethodId(Long id);
    List<MetodoMuestra> findAllBySolicitudServicioClienteMuestras_SolicitudServicioClienteMuestrasId(Long id);
}
