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
@RequestMapping(path = "/FRAPO")
public class FRA_PO_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_PO_001_Service fra_po_001_service;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_PO_001> getAll() {
        return fra_po_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_PO_001 get(@PathVariable("id") Long id) {
        FRA_PO_001 fra_po_001 = fra_po_001_service.findById(id);

        if (fra_po_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_po_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestPart("frapo") Map<String, String> request,
                                    @RequestPart("imagen") MultipartFile file,
                                    Principal principal) {
        APP.debug("Registro FRA_PO a las: " + calendario.getTime());
        String filePath = "";
        FRA_PO_001 fra_po_001 = new FRA_PO_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));

        if ("Linux".equals(System.getProperty("os.name"))){
            filePath = "/home/adpmx/java/laboratorio.cecim.com.mx/QR/";
        } else {
            filePath = System.getProperty("user.home") + "/";
        }

        SaveInServer saveInServer = new SaveInServer();
        try {
            fra_po_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
            fra_po_001.setIdInternoMuestra(request.get("idInternoMuestra"));
            fra_po_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_po_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_po_001.setTemperatura(request.get("temperatura"));
            fra_po_001.setHumedadRelativa(request.get("humedadRelativa"));
            fra_po_001.setCodigoOXTRAN(request.get("codigoOXTRAN"));
            fra_po_001.setCodigoMicrometro(request.get("codigoMicrometro"));
            fra_po_001.setEspesor1(request.get("espesor1"));
            fra_po_001.setTiempoPurga1(request.get("tiempoPurga1"));
            fra_po_001.setTipoMascarilla1(request.get("tipoMascarilla1"));
            fra_po_001.setEspesor2(request.get("espesor2"));
            fra_po_001.setTiempoPurga2(request.get("tiempoPurga2"));
            fra_po_001.setTipoMascarilla2(request.get("tipoMascarilla2"));
            fra_po_001.setNumeroCiclos(request.get("numeroCiclos"));
            fra_po_001.setTiempoCiclo(request.get("tiempoCiclo"));
            fra_po_001.setConvergencia(request.get("convergencia"));
            fra_po_001.setPermeabilidadOxigeno1(request.get("permeabilidadOxigeno1"));
            fra_po_001.setPermeabilidadOxigeno2(request.get("permeabilidadOxigeno2"));
            fra_po_001.setObservaciones(request.get("observaciones"));
            fra_po_001.setRealizo(request.get("realizo"));
            fra_po_001.setSupervisor(request.get("supervisor"));
            fra_po_001.setPathImagen(filePath+saveInServer.SaveInServer(file, filePath));
            fra_po_001.setMetodoMuestra(metodoMuestra);

            fra_po_001_service.save(fra_po_001);

            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_PO a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_po_001_service.crearFormato(id,1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_PO a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_po_001_service.crearFormato(id,2);
    }
}
