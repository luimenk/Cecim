package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_PPG_001;
import com.demo.service.operacion.metodos.FRA_PPG_001_Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping(path = "/FRAPPG")
public class FRA_PPG_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_PPG_001_Service fra_ppg_001_service;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_PPG_001> getAll() {
        return fra_ppg_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_PPG_001 get(@PathVariable("id") Long id) {
        FRA_PPG_001 fra_ppg_001 = fra_ppg_001_service.findById(id);

        if (fra_ppg_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_ppg_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody FRA_PPG_001 fra_ppg_001) throws Exception {
        APP.debug("Registro FRA_PPG a las: " + calendario.getTime());

        float p1 = Float.parseFloat(fra_ppg_001.getPeso1());
        float p2 = Float.parseFloat(fra_ppg_001.getPeso2());
        float p3 = Float.parseFloat(fra_ppg_001.getPeso3());
        float p4 = Float.parseFloat(fra_ppg_001.getPeso4());
        float p5 = Float.parseFloat(fra_ppg_001.getPeso5());

        float n1 = Float.parseFloat(fra_ppg_001.getPellet1());
        float n2 = Float.parseFloat(fra_ppg_001.getPellet2());
        float n3 = Float.parseFloat(fra_ppg_001.getPellet3());
        float n4 = Float.parseFloat(fra_ppg_001.getPellet4());
        float n5 = Float.parseFloat(fra_ppg_001.getPellet5());

        float promedioPeso = (p1+p2+p3+p4+p5) / 5;
        float promedioPellet = (n1+n2+n3+n4+n5) / 5;

        float pxg = promedioPellet / promedioPeso;

        fra_ppg_001.setPromedioPeso(promedioPeso+"");
        fra_ppg_001.setPromedioPellet(promedioPellet+"");
        fra_ppg_001.setPelletXGramo(pxg+"");

        fra_ppg_001_service.save(fra_ppg_001);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_PPG a las: " + calendario.getTime());

        return fra_ppg_001_service.crearFormato(id);
    }
}
