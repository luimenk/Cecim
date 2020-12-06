package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.FRA_CST_001;
import com.demo.service.ClientService;
import com.demo.service.FoliosService;
import com.demo.service.MethodService;
import com.demo.service.QR.QRService;
import com.demo.service.formatos.FEIM_SOC_005_Service;
import com.demo.service.formatos.FSS_SOC_001_Service;
import com.demo.service.formatos.metodos.IMPRIMIR_FRA_CST_001_Service;
import com.demo.service.formatos.metodos.IMPRIMIR_FRA_DI_001_Service;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.RecepcionVerificacionRegistroCodificacionService;
import com.demo.service.operacion.SolicitudServicioClienteMuestrasService;
import com.demo.service.operacion.SolicitudServicioClienteService;
import com.demo.service.operacion.metodos.FRA_CST_001_Service;
import com.demo.service.operacion.metodos.FRA_DI_001_Service;
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
@RequestMapping(path = "/selladoTemperatura")
public class FRA_CST_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_CST_001_Service fra_cst_001_service;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    @Autowired
    private IMPRIMIR_FRA_CST_001_Service imprimir_fra_cst_001_service;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_CST_001> getAll() {
        return fra_cst_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_CST_001 get(@PathVariable("id") Long id) {
        FRA_CST_001 fra_cst_001 = fra_cst_001_service.findById(id);

        if (fra_cst_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_cst_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {
        APP.debug("Apartado de registro nuevo" + calendario.getTime());
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        FRA_CST_001 fra_cst_001 = new FRA_CST_001();

        fra_cst_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_cst_001.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_cst_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_cst_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
        fra_cst_001.setTemperatura(request.get("temperatura"));
        fra_cst_001.setHumedadRelativa(request.get("humedadRelativa"));

        fra_cst_001.setCodigoMicrometro(request.get("codigoMicrometro"));
        fra_cst_001.setCodigoLaboratorioSello(request.get("codigoLaboratorioSello"));
        fra_cst_001.setLargoMuestra(request.get("largoMuestra"));
        fra_cst_001.setAnchoMuestra(request.get("anchoMuestra"));
        fra_cst_001.setNumeroRepeticionesMuestra(request.get("numeroRepeticionesMuestra"));
        fra_cst_001.setRangoTemperatura(request.get("rangoTemperatura"));
        fra_cst_001.setTasaCalentamiento(request.get("tasaCalentamiento"));
        fra_cst_001.setTempoSellado(request.get("tempoSellado"));
        fra_cst_001.setTiempoRetraso(request.get("tiempoRetraso"));
        fra_cst_001.setPresion(request.get("presion"));
        fra_cst_001.setTipoMordazas(request.get("tipoMordazas"));
        fra_cst_001.setVelocidadEnsayo(request.get("velocidadEnsayo"));
        fra_cst_001.setTemperaturaOptima1(request.get("temperaturaOptima1"));
        fra_cst_001.setFuerzaSello(request.get("fuerzaSello"));
        fra_cst_001.setDesviacionEstandar(request.get("desviacionEstandar"));

        fra_cst_001.setObservaciones(request.get("observaciones"));
        fra_cst_001.setRealizo(request.get("realizo"));
        fra_cst_001.setSupervisor(request.get("supervisor"));

        fra_cst_001.setMetodoMuestra(metodoMuestra);

        fra_cst_001_service.save(fra_cst_001);

        metodoMuestra.setEstatus("OK");
        metodoMuestraService.save(metodoMuestra);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimirSelladoTemperatura/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-CST-001");
        System.out.println(LocalTime.now());

        return imprimir_fra_cst_001_service.crearFormato(id,1);
    }

    @RequestMapping(value = "/imprimirSelladoTemperatura2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-CST-001");
        System.out.println(LocalTime.now());

        return imprimir_fra_cst_001_service.crearFormato(id,2);
    }
}
