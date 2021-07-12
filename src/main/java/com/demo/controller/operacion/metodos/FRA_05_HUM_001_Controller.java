package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra05hum.datas.FRA_HUM_001_DATA_01;
import com.demo.model.operacion.metodos.fra05hum.datas.FRA_HUM_001_DATA_02;
import com.demo.model.operacion.metodos.fra05hum.FRA_HUM_001;
import com.demo.repository.operacion.metodos.fra05hum.datas.FRA_HUM_001_DATA_01_Repository;
import com.demo.repository.operacion.metodos.fra05hum.datas.FRA_HUM_001_DATA_02_Repository;
import com.demo.service.formatos.metodos.FRA_05_HUM_Print;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.metodos.*;
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
@RequestMapping(path = "/FRAHUM")
public class FRA_05_HUM_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_HUM_001_Service fra_hum_001_service;

    @Autowired
    private FRA_HUM_001_DATA_01_Repository fra_hum_001_data_01_repository;

    @Autowired
    private FRA_HUM_001_DATA_02_Repository fra_hum_001_data_02_repository;

    @Autowired
    FRA_05_HUM_Print fra_05_hum_print;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_HUM_001> getAll() {
        return fra_hum_001_service.findAll();
    }

    //ListarTodoPorData
    @RequestMapping(method = RequestMethod.GET, value = "/getAll1By/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_HUM_001_DATA_01> getAllBy1(@PathVariable("id") Long id) {
        return fra_hum_001_data_01_repository.buscarTodosPorEnsayo(id);
    }

    //ListarTodoPorData
    @RequestMapping(method = RequestMethod.GET, value = "/getAll2By/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_HUM_001_DATA_02> getAllBy2(@PathVariable("id") Long id) {
        return fra_hum_001_data_02_repository.buscarTodosPorEnsayo(id);
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_HUM_001 get(@PathVariable("id") Long id) {
        FRA_HUM_001 fra_hum_001 = fra_hum_001_service.findById(id);

        if (fra_hum_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_hum_001;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/idInterno/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_HUM_001 getInterno(@PathVariable("id") String id) {
        FRA_HUM_001 fra_hum_001 = fra_hum_001_service.findByIdInternoMuestra(id);

        if (fra_hum_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_hum_001;
    }

    //ListarUnElementoDATAS
    @RequestMapping(method = RequestMethod.GET, value = "/individual1/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_HUM_001_DATA_01 getIndividual(@PathVariable("id") Long id) {
        FRA_HUM_001_DATA_01 fra_hum_001_data_01 = fra_hum_001_data_01_repository.findByIdFRAHUMDATA01(id);

        if (fra_hum_001_data_01 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_hum_001_data_01;
    }

    //ListarUnElementoDATAS
    @RequestMapping(method = RequestMethod.GET, value = "/individual2/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_HUM_001_DATA_02 getIndividual2(@PathVariable("id") Long id) {
        FRA_HUM_001_DATA_02 fra_hum_001_data_02 = fra_hum_001_data_02_repository.findByIdFRAHUMDATA02(id);

        if (fra_hum_001_data_02 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_hum_001_data_02;
    }

    //ObtenerPorFolio
    @RequestMapping(method = RequestMethod.GET, value = "/busquedaFolio/{folio}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_HUM_001 getByFolio(@PathVariable("folio") String folio) {
        FRA_HUM_001 fra_hum_001 = fra_hum_001_service.findByFolio(folio);

        if (fra_hum_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_hum_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {

        FRA_HUM_001 fra_hum_001 = new FRA_HUM_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));

        fra_hum_001.setFolioTecnica(request.get("folioTecnica"));
        fra_hum_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_hum_001.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_hum_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_hum_001.setTemperatura(request.get("temperatura"));
        fra_hum_001.setHumedadRelativa(request.get("humedadRelativa"));

        fra_hum_001.setCodigoBalanza(request.get("codigoBalanza"));
        fra_hum_001.setCodigoHorno(request.get("codigoHorno"));
        fra_hum_001.setTemperaturaSecado(request.get("temperaturaSecado"));
        fra_hum_001.setTipoMaterial(request.get("tipoMaterial"));

        fra_hum_001.setMetodoMuestra(metodoMuestra);

        fra_hum_001.setEstatus("inicio");
        fra_hum_001.setCantidadModificaciones("3");

        fra_hum_001_service.save(fra_hum_001);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/iniciar/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    public ResponseEntity<?> iniciar(@PathVariable("id") Long id) throws Exception {
        APP.debug("Se inició FRA_HUM a las: " + calendario.getTime());

        FRA_HUM_001 fra_hum_001 = fra_hum_001_service.findById(id);
        fra_hum_001.setEstatus("progreso");

        fra_hum_001_service.save(fra_hum_001);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //GuardarElemento
    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> agrega(@RequestPart("frahum") Map<String, String> request,
                                    Principal principal) {

        FRA_HUM_001 fra_hum_001 = fra_hum_001_service.findById(Long.parseLong(request.get("id")));
        FRA_HUM_001_DATA_01 fra_hum_001_data_01 = new FRA_HUM_001_DATA_01();
        FRA_HUM_001_DATA_02 fra_hum_001_data_02 = new FRA_HUM_001_DATA_02();

        fra_hum_001_data_01.setPesoCharola(request.get("1pesoCharola1"));
        fra_hum_001_data_02.setPesoCharola(request.get("2pesoCharola1"));

        fra_hum_001_data_01.setPesoInicial(request.get("1pesoInicial1"));
        fra_hum_001_data_02.setPesoInicial(request.get("2pesoInicial1"));

        fra_hum_001_data_01.setTiempoReposo(request.get("1tiempoReposo1"));
        fra_hum_001_data_02.setTiempoReposo(request.get("2tiempoReposo1"));

        fra_hum_001_data_01.setPesoFinal(request.get("1pesoFinal1"));
        fra_hum_001_data_02.setPesoFinal(request.get("2pesoFinal1"));

        fra_hum_001_data_01.setPesoSeco(String.format("%.4f", (Double.parseDouble(fra_hum_001_data_01.getPesoFinal()) - Double.parseDouble(fra_hum_001_data_01.getPesoCharola()))));
        fra_hum_001_data_02.setPesoSeco(String.format("%.4f", (Double.parseDouble(fra_hum_001_data_02.getPesoFinal()) - Double.parseDouble(fra_hum_001_data_02.getPesoCharola()))));

        fra_hum_001_data_01.setPips(String.format("%.4f", (Double.parseDouble(fra_hum_001_data_01.getPesoInicial())) - (Double.parseDouble(fra_hum_001_data_01.getPesoSeco()))));
        fra_hum_001_data_02.setPips(String.format("%.4f", (Double.parseDouble(fra_hum_001_data_02.getPesoInicial())) - (Double.parseDouble(fra_hum_001_data_02.getPesoSeco()))));

        fra_hum_001_data_01.setPorcentajeHumedad(String.format("%.2f", (Double.parseDouble(fra_hum_001_data_01.getPips()) / Double.parseDouble(fra_hum_001_data_01.getPesoInicial()) * 100)));
        fra_hum_001_data_02.setPorcentajeHumedad(String.format("%.2f", (Double.parseDouble(fra_hum_001_data_02.getPips()) / Double.parseDouble(fra_hum_001_data_02.getPesoInicial()) * 100)));

        fra_hum_001_data_01.setFra_hum_001(fra_hum_001);
        fra_hum_001_data_02.setFra_hum_001(fra_hum_001);

        fra_hum_001_data_01_repository.save(fra_hum_001_data_01);
        fra_hum_001_data_02_repository.save(fra_hum_001_data_02);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //GuardarElemento
    @RequestMapping(value = "/terminar", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create2(@RequestPart("frahum") Map<String, String> request,
                                    @RequestPart("signature") MultipartFile signature,
                                    Principal principal) {
        APP.debug("Se finalizó FRA_HUM a las: " + calendario.getTime());

        FRA_HUM_001 fra_hum_001 = fra_hum_001_service.findById(Long.parseLong(request.get("id")));
        List<FRA_HUM_001_DATA_01> lista1 = fra_hum_001_data_01_repository.buscarTodosPorEnsayo(fra_hum_001.getIdFRAHUM());
        List<FRA_HUM_001_DATA_02> lista2 = fra_hum_001_data_02_repository.buscarTodosPorEnsayo(fra_hum_001.getIdFRAHUM());
        SaveInServer saveInServer = new SaveInServer();

        try {

            fra_hum_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_hum_001.setObservaciones(request.get("observaciones"));
            fra_hum_001.setRealizo(request.get("realizo"));
            fra_hum_001.setSupervisor(request.get("supervisor"));

            fra_hum_001.setRubricaRealizo(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_REALIZO_HUM + saveInServer.SaveInServer(signature, Constantes.SIGNATURE_REALIZO_HUM));

            List<Double> promedios = new ArrayList<>();
            for (int i = 0; i < lista1.size(); i++) {
                promedios.add((Double.parseDouble(lista1.get(i).getPorcentajeHumedad()) + Double.parseDouble(lista2.get(i).getPorcentajeHumedad())) / 2);
            }

            fra_hum_001.setHumedadPromedio0(String.format("%.2f", promedios.get(0)));
            fra_hum_001.setHumedadPromedio4(String.format("%.2f", promedios.get(1)));
            fra_hum_001.setHumedadPromedio8(String.format("%.2f", promedios.get(2)));

            fra_hum_001.setEstatus("terminado");

            fra_hum_001_service.save(fra_hum_001);

            MetodoMuestra metodoMuestra = fra_hum_001.getMetodoMuestra();
            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_HUM a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_05_hum_print.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_HUM a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_05_hum_print.crearFormato(id, 2);
    }
}
