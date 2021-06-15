package com.demo.service.operacion.metodos;

import com.demo.model.operacion.metodos.fra11eat.FRA_EAT_001;
import com.demo.model.operacion.metodos.fra11eat.datas.FRA_EAT_001_DATA;
import com.demo.repository.operacion.metodos.FRA_EAT_001_DATA_Repository;
import com.demo.repository.operacion.metodos.FRA_EAT_001_Repository;
import com.demo.utils.EstructuraNombres;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.demo.utils.FormatoFechas;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

@Service
public class FRA_EAT_001_Service {

    @Autowired
    private FRA_EAT_001_Repository fra_eat_001_repository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_EAT_001 save(FRA_EAT_001 fra_eat_001) {
        return fra_eat_001_repository.save(fra_eat_001);
    }

    public List<FRA_EAT_001> findAll() {
        return fra_eat_001_repository.findAll();
    }

    public FRA_EAT_001 findById(Long id) {
        return fra_eat_001_repository.findByIdFRAEAT(id);
    }

    public FRA_EAT_001 findByFolio(String folio){
        return fra_eat_001_repository.findByFolioTecnica(folio);
    }

    public void delete(Long id) {
        fra_eat_001_repository.deleteById(id);
    }

    public long contar() {
        return fra_eat_001_repository.count();
    }


}
