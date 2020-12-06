package com.demo.service.operacion;

import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;
import com.demo.repository.operacion.RecepcionVerificacionRegistroCodificacionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecepcionVerificacionRegistroCodificacionService {
    @Autowired
    private RecepcionVerificacionRegistroCodificacionRepository recepcionVerificacionRegistroCodificacionRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public RecepcionVerificacionRegistroCodificacion save(RecepcionVerificacionRegistroCodificacion solicitudServicioCliente) {
        return recepcionVerificacionRegistroCodificacionRepository.save(solicitudServicioCliente);
    }

    public List<RecepcionVerificacionRegistroCodificacion> findAll() {
        return recepcionVerificacionRegistroCodificacionRepository.findAll();
    }

    public RecepcionVerificacionRegistroCodificacion findById(Long id) {
        return recepcionVerificacionRegistroCodificacionRepository.findByRecepcionVerificacionRegistroCodificacionId(id);
    }

    public RecepcionVerificacionRegistroCodificacion findBySolicitudServicioClienteMuestrasId(Long id){
        return recepcionVerificacionRegistroCodificacionRepository.findBySolicitudServicioClienteMuestras_SolicitudServicioClienteMuestrasId(id);
    }

    public List<RecepcionVerificacionRegistroCodificacion> findAllBySolicitudServicioClienteMuestrasId(Long id){
        return recepcionVerificacionRegistroCodificacionRepository.findAllBySolicitudServicioClienteMuestras_SolicitudServicioClienteMuestrasId(id);
    }

    /*public List<RecepcionVerificacionRegistroCodificacion> findAllBySolicitud(Long id){
        return recepcionVerificacionRegistroCodificacionRepository.findAllBySolicitudServicioCliente_SolicitudServicioClienteId(id);
    }*/

    public void delete(Long id) {
        recepcionVerificacionRegistroCodificacionRepository.deleteById(id);
    }

    public long contar() {
        return recepcionVerificacionRegistroCodificacionRepository.count();
    }
}
