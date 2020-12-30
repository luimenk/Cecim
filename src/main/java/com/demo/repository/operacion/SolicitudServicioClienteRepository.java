package com.demo.repository.operacion;

import com.demo.model.operacion.SolicitudServicioCliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface SolicitudServicioClienteRepository extends JpaRepository<SolicitudServicioCliente, Long>{
    SolicitudServicioCliente findBySolicitudServicioClienteId(Long id);
    SolicitudServicioCliente findByFolioSolitudServicioCliente(String folio);
    List<SolicitudServicioCliente> findAllByEstatusPago(String estatus);
}
