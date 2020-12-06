package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.FRA_CST_001;
import com.demo.model.operacion.metodos.FRA_DSC;
import com.demo.model.operacion.metodos.FRA_PRR_001;
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
import com.demo.service.operacion.metodos.FRA_CST_001_Service;
import com.demo.service.operacion.metodos.FRA_DI_001_Service;
import com.demo.service.operacion.metodos.FRA_DSC_Service;
import com.demo.service.operacion.metodos.FRA_PRR_001_Service;
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
@RequestMapping(path = "/FRADSC")
public class FRA_DSC_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_DSC_Service fra_dsc_service;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_DSC> getAll() {
        return fra_dsc_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_DSC get(@PathVariable("id") Long id) {
        FRA_DSC fra_dsc = fra_dsc_service.findById(id);

        if (fra_dsc == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_dsc;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {
        APP.debug("Apartado de registro nuevo" + calendario.getTime());
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        FRA_DSC fra_dsc = new FRA_DSC();

        fra_dsc.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_dsc.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_dsc.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_dsc.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
        fra_dsc.setTemperatura(request.get("temperatura"));
        fra_dsc.setHumedadRelativa(request.get("humedadRelativa"));

        fra_dsc.setCodigoDSC(request.get("codigoDSC"));
        fra_dsc.setCodigoBalanza(request.get("codigoBalanza"));
        fra_dsc.setEspesor1(request.get("espesor1"));
        fra_dsc.setPeso1(request.get("peso1"));
        fra_dsc.setPpmDSC1(request.get("ppmDSC1"));

        fra_dsc.setTemperatura1(request.get("temperatura1"));
        fra_dsc.setFlujoOxigeno1(request.get("flujoOxigeno1"));
        fra_dsc.setFnatmosfera1(request.get("fnatmosfera1"));
        fra_dsc.setFnproteccion1(request.get("fnproteccion1"));

        fra_dsc.setTemperatura2(request.get("temperatura2"));
        fra_dsc.setFlujoOxigeno2(request.get("flujoOxigeno2"));
        fra_dsc.setFnatmosfera2(request.get("fnatmosfera2"));
        fra_dsc.setFnproteccion2(request.get("fnproteccion2"));

        fra_dsc.setTemperatura3(request.get("temperatura3"));
        fra_dsc.setFlujoOxigeno3(request.get("flujoOxigeno3"));
        fra_dsc.setFnatmosfera3(request.get("fnatmosfera3"));
        fra_dsc.setFnproteccion3(request.get("fnproteccion3"));

        fra_dsc.setTemperatura4(request.get("temperatura4"));
        fra_dsc.setFlujoOxigeno4(request.get("flujoOxigeno4"));
        fra_dsc.setFnatmosfera4(request.get("fnatmosfera4"));
        fra_dsc.setFnproteccion4(request.get("fnproteccion4"));

        fra_dsc.setTemperatura5(request.get("temperatura5"));
        fra_dsc.setFlujoOxigeno5(request.get("flujoOxigeno5"));
        fra_dsc.setFnatmosfera5(request.get("fnatmosfera5"));
        fra_dsc.setFnproteccion5(request.get("fnproteccion5"));

        fra_dsc.setTemperatura6(request.get("temperatura6"));
        fra_dsc.setFlujoOxigeno6(request.get("flujoOxigeno6"));
        fra_dsc.setFnatmosfera6(request.get("fnatmosfera6"));
        fra_dsc.setFnproteccion6(request.get("fnproteccion6"));

        fra_dsc.setTemperatura7(request.get("temperatura7"));
        fra_dsc.setFlujoOxigeno7(request.get("flujoOxigeno7"));
        fra_dsc.setFnatmosfera7(request.get("fnatmosfera7"));
        fra_dsc.setFnproteccion7(request.get("fnproteccion7"));

        fra_dsc.setTemperatura8(request.get("temperatura8"));
        fra_dsc.setFlujoOxigeno8(request.get("flujoOxigeno8"));
        fra_dsc.setFnatmosfera8(request.get("fnatmosfera8"));
        fra_dsc.setFnproteccion8(request.get("fnproteccion8"));

        fra_dsc.setTasaCalentamiento(request.get("tasaCalentamiento"));
        fra_dsc.setTasaEnfriamiento(request.get("tasaEnfriamiento"));
        fra_dsc.setTemperaturaFusion1(request.get("temperaturaFusion1"));
        fra_dsc.setCalorFusion1(request.get("calorFusion1"));
        fra_dsc.setTemperaturaCristalizacion1(request.get("temperaturaCristalizacion1"));
        fra_dsc.setCalorCristalizacion1(request.get("calorCristalizacion1"));
        fra_dsc.setObservaciones(request.get("observaciones"));
        fra_dsc.setRealizo(request.get("realizo"));
        fra_dsc.setSupervisor(request.get("supervisor"));
        fra_dsc.setMetodoMuestra(metodoMuestra);

        fra_dsc_service.save(fra_dsc);

        metodoMuestra.setEstatus("OK");
        metodoMuestraService.save(metodoMuestra);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-DSC-001");
        System.out.println(LocalTime.now());

        return fra_dsc_service.crearFormato(id,1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-DSC-001");
        System.out.println(LocalTime.now());

        return fra_dsc_service.crearFormato(id,2);
    }
}
