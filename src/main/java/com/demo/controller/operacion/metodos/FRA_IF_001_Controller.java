package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra17if.FRA_IF_001;
import com.demo.service.operacion.MetodoMuestraService;
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

import java.util.*;

@RestController
@RequestMapping(path = "/FRAIF")
public class FRA_IF_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_IF_001_Service fra_if_001_service;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_IF_001> getAll() {
        return fra_if_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_IF_001 get(@PathVariable("id") Long id) {
        FRA_IF_001 fra_if_001 = fra_if_001_service.findById(id);

        if (fra_if_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_if_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {
        APP.debug("Registro FRA_IF a las: " + calendario.getTime());
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        FRA_IF_001 fra_if_001 = new FRA_IF_001();

        fra_if_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_if_001.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_if_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_if_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
        fra_if_001.setTemperatura(request.get("temperatura"));
        fra_if_001.setHumedadRelativa(request.get("humedadRelativa"));
        fra_if_001.setCodigoBalanzaAnalitica(request.get("codigoBalanzaAnalitica"));
        fra_if_001.setCodigoPlastometro(request.get("codigoPlastometro"));
        fra_if_001.setTemperaturaEnsayo(request.get("temperaturaEnsayo"));
        fra_if_001.setPesaEnsayo(request.get("pesaEnsayo"));
        fra_if_001.setTiempoCorte(request.get("tiempoCorte"));
//        fra_if_001.setPeso(request.get("peso"));
//        fra_if_001.setIndiceFluidez(request.get("indiceFluidez"));
        fra_if_001.setObservaciones(request.get("observaciones"));
        fra_if_001.setRealizo(request.get("realizo"));
        fra_if_001.setSupervisor(request.get("supervisor"));
        fra_if_001.setMetodoMuestra(metodoMuestra);

        fra_if_001_service.save(fra_if_001);

        metodoMuestra.setEstatus("OK");
        metodoMuestraService.save(metodoMuestra);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_IF a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_if_001_service.crearFormato(id,1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_IF a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_if_001_service.crearFormato(id,2);
    }
}
