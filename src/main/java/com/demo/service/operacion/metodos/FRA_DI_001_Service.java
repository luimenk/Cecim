package com.demo.service.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_DI_001;
import com.demo.repository.operacion.metodos.FRA_DI_001_Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FRA_DI_001_Service {

    @Autowired
    private FRA_DI_001_Repository fra_di_001_repository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_DI_001 save(FRA_DI_001 fra_di_001) {
        return fra_di_001_repository.save(fra_di_001);
    }

    public List<FRA_DI_001> findAll() {
        return fra_di_001_repository.findAll();
    }

    public FRA_DI_001 findById(Long id) {
        return fra_di_001_repository.findByDeterminacionDimensionesId(id);
    }

    public void delete(Long id) {
        fra_di_001_repository.deleteById(id);
    }

    public long contar() {
        return fra_di_001_repository.count();
    }
}
