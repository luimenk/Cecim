package com.demo.repository.operacion;

import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RecepcionVerificacionRegistroCodificacionRepository extends JpaRepository<RecepcionVerificacionRegistroCodificacion, Long>{
    //SolicitudServicioClienteMuestras findBySolicitudServicioClienteMuestrasId(Long id);
    RecepcionVerificacionRegistroCodificacion findByRecepcionVerificacionRegistroCodificacionId(Long id);
    RecepcionVerificacionRegistroCodificacion findBySolicitudServicioClienteMuestras_SolicitudServicioClienteMuestrasId(Long id);
    List<RecepcionVerificacionRegistroCodificacion> findAllBySolicitudServicioClienteMuestras_SolicitudServicioClienteMuestrasId(Long id);

}
