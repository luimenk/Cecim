package com.demo.service.operacion;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.repository.operacion.MetodoMuestraRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetodoMuestraService {

    @Autowired
    private MetodoMuestraRepository metodoMuestraRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public MetodoMuestra save(MetodoMuestra metodoMuestra) {
        return metodoMuestraRepository.save(metodoMuestra);
    }

    public List<MetodoMuestra> findAll() {
        return metodoMuestraRepository.findAll();
    }

    public MetodoMuestra findById(Long id) {
        return metodoMuestraRepository.findByMetodoMuestraId(id);
    }

    public List<MetodoMuestra> findAllByMetodo(Long id){
        return metodoMuestraRepository.findAllByMethod_MethodId(id);
    }

    public List<MetodoMuestra> findAllByMuestra(Long id){
        return metodoMuestraRepository.findAllBySolicitudServicioClienteMuestras_SolicitudServicioClienteMuestrasId(id);
    }

    public void delete(Long id) {
        metodoMuestraRepository.deleteById(id);
    }

    public long contar() {
        return metodoMuestraRepository.count();
    }
}