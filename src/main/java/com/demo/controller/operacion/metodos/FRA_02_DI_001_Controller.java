package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra02di.FRA_DI_001;
import com.demo.model.operacion.metodos.fra02di.datas.FRA_DI_001_DATA;
import com.demo.repository.operacion.metodos.FRA_DI_001_DATA_Repository;
import com.demo.service.formatos.metodos.FRA_02_DI_Print;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.metodos.FRA_DI_001_Service;
import com.demo.utils.Constantes;
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
@RequestMapping(path = "/FRADI")
public class FRA_02_DI_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_DI_001_Service fra_di_001_service;

    @Autowired
    private FRA_DI_001_DATA_Repository fra_di_001_data_repository;

    @Autowired
    private FRA_02_DI_Print fra_02_di_print;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_DI_001> getAll() {
        return fra_di_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_DI_001 get(@PathVariable("id") Long id) {
        FRA_DI_001 fra_di_001 = fra_di_001_service.findById(id);

        if (fra_di_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_di_001;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/idInterno/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_DI_001 getInterno(@PathVariable("id") String id) {
        FRA_DI_001 fra_di_001 = fra_di_001_service.findByIdInternoMuestra(id);

        if (fra_di_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_di_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    //public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {
    public ResponseEntity<?> create(@RequestPart("fradi") Map<String, String> request,
                                    @RequestPart("signature") MultipartFile signature,
                                    Principal principal) {

        FRA_DI_001 fra_di_001 = new FRA_DI_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        SaveInServer saveInServer = new SaveInServer();

        try {
            fra_di_001.setFolioTecnica(request.get("folioTecnica"));
            fra_di_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
            fra_di_001.setIdInternoMuestra(request.get("idInternoMuestra"));
            fra_di_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_di_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_di_001.setTemperatura(request.get("temperatura"));
            fra_di_001.setHumedadRelativa(request.get("humedadRelativa"));
            fra_di_001.setTipoCamiseta(request.get("tipoCamiseta"));

            fra_di_001.setCodigoRegla(request.get("codigoRegla"));

            fra_di_001.setRubricaRealizo(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_REALIZO_DI + saveInServer.SaveInServer(signature, Constantes.SIGNATURE_REALIZO_DI));

            fra_di_001.setObservaciones(request.get("observaciones"));
            fra_di_001.setRealizo(request.get("realizo"));
            fra_di_001.setSupervisor(request.get("supervisor"));

            fra_di_001.setMetodoMuestra(metodoMuestra);

            fra_di_001_service.save(fra_di_001);

            Double promedioLargo = 0.0, promedioAncho = 0.0, promedioFuelleDerecho = 0.0, promedioFuelleIzquierdo = 0.0;

            for (int i = 1; i < 6; i++) {
                FRA_DI_001_DATA fra_di_001_data = new FRA_DI_001_DATA();
                fra_di_001_data.setLargo(request.get("largo" + i));
                fra_di_001_data.setAncho(request.get("ancho" + i));
                if (fra_di_001.getTipoCamiseta().equals("Si")){
                    fra_di_001_data.setFuelleDerecho(request.get("fuelleDerecho" + i));
                    fra_di_001_data.setFuelleIzquierdo(request.get("fuelleIzquierdo" + i));
                } else {
                    fra_di_001_data.setFuelleDerecho("N/A");
                    fra_di_001_data.setFuelleIzquierdo("N/A");
                }
                fra_di_001_data.setFra_di_001(fra_di_001);
                fra_di_001_data_repository.save(fra_di_001_data);
                promedioLargo = promedioLargo + Double.parseDouble(request.get("largo" + i));
                promedioAncho = promedioAncho + Double.parseDouble(request.get("ancho" + i));
                if (fra_di_001.getTipoCamiseta().equals("Si")){
                    promedioFuelleDerecho = promedioFuelleDerecho + Double.parseDouble(request.get("fuelleDerecho" + i));
                    promedioFuelleIzquierdo = promedioFuelleIzquierdo + Double.parseDouble(request.get("fuelleIzquierdo" + i));
                }
            }

            fra_di_001.setPromedioLargo(String.format("%.2f", (promedioLargo / 5)));
            fra_di_001.setPromedioAncho(String.format("%.2f", (promedioAncho / 5)));
            if (fra_di_001.getTipoCamiseta().equals("Si")) {
                fra_di_001.setPromedioFuelleDerecho(String.format("%.2f", (promedioFuelleDerecho / 5)));
                fra_di_001.setPromedioFuelleIzquierdo(String.format("%.2f", (promedioFuelleIzquierdo / 5)));
            } else {
                fra_di_001.setPromedioFuelleDerecho("N/A");
                fra_di_001.setPromedioFuelleIzquierdo("N/A");
            }

            fra_di_001.setEstatus("FINALIZADO");
            fra_di_001.setCantidadModificaciones("3");

            fra_di_001_service.save(fra_di_001);

            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/imprimir1/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-DI-001");
        System.out.println(LocalTime.now());

        return fra_02_di_print.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-DI-001");
        System.out.println(LocalTime.now());

        return fra_02_di_print.crearFormato(id, 2);
    }
}
