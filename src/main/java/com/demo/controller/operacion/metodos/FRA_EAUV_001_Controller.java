package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra12eauv.FRA_EAUV_001;
import com.demo.model.operacion.metodos.fra12eauv.datas.FRA_EAUV_001_DATA;
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
@RequestMapping(path = "/FRAEAUV")
public class FRA_EAUV_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_EAUV_001_Service fra_eauv_001_service;

    @Autowired
    private FRA_EAUV_001_DATA_Service fra_eauv_001_data_service;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_EAUV_001> getAll() {
        return fra_eauv_001_service.findAll();
    }

    //ListarTodoPorData
    @RequestMapping(method = RequestMethod.GET, value = "/getAllBy/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_EAUV_001_DATA> getAllBy(@PathVariable("id") Long id) {
        return fra_eauv_001_data_service.buscarPorEnsayo(id);
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_EAUV_001 get(@PathVariable("id") Long id) {
        FRA_EAUV_001 fra_eauv_001 = fra_eauv_001_service.findById(id);

        if (fra_eauv_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_eauv_001;
    }

    //ObtenerPorFolio
    @RequestMapping(method = RequestMethod.GET, value = "busquedaFolio/{folio}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_EAUV_001 getByFolio(@PathVariable("folio") String folio) {
        FRA_EAUV_001 fra_eauv_001 = fra_eauv_001_service.findByFolio(folio);

        if (fra_eauv_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_eauv_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create1(@RequestBody Map<String, String> request) throws Exception {
        APP.debug("Registro FRA_EAUV a las: " + calendario.getTime());
        FRA_EAUV_001 fra_eauv_001 = new FRA_EAUV_001();

        fra_eauv_001.setFolioTecnica(request.get("folioTecnica"));
        fra_eauv_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_eauv_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_eauv_001.setFechaFinalAnalisis("");
        fra_eauv_001.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_eauv_001.setTemperatura(request.get("temperatura"));
        fra_eauv_001.setHumedadRelativa(request.get("humedadRelativa"));
        fra_eauv_001.setCodigoCamaraUV(request.get("codigoCamaraUV"));
        fra_eauv_001.setCodigoRadiometro(request.get("codigoRadiometro"));
        fra_eauv_001.setCantidadModificaciones("3");
        fra_eauv_001.setEstatus("inicio");
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        fra_eauv_001.setMetodoMuestra(metodoMuestra);

        fra_eauv_001_service.save(fra_eauv_001);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/iniciar/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    public ResponseEntity<?> iniciar(@PathVariable("id") Long id) throws Exception {
        APP.debug("Se inició FRA_EAUV a las: " + calendario.getTime());

        FRA_EAUV_001 fra_eauv_001 = fra_eauv_001_service.findById(id);
        fra_eauv_001.setEstatus("progreso");

        fra_eauv_001_service.save(fra_eauv_001);

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

        FRA_EAUV_001 fra_eauv_001 = fra_eauv_001_service.findById(Long.parseLong(request.get("id")));
        FRA_EAUV_001_DATA fra_eauv_001_data = new FRA_EAUV_001_DATA();
        //Timestamp ts = new Timestamp()

        SaveInServer saveInServer = new SaveInServer();
        try {
            fra_eauv_001_data.setFra_eauv_001(fra_eauv_001);
            fra_eauv_001_data.setTiempoExposicion(request.get("tiempoExposicion"));
            fra_eauv_001_data.setPathImage(filePath+saveInServer.SaveInServer(file, filePath));

            fra_eauv_001_data_service.save(fra_eauv_001_data);
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
        APP.debug("Se finalizó FRA_EAUV a las: " + calendario.getTime());

        FRA_EAUV_001 fra_eauv_001 = fra_eauv_001_service.findById(Long.parseLong(request.get("id")));

        fra_eauv_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
        fra_eauv_001.setTiempoTotalExposicion(request.get("tiempoTotalExposicion"));
        fra_eauv_001.setObservaciones(request.get("observaciones"));
        fra_eauv_001.setRealizo(request.get("realizo"));
        fra_eauv_001.setSupervisor(request.get("supervisor"));

        fra_eauv_001.setEstatus("terminado");

        fra_eauv_001_service.save(fra_eauv_001);

        MetodoMuestra metodoMuestra = fra_eauv_001.getMetodoMuestra();
        metodoMuestra.setEstatus("OK");
        metodoMuestraService.save(metodoMuestra);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_EAUV a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_eauv_001_service.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_EAUV a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_eauv_001_service.crearFormato(id, 2);
    }
}
