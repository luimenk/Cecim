package com.demo.service.operacion.metodos;

import com.demo.model.operacion.metodos.fra03es.FRA_ES_001;
import com.demo.repository.operacion.metodos.fra03es.FRA_ES_001_Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FRA_ES_001_Service {

    @Autowired
    private FRA_ES_001_Repository fra_es_001_repository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_ES_001 save(FRA_ES_001 fra_di_001) {
        return fra_es_001_repository.save(fra_di_001);
    }

    public List<FRA_ES_001> findAll() {
        return fra_es_001_repository.findAll();
    }

    public FRA_ES_001 findById(Long id) {
        return fra_es_001_repository.findByIdFRAES(id);
    }

    public FRA_ES_001 findByIdInternoMuestra(String id) {
        return fra_es_001_repository.findByIdInternoMuestra(id);
    }

    public FRA_ES_001 findByMuestra(Long id) {
        return fra_es_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
    }

    public void delete(Long id) {
        fra_es_001_repository.deleteById(id);
    }

    public long contar() {
        return fra_es_001_repository.count();
    }
}