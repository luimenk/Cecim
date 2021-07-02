package com.demo.service.operacion.metodos;

import com.demo.model.operacion.metodos.fra07ppg.FRA_PPG_001;
import com.demo.model.operacion.metodos.fra09tga.FRA_TGA_001;
import com.demo.repository.operacion.metodos.fra09tga.FRA_TGA_001_Repository;
import com.demo.utils.EstructuraNombres;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.demo.utils.FormatoFechas;
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
public class FRA_TGA_001_Service {

    @Autowired
    private FRA_TGA_001_Repository fra_tga_001_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_TGA_001 save(FRA_TGA_001 fra_tga_001) {
        return fra_tga_001_repository.save(fra_tga_001);
    }

    public List<FRA_TGA_001> findAll() {
        return fra_tga_001_repository.findAll();
    }

    public FRA_TGA_001 findById(Long id) {
        return fra_tga_001_repository.findByIdFRATGA(id);
    }

    public FRA_TGA_001 findByIdInternoMuestra(String id) {
        return fra_tga_001_repository.findByIdInternoMuestra(id);
    }

    public FRA_TGA_001 findByMuestra(Long id) {
        return fra_tga_001_repository.findByMetodoMuestra_MetodoMuestraId(id);
    }

    public void delete(Long id) {
        fra_tga_001_repository.deleteById(id);
    }

    public long contar() {
        return fra_tga_001_repository.count();
    }


}
