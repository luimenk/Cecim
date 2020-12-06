package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.*;
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
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping(path = "/FRANCP")
public class FRA_NCP_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_NCP_001_Service fra_ncp_001_service;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_NCP_001> getAll() {
        return fra_ncp_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_NCP_001 get(@PathVariable("id") Long id) {
        FRA_NCP_001 fra_ncp_001 = fra_ncp_001_service.findById(id);

        if (fra_ncp_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_ncp_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestPart("francp") Map<String, String> request,
                                    @RequestPart("imagen") MultipartFile file,
                                    Principal principal) {
        APP.debug("Registro FRA_NCP a las: " + calendario.getTime());
        String filePath = "";
        FRA_NCP_001 fra_ncp_001 = new FRA_NCP_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));

        if ("Linux".equals(System.getProperty("os.name"))){
            filePath = "/home/adpmx/java/laboratorio.cecim.com.mx/QR/";
        } else {
            filePath = System.getProperty("user.home") + "/";
        }

        SaveInServer saveInServer = new SaveInServer();
        try {
            fra_ncp_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
            fra_ncp_001.setIdInternoMuestra(request.get("idInternoMuestra"));
            fra_ncp_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_ncp_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_ncp_001.setTemperatura(request.get("temperatura"));
            fra_ncp_001.setHumedadRelativa(request.get("humedadRelativa"));
            fra_ncp_001.setCodigoMicroscopio(request.get("codigoMicroscopio"));
            fra_ncp_001.setCpc(request.get("cpc"));
            fra_ncp_001.setCodigoMicrometro(request.get("codigoMicrometro"));
            fra_ncp_001.setEspesorTotalMuestra(request.get("espesorTotalMuestra"));
            fra_ncp_001.setEspesor1(request.get("espesor1"));
            fra_ncp_001.setEspesor2(request.get("espesor2"));
            fra_ncp_001.setEspesor3(request.get("espesor3"));
            fra_ncp_001.setEspesor4(request.get("espesor4"));
            fra_ncp_001.setEspesor5(request.get("espesor5"));
            fra_ncp_001.setEspesor6(request.get("espesor6"));
            fra_ncp_001.setTotal(request.get("total"));
            fra_ncp_001.setObservaciones(request.get("observaciones"));
            fra_ncp_001.setRealizo(request.get("realizo"));
            fra_ncp_001.setSupervisor(request.get("supervisor"));
            fra_ncp_001.setPathImagen(filePath+saveInServer.SaveInServer(file, filePath));
            fra_ncp_001.setMetodoMuestra(metodoMuestra);

            fra_ncp_001_service.save(fra_ncp_001);

            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_NCP a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_ncp_001_service.crearFormato(id,1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_NCP a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_ncp_001_service.crearFormato(id,2);
    }
}
