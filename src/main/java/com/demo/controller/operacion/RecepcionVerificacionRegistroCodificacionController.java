package com.demo.controller.operacion;

import com.demo.model.Client;
import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;
import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.service.ClientService;
import com.demo.service.FoliosService;
import com.demo.service.MethodService;
import com.demo.service.QR.QRService;
import com.demo.service.formatos.*;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.RecepcionVerificacionRegistroCodificacionService;
import com.demo.service.operacion.SolicitudServicioClienteMuestrasService;
import com.demo.service.operacion.SolicitudServicioClienteService;
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
@RequestMapping(path = "/recepcionVerificacion")
public class RecepcionVerificacionRegistroCodificacionController {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    Calendar calendario = new GregorianCalendar();

    @Autowired
    private SolicitudServicioClienteService solicitudServicioClienteService;

    @Autowired
    private SolicitudServicioClienteMuestrasService solicitudServicioClienteMuestrasService;

    @Autowired
    private RecepcionVerificacionRegistroCodificacionService recepcionVerificacionRegistroCodificacionService;

    @Autowired
    private FoliosService foliosService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private MethodService methodService;

    @Autowired
    private FSS_SOC_001_Service fss_soc_001_service;

    @Autowired
    private FEIM_SOC_005_Service feim_soc_005_service;

    @Autowired
    private BRM_MIE_001_Service brm_mie_001_service;

    @Autowired
    private BRMR_MIE_001_Service brmr_mie_001_service;

    @Autowired
    private FRM_SOC_005_Service frm_soc_005_service;

    @Autowired
    private FEIL_MIE_007_Service feil_mie_007_service;
    @Autowired
    private MetodoMuestraService metodoMuestraService;

