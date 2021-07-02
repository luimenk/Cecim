package com.demo.service.operacion.metodos;

import com.demo.model.operacion.metodos.fra13eaxe.datas.FRA_EAXE_013_DATA;
import com.demo.repository.operacion.metodos.fra13eaxe.datas.FRA_EAXE_013_DATA_Repository;
import com.demo.utils.EstructuraNombres;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FRA_EAXE_013_DATA_Service {

    @Autowired
    private FRA_EAXE_013_DATA_Repository fra_eaxe_013_data_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_EAXE_013_DATA save(FRA_EAXE_013_DATA fra_eaxe_013_data) {
        return fra_eaxe_013_data_repository.save(fra_eaxe_013_data);
    }

    public List<FRA_EAXE_013_DATA> findAll() {
        return fra_eaxe_013_data_repository.findAll();
    }

    public List<FRA_EAXE_013_DATA> buscarPorEnsayo(Long id){
        return fra_eaxe_013_data_repository.buscarTodosPorEnsayo(id);
    }

    public FRA_EAXE_013_DATA findById(Long id) {
        return fra_eaxe_013_data_repository.findByIdFRAEAXEDATA(id);
    }

    public void delete(Long id) {
        fra_eaxe_013_data_repository.deleteById(id);
    }

    public long contar() {
        return fra_eaxe_013_data_repository.count();
    }
}
