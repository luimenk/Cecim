package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.FRA_ES_001;
import com.demo.service.ClientService;
import com.demo.service.FoliosService;
import com.demo.service.MethodService;
import com.demo.service.QR.QRService;
import com.demo.service.formatos.FEIM_SOC_005_Service;
import com.demo.service.formatos.FSS_SOC_001_Service;
import com.demo.service.formatos.metodos.IMPRIMIR_FRA_ES_001_Service;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.RecepcionVerificacionRegistroCodificacionService;
import com.demo.service.operacion.SolicitudServicioClienteMuestrasService;
import com.demo.service.operacion.SolicitudServicioClienteService;
import com.demo.service.operacion.metodos.FRA_ES_001_Service;
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
@RequestMapping(path = "/determinacionEspesor")
public class FRA_ES_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    Calendar calendario = new GregorianCalendar();

    @Autowired
    private SolicitudServicioClienteService solicitudServicioClienteService;

    @Autowired
    private SolicitudServicioClienteMuestrasService solicitudServicioClienteMuestrasService;

    @Autowired
    private FoliosService foliosService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private MethodService methodService;

    @Autowired
    private RecepcionVerificacionRegistroCodificacionService recepcionVerificacionRegistroCodificacionService;

    @Autowired
    private FSS_SOC_001_Service fss_soc_001_service;

    @Autowired
    private FEIM_SOC_005_Service feim_soc_005_service;

    @Autowired
    private QRService qrService;

    @Autowired
    private FRA_ES_001_Service fra_es_001_service;

    @Autowired
    private IMPRIMIR_FRA_ES_001_Service imprimir_fra_es_001_service;

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

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {
        APP.debug("Apartado de registro nuevo" + calendario.getTime());

        FRA_ES_001 fra_es_001 = new FRA_ES_001();

        fra_es_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_es_001.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_es_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_es_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));

        fra_es_001.setTemperatura(request.get("temperatura"));
        fra_es_001.setCodigoMicrometro(request.get("codigoMicrometro"));
        fra_es_001.setHumedadRelativa(request.get("humedadRelativa"));
        fra_es_001.setMetodo(request.get("metodo"));

        fra_es_001.setLargo1(request.get("largo1"));
        fra_es_001.setAncho1(request.get("ancho1"));

        fra_es_001.setLargo2(request.get("largo2"));
        fra_es_001.setAncho2(request.get("ancho2"));

        fra_es_001.setLargo3(request.get("largo3"));
        fra_es_001.setAncho3(request.get("ancho3"));

        fra_es_001.setLargo4(request.get("largo4"));
        fra_es_001.setAncho4(request.get("ancho4"));

        Double largo1 = Double.parseDouble(request.get("largo1"));
        Double largo2 = Double.parseDouble(request.get("largo2"));
        Double largo3 = Double.parseDouble(request.get("largo3"));
        Double largo4 = Double.parseDouble(request.get("largo4"));
        Double promedioLargo = (largo1 + largo2 + largo3 + largo4) / 4;

        System.out.println("Promedio largo " + promedioLargo);

        Double ancho1 = Double.parseDouble(request.get("ancho1"));
        Double ancho2 = Double.parseDouble(request.get("ancho2"));
        Double ancho3 = Double.parseDouble(request.get("ancho3"));
        Double ancho4 = Double.parseDouble(request.get("ancho4"));
        Double promedioAncho = (ancho1 + ancho2 + ancho3 + ancho4) / 4;

        Double [] valoresLargos = {largo1, largo2, largo3, largo4};
        Double [] valoresAnchos = {ancho1, ancho2, ancho3, ancho4};
        Double minLarg = valoresLargos[0];
        Double maxLarg = valoresLargos[0];
        Double minAnch = valoresAnchos[0];
        Double maxAnch = valoresAnchos[0];

        for (Double valoresLargo : valoresLargos) {
            if (maxLarg < valoresLargo) {
                maxLarg = valoresLargo;
            }
            if (minLarg > valoresLargo) {
                minLarg = valoresLargo;
            }
        }

        for (Double valoresAncho : valoresAnchos) {
            if (maxAnch < valoresAncho) {
                maxAnch = valoresAncho;
            }
            if (minAnch > valoresAncho) {
                minAnch = valoresAncho;
            }
        }

        Double desviacionEstandarLargo= Math.sqrt((Math.pow(largo1-promedioLargo,2) + Math.pow(largo2-promedioLargo,2) + Math.pow(largo3-promedioLargo,2) + Math.pow(largo4-promedioLargo,2)) / 4);
        Double desviacionEstandarAncho= Math.sqrt((Math.pow(ancho1-promedioAncho,2) + Math.pow(ancho2-promedioAncho,2) + Math.pow(ancho3-promedioAncho,2) + Math.pow(ancho4-promedioAncho,2)) / 4);

        fra_es_001.setPromedioLargo(String.valueOf(promedioLargo));
        fra_es_001.setPromedioAncho(String.valueOf(promedioAncho));
        fra_es_001.setDesvEstandarLargo(String.valueOf(desviacionEstandarLargo));
        fra_es_001.setDesvEstandarAncho(String.valueOf(desviacionEstandarAncho));
        fra_es_001.setMaxAncho(String.valueOf(maxAnch));
        fra_es_001.setMinAncho(String.valueOf(minAnch));
        fra_es_001.setMaxLargo(String.valueOf(maxLarg));
        fra_es_001.setMinLargo(String.valueOf(minLarg));

        fra_es_001.setObservaciones(request.get("observaciones"));
        fra_es_001.setRealizo(request.get("realizo"));
        fra_es_001.setSupervisor(request.get("supervisor"));
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        fra_es_001.setMetodoMuestra(metodoMuestra);

        fra_es_001_service.save(fra_es_001);

        metodoMuestra.setEstatus("OK");
        metodoMuestraService.save(metodoMuestra);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimirDeterminacionEspesores/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-DI-001");
        System.out.println(LocalTime.now());

        return imprimir_fra_es_001_service.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimirDeterminacionEspesores2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-DI-001");
        System.out.println(LocalTime.now());

        return imprimir_fra_es_001_service.crearFormato(id, 2);
    }
}
