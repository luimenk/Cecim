package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.FRA_IF_001;
import com.demo.model.operacion.metodos.FRA_PPG_001;
import com.demo.service.operacion.MetodoMuestraService;
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

    @Autowired
    private MetodoMuestraService metodoMuestraService;

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
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {
        APP.debug("Registro FRA_PPG a las: " + calendario.getTime());
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        FRA_PPG_001 fra_ppg_001 = new FRA_PPG_001();

        fra_ppg_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_ppg_001.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_ppg_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_ppg_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
        fra_ppg_001.setTemperatura(request.get("temperatura"));
        fra_ppg_001.setHumedadRelativa(request.get("humedadRelativa"));
        fra_ppg_001.setCodigoBalanzaAnalitica(request.get("codigoBalanzaAnalitica"));
        fra_ppg_001.setPeso1(request.get("peso1"));
        fra_ppg_001.setPeso2(request.get("peso2"));
        fra_ppg_001.setPeso3(request.get("peso3"));
        fra_ppg_001.setPeso4(request.get("peso4"));
        fra_ppg_001.setPeso5(request.get("peso5"));
        fra_ppg_001.setPellet1(request.get("pellet1"));
        fra_ppg_001.setPellet2(request.get("pellet2"));
        fra_ppg_001.setPellet3(request.get("pellet3"));
        fra_ppg_001.setPellet4(request.get("pellet4"));
        fra_ppg_001.setPellet5(request.get("pellet5"));
        //fra_ppg_001.setPromedioPeso(request.get("promedioPeso"));
        //fra_ppg_001.setPromedioPellet(request.get("promedioPellet"));
        //fra_ppg_001.setPelletXGramo(request.get("pelletXGramo"));
        fra_ppg_001.setObservaciones(request.get("observaciones"));
        fra_ppg_001.setRealizo(request.get("realizo"));
        fra_ppg_001.setSupervisor(request.get("supervisor"));
        fra_ppg_001.setMetodoMuestra(metodoMuestra);

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

        metodoMuestra.setEstatus("OK");
        metodoMuestraService.save(metodoMuestra);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_PPG a las: " + calendario.getTime());

        return fra_ppg_001_service.crearFormato(id,1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_PPG a las: " + calendario.getTime());

        return fra_ppg_001_service.crearFormato(id,2);
    }
}
