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
import com.demo.service.formatos.FEIM_SOC_005_Service;
import com.demo.service.formatos.FERI_MIE_002_Service;
import com.demo.service.formatos.FRM_SOC_005_Service;
import com.demo.service.formatos.FSS_SOC_001_Service;
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
@RequestMapping(path = "/solicitudServicioCliente")
public class SolicitudServicioClienteController {

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
    private FERI_MIE_002_Service feri_mie_002_service;

    @Autowired
    private QRService qrService;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<SolicitudServicioCliente> getAll() {
        return solicitudServicioClienteService.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public SolicitudServicioCliente get(@PathVariable("id") Long machineId) {
        SolicitudServicioCliente machine = solicitudServicioClienteService.findById(machineId);

        if (machine == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return machine;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Map<String, Object> request) throws Exception {
        SolicitudServicioCliente valida = solicitudServicioClienteService.findByIdFolio(request.get("folioSolitudServicioCliente")+"");
        if (valida != null){
            if (request.get("userId").equals("")){
                APP.debug("Intento de registro de correo existente" + calendario.getTime());
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                /*APP.debug("Apartado de modificación" + calendario.getTime());
                Client client = clientService.findById(Long.parseLong(request.get("empresa").toString()));
                SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteService.findById(Long.parseLong(request.get("solicitudServicioClienteId").toString()));
                solicitudServicioCliente.setFolioSolitudServicioCliente(foliosService.folioSolicitudServicioCliente());
                solicitudServicioCliente.setFechaEnvioMuestras(request.get("fechaEnvioMuestras").toString());
                solicitudServicioCliente.setFechaPago(request.get("fechaPago").toString());
                solicitudServicioCliente.setServicioUrgente(request.get("servicioUrgente").toString());
                solicitudServicioCliente.setNombreFirmaEmisor(request.get("nombreFirmaEmisor").toString());
                solicitudServicioCliente.setAlmacenamientoEspecial(request.get("almacenamientoEspecial").toString());
                solicitudServicioCliente.setEspecifique(request.get("especifique").toString());
                solicitudServicioCliente.setFechaRecepcionMuestras(request.get("fechaRecepcionMuestras").toString());
                solicitudServicioCliente.setFechaCompromisoEntrega(request.get("fechaCompromisoEntrega").toString());
                solicitudServicioCliente.setNombreFirmaReceptor(request.get("nombreFirmaReceptor").toString());
                solicitudServicioCliente.setNombreFirmaCalidad(request.get("nombreFirmaCalidad").toString());
                solicitudServicioCliente.setDevolucionMuestras(request.get("devolucionMuestras").toString());
                solicitudServicioCliente.setClient(client);
                solicitudServicioClienteService.save(solicitudServicioCliente);*/

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }

        APP.debug("Apartado de registro nuevo" + calendario.getTime());

        SolicitudServicioCliente solicitudServicioCliente = new SolicitudServicioCliente();
        Client client = clientService.findById(Long.parseLong(request.get("empresa").toString()));

        solicitudServicioCliente.setFolioSolitudServicioCliente(foliosService.folioSolicitudServicioCliente());
        solicitudServicioCliente.setFechaEnvioMuestras(request.get("fechaEnvioMuestras").toString());
        solicitudServicioCliente.setFechaPago(request.get("fechaPago").toString());
        solicitudServicioCliente.setServicioUrgente(request.get("servicioUrgente").toString());
        solicitudServicioCliente.setNombreFirmaEmisor(request.get("nombreFirmaEmisor").toString());
        solicitudServicioCliente.setAlmacenamientoEspecial(request.get("almacenamientoEspecial").toString());
        solicitudServicioCliente.setEspecifique(request.get("especifique").toString());
        solicitudServicioCliente.setFechaRecepcionMuestras(request.get("fechaRecepcionMuestras").toString());
        solicitudServicioCliente.setFechaCompromisoEntrega(request.get("fechaCompromisoEntrega").toString());
        solicitudServicioCliente.setNombreFirmaReceptor(request.get("nombreFirmaReceptor").toString());
        solicitudServicioCliente.setNombreFirmaCalidad(request.get("nombreFirmaCalidad").toString());
        solicitudServicioCliente.setDevolucionMuestras(request.get("devolucionMuestras").toString());
        solicitudServicioCliente.setClient(client);
        solicitudServicioClienteService.save(solicitudServicioCliente);

        for (int i=0;i<=Integer.parseInt(request.get("contMuestra").toString()); i++){
            SolicitudServicioClienteMuestras solicitudServicioClienteMuestras = new SolicitudServicioClienteMuestras();
            solicitudServicioClienteMuestras.setIdClienteMuestra(request.get("idClienteMuestra"+i).toString());
            solicitudServicioClienteMuestras.setTipoMuestra(request.get("tipoMuestra"+i).toString());
            solicitudServicioClienteMuestras.setDescripcionMuestra(request.get("descripcionMuestra"+i).toString());
            solicitudServicioClienteMuestras.setLote(request.get("lote"+i).toString());
            solicitudServicioClienteMuestras.setCondicionesEspeciales(request.get("condicionesEspeciales"+i).toString());
            solicitudServicioClienteMuestras.setObservaciones(request.get("observaciones"+i).toString());
            //solicitudServicioClienteMuestras.setMethod(methodService.findById(Long.parseLong(request.get("metodo"+i).toString())));
            solicitudServicioClienteMuestras.setSolicitudServicioCliente(solicitudServicioCliente);
            solicitudServicioClienteMuestras.setPathQRIdentificacion("");
            solicitudServicioClienteMuestras.setEstatus("NO");
            solicitudServicioClienteMuestrasService.save(solicitudServicioClienteMuestras);
            System.out.println(solicitudServicioClienteMuestras.getSolicitudServicioClienteMuestrasId());
            solicitudServicioClienteMuestras.setPathQRIdentificacion(qrService.generate(solicitudServicioClienteMuestras.getSolicitudServicioClienteMuestrasId()));
            solicitudServicioClienteMuestrasService.save(solicitudServicioClienteMuestras);

            List lista = (List) request.get("metodo"+i);

            for (int j = 0; j< lista.size(); j++){
                MetodoMuestra metodoMuestra = new MetodoMuestra();
                metodoMuestra.setPathQRLab("");
                metodoMuestra.setSolicitudServicioClienteMuestras(solicitudServicioClienteMuestras);
                metodoMuestra.setMethod(methodService.findById(Long.parseLong(lista.get(j).toString())));
                metodoMuestra.setEstatus("RECEPCION");
                metodoMuestraService.save(metodoMuestra);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimirSolicitud/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FSS-SOC-001");
        System.out.println(LocalTime.now());

        return fss_soc_001_service.crearFormato(id);
    }

    @RequestMapping(value = "/imprimirCotizacionContrato/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimirCotizacion(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FCO-SOC-003");
        System.out.println(LocalTime.now());

        return solicitudServicioClienteService.crearCotizacion(id);
    }

    @RequestMapping(value = "/imprimirEtiquetasIdentificacion/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FEIM-SOC-005");
        System.out.println(LocalTime.now());

        return feim_soc_005_service.crearFormato(id,1);
    }

    @RequestMapping(value = "/imprimirEtiquetasIdentificacionIndividual/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimirIdentificacion(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FEIM-SOC-005");
        System.out.println(LocalTime.now());

        return feim_soc_005_service.crearFormato(id,2);
    }

    @RequestMapping(value = "/imprimirEtiquetasRetencion/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir3(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FERI-MIE-002");
        System.out.println(LocalTime.now());

        return feri_mie_002_service.crearFormato(id);
    }

    @RequestMapping(value = "/imprimirInforme/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir4(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FIR-ERAI-002");
        System.out.println(LocalTime.now());

        return solicitudServicioClienteService.crearFormato(id);
    }

    @RequestMapping(value = "/generarListaCotizacion", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir5() throws Exception {
        System.out.println("Se generó LFC-SOC-002");
        System.out.println(LocalTime.now());

        return solicitudServicioClienteService.generarListaCotizacion();
    }

    @RequestMapping(value = "/generarListaSolicitud", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir6() throws Exception {
        System.out.println("Se generó LFS-SOC-001");
        System.out.println(LocalTime.now());

        return solicitudServicioClienteService.generarListaSolicitud();
    }

}
