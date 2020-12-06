package com.demo.service.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_CST_001;
import com.demo.repository.operacion.metodos.FRA_CST_001_Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FRA_CST_001_Service {

    @Autowired
    private FRA_CST_001_Repository fra_cst_001_repository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_CST_001 save(FRA_CST_001 fra_cst_001) {
        return fra_cst_001_repository.save(fra_cst_001);
    }

    public List<FRA_CST_001> findAll() {
        return fra_cst_001_repository.findAll();
    }

    public FRA_CST_001 findById(Long id) {
        return fra_cst_001_repository.findByCurvaSelladoId(id);
    }

    public FRA_CST_001 findByMuestra(Long id) {
        return fra_cst_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
    }

    public void delete(Long id) {
        fra_cst_001_repository.deleteById(id);
    }

    public long contar() {
        return fra_cst_001_repository.count();
    }
}
