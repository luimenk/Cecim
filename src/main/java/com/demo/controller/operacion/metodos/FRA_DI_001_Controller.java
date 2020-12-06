package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.FRA_DI_001;
import com.demo.service.formatos.metodos.IMPRIMIR_FRA_DI_001_Service;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.metodos.FRA_DI_001_Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping(path = "/determinacionDimension")
public class FRA_DI_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_DI_001_Service fra_di_001_service;

    @Autowired
    private IMPRIMIR_FRA_DI_001_Service imprimir_fra_di_001_service;

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

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {
        /*FRA_DI_001 valida = fra_di_001_service.findById(request.get("folioSolitudServicioCliente"));
        if (valida != null){
            if (request.get("userId") == ""){
                APP.debug("Intento de registro de correo existente" + calendario.getTime());
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                APP.debug("Apartado de modificación" + calendario.getTime());
                Client client = clientService.findById(Long.parseLong(request.get("empresa")));
                SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteService.findById(Long.parseLong(request.get("solicitudServicioClienteId")));
                solicitudServicioCliente.setFolioSolitudServicioCliente(foliosService.folioSolicitudServicioCliente());
                solicitudServicioCliente.setFechaEnvioMuestras(request.get("fechaEnvioMuestras"));
                solicitudServicioCliente.setFechaPago(request.get("fechaPago"));
                solicitudServicioCliente.setServicioUrgente(request.get("servicioUrgente"));
                solicitudServicioCliente.setNombreFirmaEmisor(request.get("nombreFirmaEmisor"));
                solicitudServicioCliente.setAlmacenamientoEspecial(request.get("almacenamientoEspecial"));
                solicitudServicioCliente.setEspecifique(request.get("especifique"));
                solicitudServicioCliente.setFechaRecepcionMuestras(request.get("fechaRecepcionMuestras"));
                solicitudServicioCliente.setFechaCompromisoEntrega(request.get("fechaCompromisoEntrega"));
                solicitudServicioCliente.setNombreFirmaReceptor(request.get("nombreFirmaReceptor"));
                solicitudServicioCliente.setNombreFirmaCalidad(request.get("nombreFirmaCalidad"));
                solicitudServicioCliente.setDevolucionMuestras(request.get("devolucionMuestras"));
                solicitudServicioCliente.setClient(client);
                solicitudServicioClienteService.save(solicitudServicioCliente);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }*/

        APP.debug("Apartado de registro nuevo" + calendario.getTime());

        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));

        FRA_DI_001 fra_di_001 = new FRA_DI_001();

        fra_di_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_di_001.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_di_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_di_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
        fra_di_001.setTemperatura(request.get("temperatura"));
        fra_di_001.setHumedadRelativa(request.get("humedadRelativa"));
        fra_di_001.setCodigoRegla(request.get("codigoRegla"));
        fra_di_001.setLargo1(request.get("largo1"));
        fra_di_001.setAncho1(request.get("ancho1"));
        fra_di_001.setFuelle11(request.get("fuelle11"));
        fra_di_001.setFuelle21(request.get("fuelle21"));

        fra_di_001.setLargo2(request.get("largo2"));
        fra_di_001.setAncho2(request.get("ancho2"));
        fra_di_001.setFuelle12(request.get("fuelle12"));
        fra_di_001.setFuelle22(request.get("fuelle22"));

        fra_di_001.setLargo3(request.get("largo3"));
        fra_di_001.setAncho3(request.get("ancho3"));
        fra_di_001.setFuelle13(request.get("fuelle13"));
        fra_di_001.setFuelle23(request.get("fuelle23"));

        fra_di_001.setLargo4(request.get("largo4"));
        fra_di_001.setAncho4(request.get("ancho4"));
        fra_di_001.setFuelle14(request.get("fuelle14"));
        fra_di_001.setFuelle24(request.get("fuelle24"));

        fra_di_001.setLargo5(request.get("largo5"));
        fra_di_001.setAncho5(request.get("ancho5"));
        fra_di_001.setFuelle15(request.get("fuelle15"));
        fra_di_001.setFuelle25(request.get("fuelle25"));

        Double largo1 = Double.parseDouble(request.get("largo1"));
        Double largo2 = Double.parseDouble(request.get("largo2"));
        Double largo3 = Double.parseDouble(request.get("largo3"));
        Double largo4 = Double.parseDouble(request.get("largo4"));
        Double largo5 = Double.parseDouble(request.get("largo5"));
        Double promedioLargo = (largo1 + largo2 + largo3 + largo4 + largo5) / 5;

        Double ancho1 = Double.parseDouble(request.get("ancho1"));
        Double ancho2 = Double.parseDouble(request.get("ancho2"));
        Double ancho3 = Double.parseDouble(request.get("ancho3"));
        Double ancho4 = Double.parseDouble(request.get("ancho4"));
        Double ancho5 = Double.parseDouble(request.get("ancho5"));
        Double promedioAncho = (ancho1 + ancho2 + ancho3 + ancho4 + ancho5) / 5;

        Double fuelle11 = Double.parseDouble(request.get("fuelle11"));
        Double fuelle12 = Double.parseDouble(request.get("fuelle12"));
        Double fuelle13 = Double.parseDouble(request.get("fuelle13"));
        Double fuelle14 = Double.parseDouble(request.get("fuelle14"));
        Double fuelle15 = Double.parseDouble(request.get("fuelle15"));
        Double promedioFuelle1 = (fuelle11 + fuelle12 + fuelle13 + fuelle14 + fuelle15) / 5;

        Double fuelle21 = Double.parseDouble(request.get("fuelle21"));
        Double fuelle22 = Double.parseDouble(request.get("fuelle22"));
        Double fuelle23 = Double.parseDouble(request.get("fuelle23"));
        Double fuelle24 = Double.parseDouble(request.get("fuelle24"));
        Double fuelle25 = Double.parseDouble(request.get("fuelle25"));
        Double promedioFuelle2 = (fuelle21 + fuelle22 + fuelle23 + fuelle24 + fuelle25) / 5;

        fra_di_001.setPromedioLargo(String.valueOf(promedioLargo));
        fra_di_001.setPromedioAncho(String.valueOf(promedioAncho));
        fra_di_001.setPromedioFuelle1(String.valueOf(promedioFuelle1));
        fra_di_001.setPromedioFuelle2(String.valueOf(promedioFuelle2));

        Double sumatoriaFuellePromedio = (promedioFuelle1 + promedioFuelle2) / 2;

        fra_di_001.setSumatoriaFuellePromedio(String.valueOf(sumatoriaFuellePromedio));

        fra_di_001.setObservaciones(request.get("observaciones"));
        fra_di_001.setRealizo(request.get("realizo"));
        fra_di_001.setSupervisor(request.get("supervisor"));

        fra_di_001.setMetodoMuestra(metodoMuestra);

        fra_di_001_service.save(fra_di_001);

        metodoMuestra.setEstatus("OK");
        metodoMuestraService.save(metodoMuestra);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimirDeterminacionDimensiones/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-DI-001");
        System.out.println(LocalTime.now());

        return imprimir_fra_di_001_service.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimirDeterminacionDimensiones2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-DI-001");
        System.out.println(LocalTime.now());

        return imprimir_fra_di_001_service.crearFormato(id, 2);
    }
}
