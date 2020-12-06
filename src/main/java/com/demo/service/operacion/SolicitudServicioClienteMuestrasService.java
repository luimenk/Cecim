package com.demo.service.operacion;

import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.repository.operacion.SolicitudServicioClienteMuestrasRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudServicioClienteMuestrasService {

    @Autowired
    private SolicitudServicioClienteMuestrasRepository solicitudServicioClienteMuestrasRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public SolicitudServicioClienteMuestras save(SolicitudServicioClienteMuestras solicitudServicioCliente) {
        return solicitudServicioClienteMuestrasRepository.save(solicitudServicioCliente);
    }

    public List<SolicitudServicioClienteMuestras> findAll() {
        return solicitudServicioClienteMuestrasRepository.findAll();
    }

    public SolicitudServicioClienteMuestras findById(Long id) {
        return solicitudServicioClienteMuestrasRepository.findBySolicitudServicioClienteMuestrasId(id);
    }

    public List<SolicitudServicioClienteMuestras> findAllBySolicitud(Long id){
        return solicitudServicioClienteMuestrasRepository.findAllBySolicitudServicioCliente_SolicitudServicioClienteId(id);
    }

    public List<SolicitudServicioClienteMuestras> findAllByMuestra(Long id){
        return solicitudServicioClienteMuestrasRepository.findAllBySolicitudServicioClienteMuestrasId(id);
    }

    public void delete(Long id) {
        solicitudServicioClienteMuestrasRepository.deleteById(id);
    }

    public long contar() {
        return solicitudServicioClienteMuestrasRepository.count();
    }

    public long contarMuestras(SolicitudServicioCliente solicitudServicioCliente){
        return solicitudServicioClienteMuestrasRepository.countSolicitudServicioClienteMuestrasBySolicitudServicioCliente(solicitudServicioCliente);
    }

    public long contarAcondicionadas(Long id, String estatus){
        return solicitudServicioClienteMuestrasRepository.countBySolicitudServicioCliente_SolicitudServicioClienteIdAndEstatus(id, estatus);
    }
}
