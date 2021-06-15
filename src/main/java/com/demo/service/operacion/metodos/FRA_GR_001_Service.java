package com.demo.service.operacion.metodos;

import com.demo.model.operacion.metodos.fra04gr.FRA_GR_001;
import com.demo.repository.operacion.metodos.fra04gr.FRA_GR_001_Repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FRA_GR_001_Service {

    @Autowired
    private FRA_GR_001_Repository fra_gr_001_repository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_GR_001 save(FRA_GR_001 fra_gr_001) {
        return fra_gr_001_repository.save(fra_gr_001);
    }

    public List<FRA_GR_001> findAll() {
        return fra_gr_001_repository.findAll();
    }

    public FRA_GR_001 findById(Long id) {
        return fra_gr_001_repository.findByIdFRAGR(id);
    }

    public FRA_GR_001 findByIdInternoMuestra(String id) {
        return fra_gr_001_repository.findByIdInternoMuestra(id);
    }

    public void delete(Long id) {
        fra_gr_001_repository.deleteById(id);
    }

    public long contar() {
        return fra_gr_001_repository.count();
    }


}
