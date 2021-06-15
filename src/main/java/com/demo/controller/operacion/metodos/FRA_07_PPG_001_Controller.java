package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra07ppg.FRA_PPG_001;
import com.demo.model.operacion.metodos.fra07ppg.datas.FRA_PPG_001_DATA;
import com.demo.repository.operacion.metodos.fra07ppg.datas.FRA_PPG_001_DATA_Repository;
import com.demo.service.formatos.metodos.FRA_07_PPG_Print;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.metodos.FRA_PPG_001_Service;
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
import java.util.*;

@RestController
@RequestMapping(path = "/FRAPPG")
public class FRA_07_PPG_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_PPG_001_Service fra_ppg_001_service;

    @Autowired
    private FRA_PPG_001_DATA_Repository fra_ppg_001_data_repository;

    @Autowired
    private FRA_07_PPG_Print fra_07_ppg_print;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_PPG_001> getAll() {
        return fra_ppg_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_PPG_001 get(@PathVariable("id") Long id) {
        FRA_PPG_001 fra_ppg_001 = fra_ppg_001_service.findById(id);

        if (fra_ppg_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_ppg_001;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/idInterno/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_PPG_001 getInterno(@PathVariable("id") String id) {
        FRA_PPG_001 fra_ppg_001 = fra_ppg_001_service.findByIdInternoMuestra(id);

        if (fra_ppg_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_ppg_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestPart("frappg") Map<String, String> request,
                                    @RequestPart("signature") MultipartFile signature,
                                    Principal principal) {

        FRA_PPG_001 fra_ppg_001 = new FRA_PPG_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        SaveInServer saveInServer = new SaveInServer();

        try {
            fra_ppg_001.setFolioTecnica(request.get("folioTecnica"));
            fra_ppg_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
            fra_ppg_001.setIdInternoMuestra(request.get("idInternoMuestra"));
            fra_ppg_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_ppg_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_ppg_001.setTemperatura(request.get("temperatura"));
            fra_ppg_001.setHumedadRelativa(request.get("humedadRelativa"));

            fra_ppg_001.setCodigoBalanzaAnalitica(request.get("codigoBalanzaAnalitica"));
            fra_ppg_001.setTipoMaterial(request.get("tipoMaterial"));

            fra_ppg_001.setObservaciones(request.get("observaciones"));
            fra_ppg_001.setRealizo(request.get("realizo"));
            fra_ppg_001.setSupervisor(request.get("supervisor"));

            fra_ppg_001.setMetodoMuestra(metodoMuestra);

            fra_ppg_001_service.save(fra_ppg_001);

            Double promedioPeso = 0.0, promedioPellet = 0.0, promedioPG = 0.0;

            for (int i = 1; i < 6; i++) {
                FRA_PPG_001_DATA fra_ppg_001_data = new FRA_PPG_001_DATA();

                fra_ppg_001_data.setPeso(request.get("peso" + i));
                fra_ppg_001_data.setNumeroPellets(request.get("numeroPellets" + i));
                fra_ppg_001_data.setPg(String.format("%.1f", ((Double.parseDouble(request.get("peso" + i))) * (Double.parseDouble(request.get("numeroPellets" + i))))));
                fra_ppg_001_data.setFra_ppg_001(fra_ppg_001);

                fra_ppg_001_data_repository.save(fra_ppg_001_data);

                promedioPeso = promedioPeso + Double.parseDouble(fra_ppg_001_data.getPeso());
                promedioPellet = promedioPellet + Double.parseDouble(fra_ppg_001_data.getNumeroPellets());
                promedioPG = promedioPG + Double.parseDouble(fra_ppg_001_data.getPg());
            }

            fra_ppg_001.setPromedioPeso(String.format("%.4f", (promedioPeso / 5)));
            fra_ppg_001.setPromedioPellet((promedioPellet / 5) + "");
            fra_ppg_001.setPromedioPG(String.format("%.1f", (promedioPG / 5)));

            fra_ppg_001.setRubricaRealizo(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_REALIZO_PPG + saveInServer.SaveInServer(signature, Constantes.SIGNATURE_REALIZO_PPG));

            fra_ppg_001.setEstatus("FINALIZADO");
            fra_ppg_001.setCantidadModificaciones("3");

            fra_ppg_001_service.save(fra_ppg_001);

            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_PPG a las: " + calendario.getTime());

        return fra_07_ppg_print.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_PPG a las: " + calendario.getTime());

        return fra_07_ppg_print.crearFormato(id, 2);
    }
}
