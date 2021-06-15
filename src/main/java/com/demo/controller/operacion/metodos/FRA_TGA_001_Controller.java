package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra09tga.FRA_TGA_001;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.metodos.*;
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

import java.io.IOException;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping(path = "/FRATGA")
public class FRA_TGA_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_TGA_001_Service fra_tga_001_service;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_TGA_001> getAll() {
        return fra_tga_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_TGA_001 get(@PathVariable("id") Long id) {
        FRA_TGA_001 fra_tga_001 = fra_tga_001_service.findById(id);

        if (fra_tga_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_tga_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestPart("fratga") Map<String, String> request,
                                    @RequestPart("imagen") MultipartFile file,
                                    Principal principal) {
        APP.debug("Registro FRA_TGA a las: " + calendario.getTime());
        String filePath = "";
        FRA_TGA_001 fra_tga_001 = new FRA_TGA_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));

        if ("Linux".equals(System.getProperty("os.name"))){
            filePath = "/home/adpmx/java/laboratorio.cecim.com.mx/QR/";
        } else {
            filePath = System.getProperty("user.home") + "/";
        }

        SaveInServer saveInServer = new SaveInServer();
        try {
            fra_tga_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
            fra_tga_001.setIdInternoMuestra(request.get("idInternoMuestra"));
            fra_tga_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_tga_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_tga_001.setTemperatura(request.get("temperatura"));
            fra_tga_001.setHumedadRelativa(request.get("humedadRelativa"));
            fra_tga_001.setCodigoTGA(request.get("codigoTGA"));
            fra_tga_001.setCodigoBalanza(request.get("codigoBalanza"));
            fra_tga_001.setPeso(request.get("peso"));
            fra_tga_001.setPpmTGA(request.get("ppmTGA"));
            fra_tga_001.setTemperatura1(request.get("temperatura1"));
            fra_tga_001.setFlujoOxigeno1(request.get("flujoOxigeno1"));
            fra_tga_001.setFnpa1(request.get("fnpa1"));
            fra_tga_001.setFnpp1(request.get("fnpp1"));
            fra_tga_001.setTemperatura2(request.get("temperatura2"));
            fra_tga_001.setFlujoOxigeno2(request.get("flujoOxigeno2"));
            fra_tga_001.setFnpa2(request.get("fnpa2"));
            fra_tga_001.setFnpp2(request.get("fnpp2"));
            fra_tga_001.setTemperatura3(request.get("temperatura3"));
            fra_tga_001.setFlujoOxigeno3(request.get("flujoOxigeno3"));
            fra_tga_001.setFnpa3(request.get("fnpa3"));
            fra_tga_001.setFnpp3(request.get("fnpp3"));
            fra_tga_001.setTemperatura4(request.get("temperatura4"));
            fra_tga_001.setFlujoOxigeno4(request.get("flujoOxigeno4"));
            fra_tga_001.setFnpa4(request.get("fnpa4"));
            fra_tga_001.setFnpp4(request.get("fnpp4"));
            fra_tga_001.setTemperatura5(request.get("temperatura5"));
            fra_tga_001.setFlujoOxigeno5(request.get("flujoOxigeno5"));
            fra_tga_001.setFnpa5(request.get("fnpa5"));
            fra_tga_001.setFnpp5(request.get("fnpp5"));
            fra_tga_001.setTasaCalentamiento(request.get("tasaCalentamiento"));
            fra_tga_001.setTasaEnfriamiento(request.get("tasaEnfriamiento"));
            fra_tga_001.setRangoTemperatura1(request.get("rangoTemperatura1"));
            fra_tga_001.setCambioMasa1(request.get("cambioMasa1"));
            fra_tga_001.setRangoTemperatura2(request.get("rangoTemperatura2"));
            fra_tga_001.setCambioMasa2(request.get("cambioMasa2"));
            fra_tga_001.setRangoTemperatura3(request.get("rangoTemperatura3"));
            fra_tga_001.setCambioMasa3(request.get("cambioMasa3"));
            fra_tga_001.setRangoTemperatura4(request.get("rangoTemperatura4"));
            fra_tga_001.setCambioMasa4(request.get("cambioMasa4"));

            fra_tga_001.setPathImagen(filePath+saveInServer.SaveInServer(file, filePath));

            fra_tga_001.setObservaciones(request.get("observaciones"));
            fra_tga_001.setRealizo(request.get("realizo"));
            fra_tga_001.setSupervisor(request.get("supervisor"));

            fra_tga_001.setMetodoMuestra(metodoMuestra);

            fra_tga_001_service.save(fra_tga_001);

            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_TGA a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_tga_001_service.crearFormato(id,1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_TGA a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_tga_001_service.crearFormato(id,2);
    }
}
