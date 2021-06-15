package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra13eaxe.FRA_EAXE_013;
import com.demo.model.operacion.metodos.fra13eaxe.datas.FRA_EAXE_013_DATA;
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
@RequestMapping(path = "/FRAEAXE")
public class FRA_EAXE_013_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_EAXE_013_Service fra_eaxe_013_service;

    @Autowired
    private FRA_EAXE_013_DATA_Service fra_eaxe_013_data_service;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_EAXE_013> getAll() {
        return fra_eaxe_013_service.findAll();
    }

    //ListarTodoPorData
    @RequestMapping(method = RequestMethod.GET, value = "/getAllBy/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_EAXE_013_DATA> getAllBy(@PathVariable("id") Long id) {
        return fra_eaxe_013_data_service.buscarPorEnsayo(id);
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_EAXE_013 get(@PathVariable("id") Long id) {
        FRA_EAXE_013 fra_eaxe_013 = fra_eaxe_013_service.findById(id);

        if (fra_eaxe_013 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_eaxe_013;
    }

    //ObtenerPorFolio
    @RequestMapping(method = RequestMethod.GET, value = "busquedaFolio/{folio}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_EAXE_013 getByFolio(@PathVariable("folio") String folio) {
        FRA_EAXE_013 fra_eaxe_013 = fra_eaxe_013_service.findByFolio(folio);
        if (fra_eaxe_013 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return fra_eaxe_013;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create1(@RequestBody Map<String, String> request) throws Exception {
        APP.debug("Registro FRA_EAXE a las: " + calendario.getTime());
        FRA_EAXE_013 fra_eaxe_013 = new FRA_EAXE_013();

        fra_eaxe_013.setFolioTecnica(request.get("folioTecnica"));
        fra_eaxe_013.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_eaxe_013.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_eaxe_013.setFechaFinalAnalisis("");
        fra_eaxe_013.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_eaxe_013.setTemperatura(request.get("temperatura"));
        fra_eaxe_013.setHumedadRelativa(request.get("humedadRelativa"));
        fra_eaxe_013.setCodigoCamaraXE(request.get("codigoCamaraXE"));
        fra_eaxe_013.setEstatus("inicio");
        fra_eaxe_013.setCantidadModificaciones("3");
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        fra_eaxe_013.setMetodoMuestra(metodoMuestra);

        fra_eaxe_013_service.save(fra_eaxe_013);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/iniciar/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    public ResponseEntity<?> iniciar(@PathVariable("id") Long id) throws Exception {
        APP.debug("Se inició FRA_EAXE a las: " + calendario.getTime());

        FRA_EAXE_013 fra_eaxe_013 = fra_eaxe_013_service.findById(id);
        fra_eaxe_013.setEstatus("progreso");

        fra_eaxe_013_service.save(fra_eaxe_013);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //GuardarElemento
    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> agrega(@RequestPart("imagen") MultipartFile file,
                                    @RequestPart("datos") Map<String, String> request,
                                    //@RequestPart("tiempoExposicion") String tiempoExposicion,
                                    //@RequestBody Map<String, String> request,
                                    Principal principal) {

        String filePath = "";

        if ("Linux".equals(System.getProperty("os.name"))){
            filePath = "/home/adpmx/java/laboratorio.cecim.com.mx/QR/";
        } else {
            filePath = System.getProperty("user.home") + "/";
        }

        FRA_EAXE_013 fra_eaxe_013 = fra_eaxe_013_service.findById(Long.parseLong(request.get("id")));
        FRA_EAXE_013_DATA fra_eaxe_013_data = new FRA_EAXE_013_DATA();
        //Timestamp ts = new Timestamp()

        SaveInServer saveInServer = new SaveInServer();
        try {
            fra_eaxe_013_data.setFra_eaxe_013(fra_eaxe_013);
            fra_eaxe_013_data.setTiempoExposicion(request.get("tiempoExposicion"));
            fra_eaxe_013_data.setPathImage(filePath+saveInServer.SaveInServer(file, filePath));

            fra_eaxe_013_data_service.save(fra_eaxe_013_data);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //GuardarElemento
    @RequestMapping(value = "/terminar", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create2(@RequestBody Map<String, String> request) throws Exception {
        APP.debug("Se finalizó FRA_EAXE a las: " + calendario.getTime());

        FRA_EAXE_013 fra_eaxe_013 = fra_eaxe_013_service.findById(Long.parseLong(request.get("id")));

        fra_eaxe_013.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
        fra_eaxe_013.setTiempoTotalExposicion(request.get("tiempoTotalExposicion"));
        fra_eaxe_013.setObservaciones(request.get("observaciones"));
        fra_eaxe_013.setRealizo(request.get("realizo"));
        fra_eaxe_013.setSupervisor(request.get("supervisor"));

        fra_eaxe_013.setEstatus("terminado");

        fra_eaxe_013_service.save(fra_eaxe_013);

        MetodoMuestra metodoMuestra = fra_eaxe_013.getMetodoMuestra();
        metodoMuestra.setEstatus("OK");
        metodoMuestraService.save(metodoMuestra);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_EAXE a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_eaxe_013_service.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_EAXE a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_eaxe_013_service.crearFormato(id, 2);
    }
}
