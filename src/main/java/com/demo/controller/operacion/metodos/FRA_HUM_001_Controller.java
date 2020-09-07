package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.metodos.*;
import com.demo.service.ClientService;
import com.demo.service.FoliosService;
import com.demo.service.MethodService;
import com.demo.service.QR.QRService;
import com.demo.service.formatos.FEIM_SOC_005_Service;
import com.demo.service.formatos.FSS_SOC_001_Service;
import com.demo.service.formatos.metodos.IMPRIMIR_FRA_CST_001_Service;
import com.demo.service.formatos.metodos.IMPRIMIR_FRA_DI_001_Service;
import com.demo.service.formatos.metodos.IMPRIMIR_FRA_PRR_001_Service;
import com.demo.service.operacion.RecepcionVerificacionRegistroCodificacionService;
import com.demo.service.operacion.SolicitudServicioClienteMuestrasService;
import com.demo.service.operacion.SolicitudServicioClienteService;
import com.demo.service.operacion.metodos.*;
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
@RequestMapping(path = "/FRAHUM")
public class FRA_HUM_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_HUM_001_Service fra_hum_001_service;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_HUM_001> getAll() {
        return fra_hum_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_HUM_001 get(@PathVariable("id") Long id) {
        FRA_HUM_001 fra_hum_001 = fra_hum_001_service.findById(id);

        if (fra_hum_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_hum_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody FRA_HUM_001 fra_hum_001) throws Exception {
        APP.debug("Registro FRA_HUM a las: " + calendario.getTime());

        float r1 = Float.parseFloat(fra_hum_001.getPds1());
        float r2 = Float.parseFloat(fra_hum_001.getPds2());
        float r3 = Float.parseFloat(fra_hum_001.getPds3());

        float pc = Float.parseFloat(fra_hum_001.getPesoCharola());
        float pm = Float.parseFloat(fra_hum_001.getPesoMuestra());

        float promedio = (r1+r2+r3) / 3;

        float H = (((pc+pm) - promedio) / pm)*100;

        fra_hum_001.setPromedio(promedio+"");
        fra_hum_001.setHumedad(H+"");

        fra_hum_001_service.save(fra_hum_001);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_HUM a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_hum_001_service.crearFormato(id);
    }
}
