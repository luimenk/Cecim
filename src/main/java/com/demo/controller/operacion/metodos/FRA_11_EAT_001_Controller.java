package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra11eat.FRA_EAT_001;
import com.demo.model.operacion.metodos.fra11eat.datas.FRA_EAT_001_DATA;
import com.demo.service.formatos.metodos.FRA_11_EAT_Print;
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
@RequestMapping(path = "/FRAEAT")
public class FRA_11_EAT_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_EAT_001_Service fra_eat_001_service;

    @Autowired
    private FRA_EAT_001_DATA_Service fra_eat_001_data_service;

    @Autowired
    private FRA_11_EAT_Print fra_11_eat_print;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_EAT_001> getAll() {
        return fra_eat_001_service.findAll();
    }

    //ListarTodoPorData
    @RequestMapping(method = RequestMethod.GET, value = "/getAllBy/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_EAT_001_DATA> getAllBy(@PathVariable("id") Long id) {
        return fra_eat_001_data_service.findRandPreguntas(id);
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_EAT_001 get(@PathVariable("id") Long id) {
        FRA_EAT_001 fra_eat_001 = fra_eat_001_service.findById(id);

        if (fra_eat_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_eat_001;
    }

    //ListarUnElementoDATAS
    @RequestMapping(method = RequestMethod.GET, value = "/individual/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_EAT_001_DATA getIndividual(@PathVariable("id") Long id) {
        FRA_EAT_001_DATA fra_eat_001_data = fra_eat_001_data_service.findById(id);

        if (fra_eat_001_data == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_eat_001_data;
    }

    //ObtenerPorFolio
    @RequestMapping(method = RequestMethod.GET, value = "/busquedaFolio/{folio}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_EAT_001 getByFolio(@PathVariable("folio") String folio) {
        FRA_EAT_001 fra_eat_001 = fra_eat_001_service.findByFolio(folio);

        if (fra_eat_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_eat_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create1(@RequestBody Map<String, String> request) throws Exception {
        APP.debug("Registro FRA_EAT a las: " + calendario.getTime());
        FRA_EAT_001 fra_eat_001 = new FRA_EAT_001();

        fra_eat_001.setFolioTecnica(request.get("folioTecnica"));
        fra_eat_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_eat_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_eat_001.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_eat_001.setTemperatura(request.get("temperatura"));
        fra_eat_001.setHumedadRelativa(request.get("humedadRelativa"));

        fra_eat_001.setTemperaturaEnsayo(request.get("temperaturaEnsayo"));
        fra_eat_001.setCodigoHorno(request.get("codigoHorno"));

        fra_eat_001.setCicloTomaFotografica(request.get("cicloTomaFotografica"));
        fra_eat_001.setTipoMaterial(request.get("tipoMaterial"));
        fra_eat_001.setDescripcionCiclo(request.get("descripcionCiclo"));

        fra_eat_001.setCantidadModificaciones("3");
        fra_eat_001.setEstatus("inicio");

        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        fra_eat_001.setMetodoMuestra(metodoMuestra);

        fra_eat_001_service.save(fra_eat_001);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/iniciar/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    public ResponseEntity<?> iniciar(@PathVariable("id") Long id) throws Exception {
        APP.debug("Se inició FRA_EAT a las: " + calendario.getTime());

        FRA_EAT_001 fra_eat_001 = fra_eat_001_service.findById(id);
        fra_eat_001.setEstatus("progreso");

        fra_eat_001_service.save(fra_eat_001);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //GuardarElemento
    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> agrega(@RequestPart("imagen") MultipartFile file,
                                    @RequestPart("signature") MultipartFile file2,
                                    @RequestPart("datos") Map<String, String> request,
                                    //@RequestPart("tiempoExposicion") String tiempoExposicion,
                                    //@RequestBody Map<String, String> request,
                                    Principal principal) {

        FRA_EAT_001 fra_eat_001 = fra_eat_001_service.findById(Long.parseLong(request.get("id")));
        FRA_EAT_001_DATA fra_eat_001_data = new FRA_EAT_001_DATA();
        //Timestamp ts = new Timestamp()

        SaveInServer saveInServer = new SaveInServer();
        try {
            fra_eat_001_data.setFra_eat_001(fra_eat_001);
            fra_eat_001_data.setNombreAnalista(request.get("nombreAnalista"));
            fra_eat_001_data.setRubrica(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_EAT_TIME + saveInServer.SaveInServer(file2, Constantes.SIGNATURE_EAT_TIME));
            fra_eat_001_data.setPathImage(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.RUTA_IMG_11_EAT + saveInServer.SaveInServer(file, Constantes.RUTA_IMG_11_EAT));

            fra_eat_001_data_service.save(fra_eat_001_data);
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
        APP.debug("Se finalizó FRA_EAT a las: " + calendario.getTime());

        FRA_EAT_001 fra_eat_001 = fra_eat_001_service.findById(Long.parseLong(request.get("id")));

        fra_eat_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
        fra_eat_001.setTiempoTotalExposicion(request.get("tiempoTotalExposicion"));
        fra_eat_001.setObservaciones(request.get("observaciones"));
        fra_eat_001.setRealizo(request.get("realizo"));
        fra_eat_001.setSupervisor(request.get("supervisor"));

        fra_eat_001.setEstatus("terminado");

        fra_eat_001_service.save(fra_eat_001);

        MetodoMuestra metodoMuestra = fra_eat_001.getMetodoMuestra();
        metodoMuestra.setEstatus("OK");
        metodoMuestraService.save(metodoMuestra);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_EAT a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_11_eat_print.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_EAT a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_11_eat_print.crearFormato(id, 2);
    }
}
