package com.demo.service;

import com.demo.model.Etiqueta;
import com.demo.repository.EtiquetaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtiquetaService {

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public Etiqueta save(Etiqueta etiqueta) {
        return etiquetaRepository.save(etiqueta);
    }

    public List<Etiqueta> findAll() {
        return etiquetaRepository.findAll();
    }

    public Etiqueta findById(Long etiquetaId) {
        return etiquetaRepository.findByEtiquetaId(etiquetaId);
    }

    public void delete(Long etiquetaId) {
        etiquetaRepository.deleteById(etiquetaId);
    }

    public long contar() {
        return etiquetaRepository.count();
    }
}
