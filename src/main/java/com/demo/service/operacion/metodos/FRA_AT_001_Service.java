package com.demo.service.operacion.metodos;

import com.demo.model.operacion.metodos.fra01at.FRA_AT_001;
import com.demo.repository.operacion.metodos.fra01at.FRA_AT_001_Repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FRA_AT_001_Service {

    @Autowired
    private FRA_AT_001_Repository fra_at_001_repository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_AT_001 save(FRA_AT_001 fra_at_001) {
        return fra_at_001_repository.save(fra_at_001);
    }

    public List<FRA_AT_001> findAll() {
        return fra_at_001_repository.findAll();
    }

    public FRA_AT_001 findById(Long id) {
        return fra_at_001_repository.findByIdFRAAT(id);
    }

    public void delete(Long id) {
        fra_at_001_repository.deleteById(id);
    }

    public long contar() {
        return fra_at_001_repository.count();
    }


}
