package com.demo.service.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_RTER_001;
import com.demo.repository.operacion.metodos.FRA_RTER_001_Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FRA_RTER_001_Service {

    @Autowired
    private FRA_RTER_001_Repository fra_rter_001_repository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_RTER_001 save(FRA_RTER_001 fra_rter_001) {
        return fra_rter_001_repository.save(fra_rter_001);
    }

    public List<FRA_RTER_001> findAll() {
        return fra_rter_001_repository.findAll();
    }

    public FRA_RTER_001 findById(Long id) {
        return fra_rter_001_repository.findByResistenciaTensionElongacionRupturaId(id);
    }

    public void delete(Long id) {
        fra_rter_001_repository.deleteById(id);
    }

    public long contar() {
        return fra_rter_001_repository.count();
    }
}
