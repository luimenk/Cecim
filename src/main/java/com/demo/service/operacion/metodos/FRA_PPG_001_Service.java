package com.demo.service.operacion.metodos;

import com.demo.utils.EstructuraNombres;
import com.demo.model.operacion.metodos.fra07ppg.FRA_PPG_001;
import com.demo.repository.operacion.metodos.fra07ppg.FRA_PPG_001_Repository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

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
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

@Service
public class FRA_PPG_001_Service {

    @Autowired
    private FRA_PPG_001_Repository fra_ppg_001_repository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_PPG_001 save(FRA_PPG_001 fra_ppg_001) {
        return fra_ppg_001_repository.save(fra_ppg_001);
    }

    public List<FRA_PPG_001> findAll() {
        return fra_ppg_001_repository.findAll();
    }

    public FRA_PPG_001 findById(Long id) {
        return fra_ppg_001_repository.findByIdFRAPPG(id);
    }

    public FRA_PPG_001 findByIdInternoMuestra(String id) {
        return fra_ppg_001_repository.findByIdInternoMuestra(id);
    }

    public FRA_PPG_001 findByMuestra(Long id) {
        return fra_ppg_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
    }

    public void delete(Long id) {
        fra_ppg_001_repository.deleteById(id);
    }

    public long contar() {
        return fra_ppg_001_repository.count();
    }
}
