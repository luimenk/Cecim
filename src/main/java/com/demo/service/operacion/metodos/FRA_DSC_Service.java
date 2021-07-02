package com.demo.service.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;
import com.demo.model.operacion.metodos.fra09tga.FRA_TGA_001;
import com.demo.repository.operacion.MetodoMuestraRepository;
import com.demo.repository.operacion.RecepcionVerificacionRegistroCodificacionRepository;
import com.demo.utils.EstructuraNombres;
import com.demo.model.operacion.metodos.fra15dsc.FRA_DSC;
import com.demo.repository.operacion.metodos.fra15dsc.FRA_DSC_Repository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.demo.utils.FormatoFechas;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
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
public class FRA_DSC_Service {

    @Autowired
    private FRA_DSC_Repository fra_dsc_repository;

    @Autowired
    private MetodoMuestraRepository metodoMuestraRepository;

    @Autowired
    private RecepcionVerificacionRegistroCodificacionRepository recepcionVerificacionRegistroCodificacionRepository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();
    FormatoFechas formatoFechas = new FormatoFechas();

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_DSC save(FRA_DSC fra_dsc) {
        return fra_dsc_repository.save(fra_dsc);
    }

    public List<FRA_DSC> findAll() {
        return fra_dsc_repository.findAll();
    }

    public FRA_DSC findById(Long id) {
        return fra_dsc_repository.findByIdFRADSC(id);
    }

    public FRA_DSC findByIdInternoMuestra(String id) {
        return fra_dsc_repository.findByIdInternoMuestra(id);
    }

    public FRA_DSC findByMuestra(Long id) {
        return fra_dsc_repository.findByMetodoMuestra_MetodoMuestraId(id);
    }

    public void delete(Long id) {
        fra_dsc_repository.deleteById(id);
    }

    public long contar() {
        return fra_dsc_repository.count();
    }


}
