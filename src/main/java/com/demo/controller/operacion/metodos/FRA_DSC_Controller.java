package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.FRA_CST_001;
import com.demo.model.operacion.metodos.FRA_DSC;
import com.demo.model.operacion.metodos.FRA_OIT_001;
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
import com.demo.utils.SaveInServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping(path = "/FRADSC")
public class FRA_DSC_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();
    SaveInServer saveInServer = new SaveInServer();

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

    @RequestMapping(method = RequestMethod.GET, value = "/idInterno/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_DSC getInterno(@PathVariable("id") String id) {
        FRA_DSC fra_dsc = fra_dsc_service.findByIdInternoMuestra(id);

        if (fra_dsc == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_dsc;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestPart("fraoit") Map<String, String> request, Principal principal) {
        APP.debug("Registro FRA_OIT a las: " + calendario.getTime());
        FRA_DSC fra_dsc = new FRA_DSC();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));

        try {
            fra_dsc.setFolioTecnica(request.get("folioTecnica"));
            fra_dsc.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
            fra_dsc.setIdInternoMuestra(request.get("idInternoMuestra"));
            fra_dsc.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_dsc.setFechaFinalAnalisis("");
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

            fra_dsc.setTemperaturaFusion1("");
            fra_dsc.setCalorFusion1("");
            fra_dsc.setTemperaturaCristalizacion1("");
            fra_dsc.setCalorCristalizacion1("");

            fra_dsc.setObservaciones("");
            fra_dsc.setRealizo("");
            fra_dsc.setSupervisor("");

            fra_dsc.setPathImage("");
            fra_dsc.setEstatus("INICIADO");
            fra_dsc.setCantidadModificaciones("3");
            fra_dsc.setMetodoMuestra(metodoMuestra);
            fra_dsc_service.save(fra_dsc);
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
    public ResponseEntity<?> create2(@RequestPart("fradsc") Map<String, String> request,
                                     @RequestPart("imagen") MultipartFile file,
                                     Principal principal) {
        String filePath = "";
        FRA_DSC fra_dsc = fra_dsc_service.findById(Long.parseLong(request.get("id")));
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(fra_dsc.getMetodoMuestra().getMetodoMuestraId());

        if ("Linux".equals(System.getProperty("os.name"))) {
            filePath = "/home/adpmx/java/laboratorio.cecim.com.mx/QR/";
        } else {
            filePath = System.getProperty("user.home") + "/";
        }

        try {
            fra_dsc.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_dsc.setTemperaturaFusion1(request.get("temperaturaFusion1"));
            fra_dsc.setCalorFusion1(request.get("calorFusion1"));
            fra_dsc.setTemperaturaCristalizacion1(request.get("temperaturaCristalizacion1"));
            fra_dsc.setCalorCristalizacion1(request.get("calorCristalizacion1"));


            fra_dsc.setObservaciones(request.get("observaciones"));
            fra_dsc.setRealizo(request.get("realizo"));
            fra_dsc.setSupervisor(request.get("supervisor"));
            fra_dsc.setPathImage(filePath+saveInServer.SaveInServer(file, filePath));
            fra_dsc.setEstatus("FINALIZADO");
            fra_dsc_service.save(fra_dsc);
            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);
        } catch (NullPointerException | IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/modificar")
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> modificar(@RequestPart("fradsc") Map<String, String> request,
                                       @RequestPart(value = "imagen", required = false) MultipartFile file,
                                       Principal principal) {
        APP.debug("Modificar FRA_DSC a las: " + calendario.getTime());
        String filePath = "";

        FRA_DSC fra_dsc = fra_dsc_service.findById(Long.parseLong(request.get("id")));

        if (fra_dsc.getCantidadModificaciones().equals("0")){
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }

        try {
            fra_dsc.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_dsc.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_dsc.setTemperatura(request.get("temperatura"));
            fra_dsc.setHumedadRelativa(request.get("humedadRelativa"));
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

            fra_dsc.setEstatus("MODIFICADO");

            int aux = Integer.parseInt(fra_dsc.getCantidadModificaciones());
            aux--;
            fra_dsc.setCantidadModificaciones(aux + "");

            if (file == null) {
                System.out.println("El archivo no fue modificado");
            } else {
                fra_dsc.setPathImage(filePath + saveInServer.SaveInServer(file, filePath));
            }

            fra_dsc_service.save(fra_dsc);

        } catch (NullPointerException | IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-DSC-001");
        System.out.println(LocalTime.now());

        return fra_dsc_service.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-DSC-001");
        System.out.println(LocalTime.now());

        return fra_dsc_service.crearFormato(id, 2);
    }

    @RequestMapping(value = "/imprimir3", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir3() throws Exception {
        System.out.println("Se generó BFF-MIE-016");
        System.out.println(LocalTime.now());

        return fra_dsc_service.crearListaFolios();
    }
}