    @Autowired
    private QRService qrService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<RecepcionVerificacionRegistroCodificacion> getAll() {
        return recepcionVerificacionRegistroCodificacionService.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public RecepcionVerificacionRegistroCodificacion get(@PathVariable("id") Long id) {
        RecepcionVerificacionRegistroCodificacion recepcionVerificacionRegistroCodificacion = recepcionVerificacionRegistroCodificacionService.findById(id);

        if (recepcionVerificacionRegistroCodificacion == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return recepcionVerificacionRegistroCodificacion;
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/muestra/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public RecepcionVerificacionRegistroCodificacion getUnoPorMuestra(@PathVariable("id") Long id) {
        RecepcionVerificacionRegistroCodificacion recepcionVerificacionRegistroCodificacion = recepcionVerificacionRegistroCodificacionService.findBySolicitudServicioClienteMuestrasId(id);

        if (recepcionVerificacionRegistroCodificacion == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return recepcionVerificacionRegistroCodificacion;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {
        /*SolicitudServicioCliente valida = solicitudServicioClienteService.findByIdFolio(request.get("folioSolitudServicioCliente"));
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

        String ids = foliosService.IdConsecutivos();

        RecepcionVerificacionRegistroCodificacion recepcionVerificacionRegistroCodificacion = new RecepcionVerificacionRegistroCodificacion();
        SolicitudServicioClienteMuestras solicitudServicioClienteMuestras = solicitudServicioClienteMuestrasService.findById(Long.parseLong(request.get("idMuestra")));

        recepcionVerificacionRegistroCodificacion.setCuentaConEtiqueta(request.get("cuentaConEtiqueta"));
        recepcionVerificacionRegistroCodificacion.setUtilizoFeim(request.get("utilizoFeim"));
        recepcionVerificacionRegistroCodificacion.setCantidadMuestraEntregada(request.get("cantidadMuestraEntregada"));
        recepcionVerificacionRegistroCodificacion.setSolicitudServicioClienteMuestras(solicitudServicioClienteMuestras);
        recepcionVerificacionRegistroCodificacion.setFechaRecepcion(request.get("fechaRecepcion"));
        recepcionVerificacionRegistroCodificacion.setFolioRecepcionVerificacion(foliosService.folioRecepcionValidacion());
        recepcionVerificacionRegistroCodificacion.setNombrePersonaRecibe(request.get("nombrePersonaRecibe"));
        recepcionVerificacionRegistroCodificacion.setNombrePersonaEntrega(request.get("nombrePersonaEntrega"));
        recepcionVerificacionRegistroCodificacion.setMedioRecepcion(request.get("medioRecepcion"));
        recepcionVerificacionRegistroCodificacion.setIdInternoMuestra1("L-" + ids);
        recepcionVerificacionRegistroCodificacion.setIdInternoMuestra2("R-" + ids);
        recepcionVerificacionRegistroCodificacion.setCondicionesMuestra1(request.get("condicionesMuestra1"));
        recepcionVerificacionRegistroCodificacion.setCondicionesMuestra2(request.get("condicionesMuestra2"));
        recepcionVerificacionRegistroCodificacion.setCumpleCantidad(request.get("cumpleCantidad"));
        recepcionVerificacionRegistroCodificacion.setSinoEspecifiqueCantidad(request.get("sinoEspecifiqueCantidad"));
        recepcionVerificacionRegistroCodificacion.setCantidadMuestraAnalisis(request.get("cantidadMuestraAnalisis"));
        recepcionVerificacionRegistroCodificacion.setCantidadMuestraRetencion(request.get("cantidadMuestraRetencion"));
        recepcionVerificacionRegistroCodificacion.setNombrePersonaAcondicionara(request.get("nombrePersonaAcondicionara"));
        recepcionVerificacionRegistroCodificacion.setUbicacionMuestraRetencion(request.get("ubicacionMuestraRetencion"));
        recepcionVerificacionRegistroCodificacionService.save(recepcionVerificacionRegistroCodificacion);
        solicitudServicioClienteMuestras.setEstatus("SI");
        solicitudServicioClienteMuestrasService.save(solicitudServicioClienteMuestras);

        List<MetodoMuestra> lista = metodoMuestraService.findAllByMuestra(recepcionVerificacionRegistroCodificacion.getSolicitudServicioClienteMuestras().getSolicitudServicioClienteMuestrasId());

        solicitudServicioClienteMuestras.setPathQRIdentificacion(qrService.generate(solicitudServicioClienteMuestras.getSolicitudServicioClienteMuestrasId()));

        for (int j = 0; j< lista.size(); j++){
            MetodoMuestra metodoMuestra = metodoMuestraService.findById(lista.get(j).getMetodoMuestraId());
            metodoMuestra.setPathQRLab(qrService.generateToLab(lista.get(j).getMetodoMuestraId()));
            metodoMuestra.setFolioTecnica(foliosService.folioTecnicas(lista.get(j).getMethod().getCodigoMetodo()));
            metodoMuestra.setEstatus("PENDIENTE");
            metodoMuestraService.save(metodoMuestra);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimirRegistroMuestras", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir3() throws Exception {
        System.out.println("Se generó BRM-MIE-001");
        System.out.println(LocalTime.now());

        return brm_mie_001_service.crearFormato();
    }

    @RequestMapping(value = "/imprimirRegistroRecepcion", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir4() throws Exception {
        System.out.println("Se generó BRMR-MIE-003");
        System.out.println(LocalTime.now());

        return brmr_mie_001_service.crearFormato();
    }

    @RequestMapping(value = "/imprimirRecepcion/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir3(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRM-SOC-005");
        System.out.println(LocalTime.now());

        //RecepcionVerificacionRegistroCodificacion recepcionVerificacionRegistroCodificacion = recepcionVerificacionRegistroCodificacionService.findById(id);

        return frm_soc_005_service.crearFormato(id);
    }

    @RequestMapping(value = "/imprimirRecepcionDirecta/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimirRecepcion(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRM-SOC-005");
        System.out.println(LocalTime.now());

        RecepcionVerificacionRegistroCodificacion recepcionVerificacionRegistroCodificacion = recepcionVerificacionRegistroCodificacionService.findBySolicitudServicioClienteMuestrasId(id);

        return frm_soc_005_service.crearFormato(recepcionVerificacionRegistroCodificacion.getRecepcionVerificacionRegistroCodificacionId());
    }

    @RequestMapping(value = "/imprimirEtiquetasLaboratorio/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir14(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FEIL-MIE-007");
        System.out.println(LocalTime.now());

        return feil_mie_007_service.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimirEtiquetasLaboratorioIndividual/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir15(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FEIL-MIE-007");
        System.out.println(LocalTime.now());

        return feil_mie_007_service.crearFormato(id, 2);
    }
}
