package com.demo.service.operacion.metodos;

import com.demo.model.operacion.metodos.fra05hum.FRA_HUM_001;
import com.demo.repository.operacion.metodos.fra05hum.FRA_HUM_001_Repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FRA_HUM_001_Service {

    @Autowired
    private FRA_HUM_001_Repository fra_hum_001_repository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_HUM_001 save(FRA_HUM_001 fra_hum) {
        return fra_hum_001_repository.save(fra_hum);
    }

    public List<FRA_HUM_001> findAll() {
        return fra_hum_001_repository.findAll();
    }

    public FRA_HUM_001 findById(Long id) {
        return fra_hum_001_repository.findByIdFRAHUM(id);
    }

    public FRA_HUM_001 findByFolio(String folio){
        return fra_hum_001_repository.findByFolioTecnica(folio);
    }

    public FRA_HUM_001 findByIdInternoMuestra(String id) {
        return fra_hum_001_repository.findByIdInternoMuestra(id);
    }

    public void delete(Long id) {
        fra_hum_001_repository.deleteById(id);
    }

    public long contar() {
        return fra_hum_001_repository.count();
    }


}
