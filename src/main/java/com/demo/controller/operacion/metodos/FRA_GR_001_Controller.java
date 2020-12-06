package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.FRA_CST_001;
import com.demo.model.operacion.metodos.FRA_DSC;
import com.demo.model.operacion.metodos.FRA_GR_001;
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
@RequestMapping(path = "/FRAGR")
public class FRA_GR_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_GR_001_Service fra_gr_001_service;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_GR_001> getAll() {
        return fra_gr_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_GR_001 get(@PathVariable("id") Long id) {
        FRA_GR_001 fra_gr_001 = fra_gr_001_service.findById(id);

        if (fra_gr_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_gr_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {
        APP.debug("Apartado de registro nuevo" + calendario.getTime());
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        FRA_GR_001 fra_gr_001 = new FRA_GR_001();

        fra_gr_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_gr_001.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_gr_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_gr_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
        fra_gr_001.setTemperatura(request.get("temperatura"));
        fra_gr_001.setHumedadRelativa(request.get("humedadRelativa"));
        fra_gr_001.setCodigoBalanzaAnalitica(request.get("codigoBalanzaAnalitica"));
        fra_gr_001.setPeso1(request.get("peso1"));
        fra_gr_001.setPeso2(request.get("peso2"));
        fra_gr_001.setPeso3(request.get("peso3"));
        fra_gr_001.setPeso4(request.get("peso4"));
        fra_gr_001.setPeso5(request.get("peso5"));
        //fra_gr_001.setPromedio(request.get("promedio"));
        fra_gr_001.setAreaProbeta(request.get("areaProbeta"));
        //fra_gr_001.setGramaje(request.get("gramaje"));
        fra_gr_001.setObservaciones(request.get("observaciones"));
        fra_gr_001.setRealizo(request.get("realizo"));
        fra_gr_001.setSupervisor(request.get("supervisor"));
        fra_gr_001.setMetodoMuestra(metodoMuestra);



        float r1 = Float.parseFloat(request.get("peso1"));
        float r2 = Float.parseFloat(request.get("peso2"));
        float r3 = Float.parseFloat(request.get("peso3"));
        float r4 = Float.parseFloat(request.get("peso4"));
        float r5 = Float.parseFloat(request.get("peso5"));
        float ap = Float.parseFloat(request.get("areaProbeta"));

        float promedio = (r1+r2+r3+r4+r5) / 5;

        float gramaje = promedio / ap;

        fra_gr_001.setPromedio(promedio+"");
        fra_gr_001.setGramaje(gramaje+"");

        fra_gr_001_service.save(fra_gr_001);

        metodoMuestra.setEstatus("OK");
        metodoMuestraService.save(metodoMuestra);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-GR-001");
        System.out.println(LocalTime.now());

        return fra_gr_001_service.crearFormato(id,1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-GR-001");
        System.out.println(LocalTime.now());

        return fra_gr_001_service.crearFormato(id,2);
    }
}
