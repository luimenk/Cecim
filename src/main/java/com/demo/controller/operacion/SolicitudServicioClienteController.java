package com.demo.controller.operacion;

import com.demo.model.Client;
import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.service.ClientService;
import com.demo.service.FoliosService;
import com.demo.service.MethodService;
import com.demo.service.formatos.FSS_SOC_001_Service;
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
    private FSS_SOC_001_Service fss_soc_001_service;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<SolicitudServicioCliente> getAll() {
        return solicitudServicioClienteService.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{machineId}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public SolicitudServicioCliente get(@PathVariable("machineId") Long machineId) {
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
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {
        SolicitudServicioCliente valida = solicitudServicioClienteService.findByIdFolio(request.get("folioSolitudServicioCliente"));
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
        }

        APP.debug("Apartado de registro nuevo" + calendario.getTime());

        SolicitudServicioCliente solicitudServicioCliente = new SolicitudServicioCliente();
        Client client = clientService.findById(Long.parseLong(request.get("empresa")));

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

        for (int i=0;i<=Integer.parseInt(request.get("contMuestra")); i++){
            SolicitudServicioClienteMuestras solicitudServicioClienteMuestras = new SolicitudServicioClienteMuestras();
            solicitudServicioClienteMuestras.setIdClienteMuestra(request.get("idClienteMuestra"+i));
            solicitudServicioClienteMuestras.setTipoMuestra(request.get("tipoMuestra"+i));
            solicitudServicioClienteMuestras.setDescripcionMuestra(request.get("descripcionMuestra"+i));
            solicitudServicioClienteMuestras.setCondicionesEspeciales(request.get("condicionesEspeciales"+i));
            solicitudServicioClienteMuestras.setObservaciones(request.get("observaciones"+i));
            /*for (int j = 0; i<Integer.parseInt(request.get("numeroMetodo")); i++){*/
                //solicitudServicioClienteMuestras.setMethod(methodService.findById(Long.parseLong(request.get("metodo"+i))));
                //variable bandera controladora de número de métodos funcion numero de muestras metodo -> muestra
            //}
            solicitudServicioClienteMuestras.setMethod(methodService.findById(Long.parseLong(request.get("metodo"+i))));
            solicitudServicioClienteMuestras.setSolicitudServicioCliente(solicitudServicioCliente);
            solicitudServicioClienteMuestrasService.save(solicitudServicioClienteMuestras);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimirSolicitud/{id}", method = RequestMethod.GET)
    //public ResponseEntity<InputStreamResource> reporteEncuesta(@RequestParam String param) throws IOException {
    public ResponseEntity<InputStreamResource> reporteCliente(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FCC-SOC");
        System.out.println(LocalTime.now());

        return fss_soc_001_service.crearFormato(id);
    }
}
