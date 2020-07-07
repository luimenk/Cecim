package com.demo.service.operacion;

import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.repository.operacion.SolicitudServicioClienteRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudServicioClienteService {

    @Autowired
    private SolicitudServicioClienteRepository solicitudServicioClienteRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public SolicitudServicioCliente save(SolicitudServicioCliente solicitudServicioCliente) {
        return solicitudServicioClienteRepository.save(solicitudServicioCliente);
    }

    public List<SolicitudServicioCliente> findAll() {
        return solicitudServicioClienteRepository.findAll();
    }

    public SolicitudServicioCliente findById(Long id) {
        return solicitudServicioClienteRepository.findBySolicitudServicioClienteId(id);
    }

    public SolicitudServicioCliente findByIdFolio(String folio) {
        return solicitudServicioClienteRepository.findByFolioSolitudServicioCliente(folio);
    }

    public void delete(Long id) {
        solicitudServicioClienteRepository.deleteById(id);
    }

    public long contar() {
        return solicitudServicioClienteRepository.count();
    }
}
