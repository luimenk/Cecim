package com.demo.repository.operacion;

import com.demo.model.operacion.MetodoMuestra;

import com.demo.model.operacion.SolicitudServicioCliente;
import org.apache.commons.math3.optim.linear.SolutionCallback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MetodoMuestraRepository extends JpaRepository<MetodoMuestra, Long>{
    MetodoMuestra findByMetodoMuestraId(Long id);
    MetodoMuestra findBySolicitudServicioClienteMuestras_SolicitudServicioClienteMuestrasId(Long id);
    void deleteAllBySolicitudServicioClienteMuestras_SolicitudServicioClienteMuestrasId(Long id);
    List<MetodoMuestra> findAllByMethod_MethodId(Long id);
    List<MetodoMuestra> findAllBySolicitudServicioClienteMuestras_SolicitudServicioClienteMuestrasId(Long id);
    //List<MetodoMuestra> findAllBySolicitudServicioClienteSolicitudServicioClienteIdMuestras_SolicitudServicioCliente(SolicitudServicioCliente solicitudServicioCliente);
    List<MetodoMuestra> findAllBySolicitudServicioClienteMuestras_SolicitudServicioCliente(SolicitudServicioCliente solicitudServicioCliente);
    List<MetodoMuestra> findAllByMetodoMuestraId(Long id);
    Long countBySolicitudServicioClienteMuestras_SolicitudServicioClienteAndEstatus(SolicitudServicioCliente solicitudServicioCliente, String Estatus);
}
