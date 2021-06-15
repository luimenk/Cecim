package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra03es.FRA_ES_001;
import com.demo.model.operacion.metodos.fra03es.datas.FRA_ES_001_DATA;
import com.demo.repository.operacion.metodos.FRA_ES_001_DATA_Repository;
import com.demo.service.formatos.metodos.FRA_03_ES_Print;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.metodos.FRA_ES_001_Service;
import com.demo.utils.Constantes;
import com.demo.utils.SaveInServer;
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
@RequestMapping(path = "/FRAES")
public class FRA_03_ES_001_Controller {

    @Autowired
    private FRA_ES_001_Service fra_es_001_service;

    @Autowired
    private FRA_ES_001_DATA_Repository fra_es_001_data_repository;

    @Autowired
    private FRA_03_ES_Print fra_03_es_print;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_ES_001> getAll() {
        return fra_es_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_ES_001 get(@PathVariable("id") Long id) {
        FRA_ES_001 fra_es_001 = fra_es_001_service.findById(id);

        if (fra_es_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_es_001;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/idInterno/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_ES_001 getInterno(@PathVariable("id") String id) {
        FRA_ES_001 fra_es_001 = fra_es_001_service.findByIdInternoMuestra(id);

        if (fra_es_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_es_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    //public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {
    public ResponseEntity<?> create(@RequestPart("fraes") Map<String, String> request,
                                    @RequestPart("signature") MultipartFile signature,
                                    Principal principal) {

        FRA_ES_001 fra_es_001 = new FRA_ES_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        SaveInServer saveInServer = new SaveInServer();

        try {
            fra_es_001.setFolioTecnica(request.get("folioTecnica"));
            fra_es_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
            fra_es_001.setIdInternoMuestra(request.get("idInternoMuestra"));
            fra_es_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_es_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_es_001.setTemperatura(request.get("temperatura"));
            fra_es_001.setHumedadRelativa(request.get("humedadRelativa"));

            fra_es_001.setCodigoMicrometro(request.get("codigoMicrometro"));
            fra_es_001.setNormaReferencia(request.get("normaReferencia"));
            fra_es_001.setAnchoProveta(request.get("anchoProveta"));
            fra_es_001.setNumeroMediciones(request.get("numeroMediciones"));

            fra_es_001.setRubricaRealizo(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_REALIZO_ES + saveInServer.SaveInServer(signature, Constantes.SIGNATURE_REALIZO_ES));

            fra_es_001.setObservaciones(request.get("observaciones"));
            fra_es_001.setRealizo(request.get("realizo"));
            fra_es_001.setSupervisor(request.get("supervisor"));

            fra_es_001.setMetodoMuestra(metodoMuestra);

            fra_es_001_service.save(fra_es_001);

            double promedioLargo = 0.0, promedioAncho = 0.0;
            int limite = Integer.parseInt(request.get("numeroMediciones"));

            for (int i = 1; i <= limite; i++) {
                FRA_ES_001_DATA fra_es_001_data = new FRA_ES_001_DATA();
                fra_es_001_data.setLargo(request.get("largo" + i));
                fra_es_001_data.setAncho(request.get("ancho" + i));
                fra_es_001_data.setFra_es_001(fra_es_001);
                fra_es_001_data_repository.save(fra_es_001_data);
                promedioLargo = promedioLargo + Double.parseDouble(request.get("largo" + i));
                promedioAncho = promedioAncho + Double.parseDouble(request.get("ancho" + i));
            }

            fra_es_001.setPromedioLargo(String.format("%.3f", (promedioLargo / limite)));
            fra_es_001.setPromedioAncho(String.format("%.3f", (promedioAncho / limite)));
            fra_es_001.setEstatus("FINALIZADO");
            fra_es_001.setCantidadModificaciones("3");

            fra_es_001_service.save(fra_es_001);

            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/imprimir1/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-ES-001");
        System.out.println(LocalTime.now());

        return fra_03_es_print.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-ES-001");
        System.out.println(LocalTime.now());

        return fra_03_es_print.crearFormato(id, 2);
    }
}
