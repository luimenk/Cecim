package com.demo.repository.operacion;

import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface SolicitudServicioClienteMuestrasRepository extends JpaRepository<SolicitudServicioClienteMuestras, Long>{
    SolicitudServicioClienteMuestras findBySolicitudServicioClienteMuestrasId(Long id);
    List<SolicitudServicioClienteMuestras> findAllBySolicitudServicioCliente_SolicitudServicioClienteId(Long id);
}
