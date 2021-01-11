package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.*;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.metodos.*;
import com.demo.utils.SaveInServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.jni.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping(path = "/FRAOIT")
public class FRA_OIT_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_OIT_001_Service fra_oit_001_service;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_OIT_001> getAll() {
        return fra_oit_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_OIT_001 get(@PathVariable("id") Long id) {
        FRA_OIT_001 fra_oit_001 = fra_oit_001_service.findById(id);

        if (fra_oit_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_oit_001;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/idInterno/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_OIT_001 getInterno(@PathVariable("id") String id) {
        FRA_OIT_001 fra_oit_001 = fra_oit_001_service.findByIdInternoMuestra(id);

        if (fra_oit_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_oit_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestPart("fraoit") Map<String, String> request,
            /*@RequestPart("imagen") MultipartFile file,*/
                                    Principal principal) {
        APP.debug("Registro FRA_OIT a las: " + calendario.getTime());
        String filePath = "";
        FRA_OIT_001 fra_oit_001 = new FRA_OIT_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));

        if ("Linux".equals(System.getProperty("os.name"))) {
            filePath = "/home/adpmx/java/laboratorio.cecim.com.mx/QR/";
        } else {
            filePath = System.getProperty("user.home") + "/";
        }

        SaveInServer saveInServer = new SaveInServer();
        try {
            fra_oit_001.setFolioTecnica(request.get("folioTecnica"));
            fra_oit_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
            fra_oit_001.setIdInternoMuestra(request.get("idInternoMuestra"));
            fra_oit_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            //fra_oit_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_oit_001.setFechaFinalAnalisis("");
            fra_oit_001.setTemperatura(request.get("temperatura"));
            fra_oit_001.setHumedadRelativa(request.get("humedadRelativa"));
            fra_oit_001.setCodigoDSC(request.get("codigoDSC"));
            fra_oit_001.setCodigoBalanza(request.get("codigoBalanza"));
            fra_oit_001.setTiempoIsoterma(request.get("tiempoIsoterma"));

            if (request.get("numeroRepeticiones").equals("1")) {
                fra_oit_001.setEspesor1(request.get("espesor1"));
                fra_oit_001.setPeso1(request.get("peso1"));
                fra_oit_001.setPpmdsc1(request.get("ppmdsc1"));
                fra_oit_001.setEspesor2("N/A");
                fra_oit_001.setPeso2("N/A");
                fra_oit_001.setPpmdsc2("N/A");
            } else {
                fra_oit_001.setEspesor1(request.get("espesor1"));
                fra_oit_001.setPeso1(request.get("peso1"));
                fra_oit_001.setPpmdsc1(request.get("ppmdsc1"));
                fra_oit_001.setEspesor2(request.get("espesor2"));
                fra_oit_001.setPeso2(request.get("peso2"));
                fra_oit_001.setPpmdsc2(request.get("ppmdsc2"));
            }
            fra_oit_001.setRepeticion1OIT("");
            fra_oit_001.setRepeticion2OIT("");

            //float r1 = Float.parseFloat(request.get("repeticion1OIT"));
            //float r2 = Float.parseFloat(request.get("repeticion2OIT"));
            //float promedio = (r1+r2) / 2;
            //fra_oit_001.setPromedio(promedio+"");
            fra_oit_001.setPromedio("");


//            fra_oit_001.setObservaciones(request.get("observaciones"));
//            fra_oit_001.setRealizo(request.get("realizo"));
//            fra_oit_001.setSupervisor(request.get("supervisor"));
//            fra_oit_001.setPathImage(filePath+saveInServer.SaveInServer(file, filePath));
            fra_oit_001.setObservaciones("");
            fra_oit_001.setRealizo("");
            fra_oit_001.setSupervisor("");
            fra_oit_001.setPathImage("");
            fra_oit_001.setEstatus("INICIADO");
            fra_oit_001.setCantidadModificaciones("3");
            fra_oit_001.setNumeroRepeticiones(request.get("numeroRepeticiones"));
            fra_oit_001.setMetodoMuestra(metodoMuestra);
            fra_oit_001_service.save(fra_oit_001);
            metodoMuestra.setEstatus("INICIADO");
            metodoMuestraService.save(metodoMuestra);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/finalizar")
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create2(@RequestPart("fraoit") Map<String, String> request,
                                     @RequestPart("imagen") MultipartFile file,
                                     Principal principal) {
        APP.debug("Registro FRA_OIT a las: " + calendario.getTime());
        String filePath = "";
        FRA_OIT_001 fra_oit_001 = fra_oit_001_service.findById(Long.parseLong(request.get("id")));
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(fra_oit_001.getMetodoMuestra().getMetodoMuestraId());

        if ("Linux".equals(System.getProperty("os.name"))) {
            filePath = "/home/adpmx/java/laboratorio.cecim.com.mx/QR/";
        } else {
            filePath = System.getProperty("user.home") + "/";
        }

        SaveInServer saveInServer = new SaveInServer();
        try {
            fra_oit_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));

            if (fra_oit_001.getNumeroRepeticiones().equals("1")) {
                fra_oit_001.setRepeticion1OIT(request.get("repeticion1OIT"));
                fra_oit_001.setRepeticion2OIT("N/A");
                fra_oit_001.setPromedio(fra_oit_001.getRepeticion1OIT());
            } else {
                float r1 = Float.parseFloat(request.get("repeticion1OIT"));
                float r2 = Float.parseFloat(request.get("repeticion2OIT"));
                float promedio = (r1+r2) / 2;
                fra_oit_001.setRepeticion1OIT(request.get("repeticion1OIT"));
                fra_oit_001.setRepeticion2OIT(request.get("repeticion2OIT"));
                fra_oit_001.setPromedio(promedio+"");
            }

            fra_oit_001.setObservaciones(request.get("observaciones"));
            fra_oit_001.setRealizo(request.get("realizo"));
            fra_oit_001.setSupervisor(request.get("supervisor"));
            fra_oit_001.setPathImage(filePath+saveInServer.SaveInServer(file, filePath));
            fra_oit_001.setEstatus("FINALIZADO");
            fra_oit_001_service.save(fra_oit_001);
            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);
        } catch (NullPointerException | IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-OIT-001");
        System.out.println(LocalTime.now());

        return fra_oit_001_service.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-OIT-001");
        System.out.println(LocalTime.now());

        return fra_oit_001_service.crearFormato(id, 2);
    }
}
