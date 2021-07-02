package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra11eat.datas.FRA_EAT_001_DATA;
import com.demo.model.operacion.metodos.fra12eauv.FRA_EAUV_001;
import com.demo.model.operacion.metodos.fra12eauv.datas.FRA_EAUV_001_DATA;
import com.demo.service.formatos.metodos.FRA_12_EAUV_Print;
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
@RequestMapping(path = "/FRAEAUV")
public class FRA_12_EAUV_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_EAUV_001_Service fra_eauv_001_service;

    @Autowired
    private FRA_EAUV_001_DATA_Service fra_eauv_001_data_service;

    @Autowired
    private FRA_12_EAUV_Print fra_12_eauv_print;

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

    //ListarUnElementoDATAS
    @RequestMapping(method = RequestMethod.GET, value = "/individual/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_EAUV_001_DATA getIndividual(@PathVariable("id") Long id) {
        FRA_EAUV_001_DATA fra_eauv_001_data = fra_eauv_001_data_service.findById(id);

        if (fra_eauv_001_data == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_eauv_001_data;
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
        fra_eauv_001.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_eauv_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_eauv_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
        fra_eauv_001.setTemperatura(request.get("temperatura"));
        fra_eauv_001.setHumedadRelativa(request.get("humedadRelativa"));
        fra_eauv_001.setCodigoCamaraUV(request.get("codigoCamaraUV"));
        fra_eauv_001.setCodigoRadiometro(request.get("codigoRadiometro"));
        fra_eauv_001.setIrradiacion(request.get("irradiacion"));
        fra_eauv_001.setTemperaturaPanel(request.get("temperaturaPanel"));
        fra_eauv_001.setLongitudOnda(request.get("longitudOnda"));
        fra_eauv_001.setTiempoCapturasFotograficas(request.get("tiempoCapturasFotograficas"));
        fra_eauv_001.setCicloRadiacionUV(request.get("cicloRadiacionUV"));
        fra_eauv_001.setCicloAusenciaRadiacionUV(request.get("cicloAusenciaRadiacionUV"));
        fra_eauv_001.setDescripcionMuestra(request.get("descripcionMuestra"));
        fra_eauv_001.setTipoProducto(request.get("tipoProducto"));
        fra_eauv_001.setTipoMaterial(request.get("tipoMaterial"));
        fra_eauv_001.setCaraAnalisis(request.get("caraAnalisis"));
        fra_eauv_001.setAditivoBiodegradable(request.get("aditivoBiodegradable"));
        fra_eauv_001.setPorcentajeInclusion(request.get("porcentajeInclusion"));
        fra_eauv_001.setTipoSuperficie(request.get("tipoSuperficie"));
        fra_eauv_001.setEspecifique(request.get("especifique"));


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
                                    @RequestPart("signature") MultipartFile file2,
                                    @RequestPart("datos") Map<String, String> request,
                                    Principal principal) {

        FRA_EAUV_001 fra_eauv_001 = fra_eauv_001_service.findById(Long.parseLong(request.get("id")));
        FRA_EAUV_001_DATA fra_eauv_001_data = new FRA_EAUV_001_DATA();
        //Timestamp ts = new Timestamp()

        SaveInServer saveInServer = new SaveInServer();
        try {
            fra_eauv_001_data.setFra_eauv_001(fra_eauv_001);
            fra_eauv_001_data.setImagenSeleccionada(request.get("imagenSeleccionada"));
            fra_eauv_001_data.setTiempoExposicion(request.get("tiempoExposicion"));
            fra_eauv_001_data.setNombreAnalista(request.get("nombreAnalista"));
            fra_eauv_001_data.setImagenSeleccionada(request.get("imagenSeleccionada"));
            fra_eauv_001_data.setRubrica(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_EAUV_TIME + saveInServer.SaveInServer(file2, Constantes.SIGNATURE_EAUV_TIME));
            fra_eauv_001_data.setPathImage(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.RUTA_IMG_12_EAUV + saveInServer.SaveInServer(file, Constantes.RUTA_IMG_12_EAUV));

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
    public ResponseEntity<?> create2(@RequestPart("fraeauv") Map<String, String> request,
                                     @RequestPart("signature") MultipartFile signature,
                                     Principal principal) {
        APP.debug("Se finalizó FRA_EAUV a las: " + calendario.getTime());

        FRA_EAUV_001 fra_eauv_001 = fra_eauv_001_service.findById(Long.parseLong(request.get("id")));
        SaveInServer saveInServer = new SaveInServer();

        try {
            fra_eauv_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_eauv_001.setTiempoTotalExposicion(request.get("tiempoTotalExposicion"));
            fra_eauv_001.setObservaciones(request.get("observaciones"));
            fra_eauv_001.setRealizo(request.get("realizo"));
            fra_eauv_001.setSupervisor(request.get("supervisor"));

            fra_eauv_001.setRubricaRealizo(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_REALIZO_EAUV + saveInServer.SaveInServer(signature, Constantes.SIGNATURE_REALIZO_EAUV));

            fra_eauv_001.setEstatus("terminado");

            fra_eauv_001_service.save(fra_eauv_001);

            MetodoMuestra metodoMuestra = fra_eauv_001.getMetodoMuestra();
            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/imagenSi/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    public ResponseEntity<?> imagenSi(@PathVariable("id") Long id) throws Exception {

        FRA_EAUV_001_DATA fra_eauv_001_data = fra_eauv_001_data_service.findById(id);
        fra_eauv_001_data.setImagenSeleccionada("Si");

        fra_eauv_001_data_service.save(fra_eauv_001_data);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imagenNo/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    public ResponseEntity<?> imagenNo(@PathVariable("id") Long id) throws Exception {

        FRA_EAUV_001_DATA fra_eauv_001_data = fra_eauv_001_data_service.findById(id);
        fra_eauv_001_data.setImagenSeleccionada("No");

        fra_eauv_001_data_service.save(fra_eauv_001_data);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_EAUV a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_12_eauv_print.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_EAUV a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_12_eauv_print.crearFormato(id, 2);
    }
}
