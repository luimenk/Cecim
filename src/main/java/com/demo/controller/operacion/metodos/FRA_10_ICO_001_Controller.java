package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra05hum.FRA_HUM_001;
import com.demo.model.operacion.metodos.fra10ico.FRA_ICO_001;
import com.demo.model.operacion.metodos.fra10ico.datas.FRA_ICO_001_DATA;
import com.demo.model.operacion.metodos.fra11eat.FRA_EAT_001;
import com.demo.model.operacion.metodos.fra11eat.datas.FRA_EAT_001_DATA;
import com.demo.service.formatos.metodos.FRA_05_HUM_Print;
import com.demo.service.formatos.metodos.FRA_10_ICO_Print;
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
@RequestMapping(path = "/FRAICO")
public class FRA_10_ICO_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_ICO_001_Service fra_ico_001_service;

    @Autowired
    private FRA_ICO_001_DATA_Service fra_ico_001_data_service;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    @Autowired
    FRA_10_ICO_Print fra_10_ico_print;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_ICO_001> getAll() {
        return fra_ico_001_service.findAll();
    }

    //ListarTodoPorData
    @RequestMapping(method = RequestMethod.GET, value = "/getAllBy/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_ICO_001_DATA> getAllBy(@PathVariable("id") Long id) {
        return fra_ico_001_data_service.findRandPreguntas(id);
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_ICO_001 get(@PathVariable("id") Long id) {
        FRA_ICO_001 fra_ico_001 = fra_ico_001_service.findById(id);

        if (fra_ico_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_ico_001;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/idInterno/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_ICO_001 getInterno(@PathVariable("id") String id) {
        FRA_ICO_001 fra_ico_001 = fra_ico_001_service.findByIdInternoMuestra(id);

        if (fra_ico_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_ico_001;
    }

    //ListarUnElementoDATAS
    @RequestMapping(method = RequestMethod.GET, value = "/individual/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_ICO_001_DATA getIndividual(@PathVariable("id") Long id) {
        FRA_ICO_001_DATA fra_ico_001_data = fra_ico_001_data_service.findById(id);

        if (fra_ico_001_data == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_ico_001_data;
    }

    //ObtenerPorFolio
    @RequestMapping(method = RequestMethod.GET, value = "/busquedaFolio/{folio}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_ICO_001 getByFolio(@PathVariable("folio") String folio) {
        FRA_ICO_001 fra_ico_001 = fra_ico_001_service.findByFolio(folio);

        if (fra_ico_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_ico_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create1(@RequestBody Map<String, String> request) throws Exception {
        APP.debug("Registro FRA_ICO a las: " + calendario.getTime());
        FRA_ICO_001 fra_ico_001 = new FRA_ICO_001();

        fra_ico_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_ico_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_ico_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
        fra_ico_001.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_ico_001.setTemperatura(request.get("temperatura"));
        fra_ico_001.setHumedadRelativa(request.get("humedadRelativa"));

        fra_ico_001.setTipoMaterial(request.get("tipoMaterial"));
        fra_ico_001.setCaraAnalisis(request.get("caraAnalisis"));
        fra_ico_001.setTipoSuperficie(request.get("tipoSuperficie"));
        fra_ico_001.setEspecifiqueTipoSuperficie(request.get("especifiqueTipoSuperficie"));
        fra_ico_001.setTipoProducto(request.get("tipoProducto"));
        fra_ico_001.setGeometria(request.get("geometria"));
        fra_ico_001.setAditivoBiodegradable(request.get("aditivoBiodegradable"));
        fra_ico_001.setGradoAditivo(request.get("gradoAditivo"));
        fra_ico_001.setPorcentajeInclusión(request.get("porcentajeInclusión"));
        fra_ico_001.setTipoEnvejecimiento(request.get("tipoEnvejecimiento"));
        fra_ico_001.setTiempoEnvejecimiento(request.get("tiempoEnvejecimiento"));
        fra_ico_001.setCodigoMicrometro(request.get("codigoMicrometro"));
        fra_ico_001.setNormaReferencia(request.get("normaReferencia"));
        fra_ico_001.setCodigoEspectrometro(request.get("codigoEspectrometro"));
        fra_ico_001.setMetodoAnalisis(request.get("metodoAnalisis"));
        fra_ico_001.setNumeroBarridos(request.get("numeroBarridos"));
        fra_ico_001.setResolucion(request.get("resolucion"));
        fra_ico_001.setNumeroOnda(request.get("numeroOnda"));
        fra_ico_001.setLineaBase(request.get("lineaBase"));
        fra_ico_001.setGrupoCarbonillo(request.get("grupoCarbonillo"));
        fra_ico_001.setGrupoAlifatico(request.get("grupoAlifatico"));


        fra_ico_001.setEstatus("inicio");
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        fra_ico_001.setMetodoMuestra(metodoMuestra);

        fra_ico_001_service.save(fra_ico_001);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/iniciar/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    public ResponseEntity<?> iniciar(@PathVariable("id") Long id) throws Exception {
        APP.debug("Se inició FRA_ICO a las: " + calendario.getTime());

        FRA_ICO_001 fra_ico_001 = fra_ico_001_service.findById(id);
        fra_ico_001.setEstatus("progreso");

        fra_ico_001_service.save(fra_ico_001);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //GuardarElemento
    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> agrega(@RequestPart("datos") Map<String, String> request,
                                    Principal principal) {

        FRA_ICO_001 fra_ico_001 = fra_ico_001_service.findById(Long.parseLong(request.get("id")));
        FRA_ICO_001_DATA fra_ico_001_data = new FRA_ICO_001_DATA();

        fra_ico_001_data.setTiempoExposicion(request.get("tiempoExposicion"));
        fra_ico_001_data.setE1(request.get("E1"));
        fra_ico_001_data.setE2(request.get("E2"));
        fra_ico_001_data.setE3(request.get("E3"));

        fra_ico_001_data.setPromedioEspesor(String.format("%.4f", (
                Double.parseDouble(fra_ico_001_data.getE1()) +
                Double.parseDouble(fra_ico_001_data.getE2()) +
                Double.parseDouble(fra_ico_001_data.getE3())) / 3));

        fra_ico_001_data.setMedicion1(request.get("medicion1"));
        fra_ico_001_data.setMedicion2(request.get("medicion2"));

        fra_ico_001_data.setPromedioCarbonillo(String.format("%.4f", (
                Double.parseDouble(fra_ico_001_data.getMedicion1()) +
                Double.parseDouble(fra_ico_001_data.getMedicion2())) / 2));

        fra_ico_001_data.setFra_ico_001(fra_ico_001);

        fra_ico_001_data_service.save(fra_ico_001_data);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //GuardarElemento
    @RequestMapping(value = "/terminar", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create2(@RequestPart("fraico") Map<String, String> request,
                                     @RequestPart("signature") MultipartFile signature,
                                     Principal principal) {
        APP.debug("Se finalizó FRA_ICO a las: " + calendario.getTime());

        FRA_ICO_001 fra_ico_001 = fra_ico_001_service.findById(Long.parseLong(request.get("id")));
        SaveInServer saveInServer = new SaveInServer();

        try {
            fra_ico_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_ico_001.setObservaciones(request.get("observaciones"));
            fra_ico_001.setRealizo(request.get("realizo"));
            fra_ico_001.setSupervisor(request.get("supervisor"));

            fra_ico_001.setRubricaRealizo(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_REALIZO_ICO + saveInServer.SaveInServer(signature, Constantes.SIGNATURE_REALIZO_ICO));

            fra_ico_001.setEstatus("terminado");

            fra_ico_001_service.save(fra_ico_001);

            MetodoMuestra metodoMuestra = fra_ico_001.getMetodoMuestra();
            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_ICO a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_10_ico_print.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_ICO a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_10_ico_print.crearFormato(id, 2);
    }
}
