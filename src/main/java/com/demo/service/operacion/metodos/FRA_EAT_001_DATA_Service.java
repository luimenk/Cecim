package com.demo.service.operacion.metodos;

import com.demo.model.operacion.metodos.fra11eat.datas.FRA_EAT_001_DATA;
import com.demo.repository.operacion.metodos.fra11eat.datas.FRA_EAT_001_DATA_Repository;
import com.demo.utils.EstructuraNombres;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FRA_EAT_001_DATA_Service {

    @Autowired
    private FRA_EAT_001_DATA_Repository fra_eat_001_data_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_EAT_001_DATA save(FRA_EAT_001_DATA fra_eat_001_data) {
        return fra_eat_001_data_repository.save(fra_eat_001_data);
    }

    public List<FRA_EAT_001_DATA> findAll() {
        return fra_eat_001_data_repository.findAll();
    }

//    public List<FRA_EAT_001_DATA> findAllBy(FRA_EAT_001 fra_eat_001) {
//        return fra_eat_001_data_repository.findByFra_eat_001(fra_eat_001);
//    }

    public List<FRA_EAT_001_DATA> findRandPreguntas(Long id){
        return fra_eat_001_data_repository.findRandPreguntas(id);
    }

    public FRA_EAT_001_DATA findById(Long id) {
        return fra_eat_001_data_repository.findByIdFRAEATDATA(id);
    }

    public void delete(Long id) {
        fra_eat_001_data_repository.deleteById(id);
    }

    public long contar() {
        return fra_eat_001_data_repository.count();
    }
}
