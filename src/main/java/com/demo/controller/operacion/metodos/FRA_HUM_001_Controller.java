package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
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
import com.demo.service.operacion.MetodoMuestraService;
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

    @Autowired
    private MetodoMuestraService metodoMuestraService;

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
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {
        APP.debug("Registro FRA_HUM a las: " + calendario.getTime());
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        FRA_HUM_001 fra_hum_001 = new FRA_HUM_001();

        fra_hum_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_hum_001.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_hum_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_hum_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
        fra_hum_001.setTemperatura(request.get("temperatura"));
        fra_hum_001.setHumedadRelativa(request.get("humedadRelativa"));
        fra_hum_001.setCodigoBalanza(request.get("codigoBalanza"));
        fra_hum_001.setCodigoHomo(request.get("codigoHomo"));
        fra_hum_001.setTemperaturaEnsayo(request.get("temperaturaEnsayo"));
        fra_hum_001.setNoCharola(request.get("noCharola"));
        fra_hum_001.setPesoCharola(request.get("pesoCharola"));
        fra_hum_001.setPesoMuestra(request.get("pesoMuestra"));
        fra_hum_001.setPds1(request.get("pds1"));
        fra_hum_001.setPds2(request.get("pds2"));
        fra_hum_001.setPds3(request.get("pds3"));
        //fra_hum_001.setPromedio(request.get("promedio"));
        //fra_hum_001.setHumedad(request.get("humedad"));
        fra_hum_001.setObservaciones(request.get("observaciones"));
        fra_hum_001.setRealizo(request.get("realizo"));
        fra_hum_001.setSupervisor(request.get("supervisor"));
        fra_hum_001.setMetodoMuestra(metodoMuestra);

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

        metodoMuestra.setEstatus("OK");
        metodoMuestraService.save(metodoMuestra);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_HUM a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_hum_001_service.crearFormato(id,1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_HUM a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_hum_001_service.crearFormato(id,2);
    }
}
