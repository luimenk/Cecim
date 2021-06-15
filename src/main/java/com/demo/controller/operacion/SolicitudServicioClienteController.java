package com.demo.controller.operacion;

import com.demo.model.Client;
import com.demo.model.Method;
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
import com.demo.utils.Constantes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
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
    public ResponseEntity<?> create(@RequestBody Map<String, Object> request) throws Exception, SQLException, SQLIntegrityConstraintViolationException {
        SolicitudServicioCliente valida = null;
        try {
            valida = solicitudServicioClienteService.findById(Long.parseLong(request.get("solicitudServicioClienteId").toString()));
        } catch (NullPointerException e) {
            System.out.println("En teoría como está vacío sería uno nuevo");
        }

        if (valida != null) {
            System.out.println("Apartado de modificación " + calendario.getTime());

            //SolicitudServicioCliente solicitudServicioCliente = new SolicitudServicioCliente();
            Client client = clientService.findById(Long.parseLong(request.get("empresa").toString()));

            //valida.setFolioSolitudServicioCliente(foliosService.folioSolicitudServicioCliente());
            //valida.setFechaEnvioMuestras(request.get("fechaEnvioMuestras").toString());
            //valida.setFechaPago(request.get("fechaPago").toString());
            valida.setServicioUrgente(request.get("servicioUrgente").toString());
            valida.setNombreFirmaEmisor("");
            valida.setAlmacenamientoEspecial(request.get("almacenamientoEspecial").toString());
            valida.setEspecifique(request.get("especifique").toString());
            //valida.setFechaRecepcionMuestras(request.get("fechaRecepcionMuestras").toString());
            //valida.setFechaCompromisoEntrega(request.get("fechaCompromisoEntrega").toString());
            valida.setNombreFirmaReceptor(request.get("nombreFirmaReceptor").toString());
            valida.setNombreFirmaCalidad(request.get("nombreFirmaCalidad").toString());
            valida.setDevolucionMuestras(request.get("devolucionMuestras").toString());
            valida.setClient(client);
            solicitudServicioClienteService.save(valida);

            List<SolicitudServicioClienteMuestras> solicitudServicioClienteMuestras = solicitudServicioClienteMuestrasService.findAllBySolicitud(valida.getSolicitudServicioClienteId());
            for (int i = 0; i < solicitudServicioClienteMuestras.size(); i++) {
                solicitudServicioClienteMuestras.get(i).setIdClienteMuestra(request.get("idClienteMuestra" + i).toString());
                solicitudServicioClienteMuestras.get(i).setTipoMuestra(request.get("tipoMuestra" + i).toString());
                solicitudServicioClienteMuestras.get(i).setDescripcionMuestra(request.get("descripcionMuestra" + i).toString());
                solicitudServicioClienteMuestras.get(i).setLote(request.get("lote" + i).toString());
                solicitudServicioClienteMuestras.get(i).setCondicionesEspeciales(request.get("condicionesEspeciales" + i).toString());
                solicitudServicioClienteMuestras.get(i).setObservaciones(request.get("observaciones" + i).toString());
                solicitudServicioClienteMuestras.get(i).setSolicitudServicioCliente(valida);
                solicitudServicioClienteMuestrasService.save(solicitudServicioClienteMuestras.get(i));

                //solicitudServicioClienteMuestras.setMethod(methodService.findById(Long.parseLong(request.get("metodo"+i).toString())));
                //solicitudServicioClienteMuestras.setPathQRIdentificacion("");
                //solicitudServicioClienteMuestras.setEstatus("NO");
                //solicitudServicioClienteMuestras.setPathQRIdentificacion(qrService.generate(solicitudServicioClienteMuestras.getSolicitudServicioClienteMuestrasId()));
                //solicitudServicioClienteMuestrasService.save(solicitudServicioClienteMuestras);

                List lista = (List) request.get("metodo" + i);

                metodoMuestraService.borraTodosPorMuestra(solicitudServicioClienteMuestras.get(i).getSolicitudServicioClienteMuestrasId());

                for (int j = 0; j < lista.size(); j++) {
                    MetodoMuestra metodoMuestra = new MetodoMuestra();
                    metodoMuestra.setPathQRLab("");
                    metodoMuestra.setFolioTecnica("");
                    metodoMuestra.setSolicitudServicioClienteMuestras(solicitudServicioClienteMuestras.get(i));
                    metodoMuestra.setMethod(methodService.findById(Long.parseLong(lista.get(j).toString())));
                    metodoMuestra.setEstatus("RECEPCION");
                    metodoMuestraService.save(metodoMuestra);
                }
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            System.out.println("Apartado de registro nuevo" + calendario.getTime());

            SolicitudServicioCliente solicitudServicioCliente = new SolicitudServicioCliente();
            Client client = clientService.findById(Long.parseLong(request.get("empresa").toString()));

            solicitudServicioCliente.setFolioSolitudServicioCliente(foliosService.folioSolicitudServicioCliente());
            solicitudServicioCliente.setFechaEnvioMuestras("");
//            solicitudServicioCliente.setFechaPago(request.get("fechaPago").toString());
            solicitudServicioCliente.setFechaPago("");
            solicitudServicioCliente.setServicioUrgente(request.get("servicioUrgente").toString());
            solicitudServicioCliente.setNombreFirmaEmisor("");
            solicitudServicioCliente.setAlmacenamientoEspecial(request.get("almacenamientoEspecial").toString());
            solicitudServicioCliente.setEspecifique(request.get("especifique").toString());
//            solicitudServicioCliente.setFechaRecepcionMuestras(request.get("fechaRecepcionMuestras").toString());
            solicitudServicioCliente.setFechaRecepcionMuestras("");
//            solicitudServicioCliente.setFechaCompromisoEntrega(request.get("fechaCompromisoEntrega").toString());
            solicitudServicioCliente.setFechaCompromisoEntrega("");
            solicitudServicioCliente.setFechaPago2("");
            solicitudServicioCliente.setConfirmacion("");
            solicitudServicioCliente.setConfirmacion2("");
            solicitudServicioCliente.setEstatusPago("Pendiente de Pago");
            solicitudServicioCliente.setNombreFirmaReceptor(request.get("nombreFirmaReceptor").toString());
            solicitudServicioCliente.setNombreFirmaCalidad(request.get("nombreFirmaCalidad").toString());
            solicitudServicioCliente.setDevolucionMuestras(request.get("devolucionMuestras").toString());
            solicitudServicioCliente.setTotalMuestras(request.get("contMuestra").toString());
            solicitudServicioCliente.setClient(client);
            solicitudServicioClienteService.save(solicitudServicioCliente);

            for (int i = 0; i < Integer.parseInt(request.get("contMuestra").toString()); i++) {
                SolicitudServicioClienteMuestras solicitudServicioClienteMuestras = new SolicitudServicioClienteMuestras();
                try {
                    solicitudServicioClienteMuestras.setIdClienteMuestra(request.get("idClienteMuestra" + i).toString());
                    solicitudServicioClienteMuestras.setTipoMuestra(request.get("tipoMuestra" + i).toString());
                    solicitudServicioClienteMuestras.setDescripcionMuestra(request.get("descripcionMuestra" + i).toString());
                    solicitudServicioClienteMuestras.setLote(request.get("lote" + i).toString());
                    solicitudServicioClienteMuestras.setCondicionesEspeciales(request.get("condicionesEspeciales" + i).toString());
                    solicitudServicioClienteMuestras.setObservaciones(request.get("observaciones" + i).toString());
                    solicitudServicioClienteMuestras.setSolicitudServicioCliente(solicitudServicioCliente);
                    solicitudServicioClienteMuestras.setPathQRIdentificacion("");
                    solicitudServicioClienteMuestras.setEstatus("NO");
                    List listaa = (List) request.get("metodo" + i);
                    solicitudServicioClienteMuestras.setTotalEnsayos(listaa.size() + "");
                    solicitudServicioClienteMuestrasService.save(solicitudServicioClienteMuestras);
                    solicitudServicioClienteMuestras.setPathQRIdentificacion(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.QR_FEIM + qrService.generate(solicitudServicioClienteMuestras.getSolicitudServicioClienteMuestrasId(), Constantes.QR_FEIM));
                    solicitudServicioClienteMuestrasService.save(solicitudServicioClienteMuestras);
                } catch (NullPointerException e) {
                    solicitudServicioClienteMuestras.setIdClienteMuestra("");
                    solicitudServicioClienteMuestras.setTipoMuestra("");
                    solicitudServicioClienteMuestras.setDescripcionMuestra("");
                    solicitudServicioClienteMuestras.setLote("");
                    solicitudServicioClienteMuestras.setCondicionesEspeciales("");
                    solicitudServicioClienteMuestras.setObservaciones("");
                    solicitudServicioClienteMuestras.setSolicitudServicioCliente(solicitudServicioCliente);
                    solicitudServicioClienteMuestras.setPathQRIdentificacion("");
                    solicitudServicioClienteMuestras.setEstatus("");
                    List listaa = (List) request.get("metodo" + i);
                    solicitudServicioClienteMuestras.setTotalEnsayos(listaa.size() + "");
                    solicitudServicioClienteMuestrasService.save(solicitudServicioClienteMuestras);
                    solicitudServicioClienteMuestras.setPathQRIdentificacion(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.QR_FEIM + qrService.generate(solicitudServicioClienteMuestras.getSolicitudServicioClienteMuestrasId(), Constantes.QR_FEIM));
                    solicitudServicioClienteMuestrasService.save(solicitudServicioClienteMuestras);
                }

                List lista = (List) request.get("metodo" + i);
                try {
                    for (int j = 0; j < lista.size(); j++) {
                        MetodoMuestra metodoMuestra = new MetodoMuestra();
                        metodoMuestra.setPathQRLab("");
                        metodoMuestra.setFolioTecnica("");
                        metodoMuestra.setSolicitudServicioClienteMuestras(solicitudServicioClienteMuestras);
                        metodoMuestra.setMethod(methodService.findById(Long.parseLong(lista.get(j).toString())));
                        metodoMuestra.setEstatus("RECEPCION");
                        metodoMuestraService.save(metodoMuestra);
                    }
                } catch (NullPointerException e) {
                    System.out.println("No tiene caso que guardes algo en metodo muestra");
                }
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    //ConfirmarFechaAnticipo
    @RequestMapping(method = RequestMethod.POST, value = "/confirmarFechas1")
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> fechaPagoAnticipo(@RequestBody Map<String, String> request) throws Exception, SQLException, SQLIntegrityConstraintViolationException {
        System.out.println("Apartado de confirmación de fechas" + calendario.getTime());
        SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteService.findById(Long.parseLong(request.get("idSolicitud")));
        solicitudServicioCliente.setFechaPago(request.get("fechaPago"));
        solicitudServicioCliente.setConfirmacion(request.get("confirmacion"));
        solicitudServicioCliente.setEstatusPago("Anticipo pagado");

        solicitudServicioClienteService.save(solicitudServicioCliente);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //ConfirmarFechaFinal
    @RequestMapping(method = RequestMethod.POST, value = "/confirmarFechas2")
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> fechaPagoFinal(@RequestBody Map<String, String> request) throws Exception, SQLException, SQLIntegrityConstraintViolationException {
        System.out.println("Apartado de confirmación de fechas final " + calendario.getTime());
        SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteService.findById(Long.parseLong(request.get("idSolicitud")));
        solicitudServicioCliente.setFechaPago2(request.get("fechaPago2"));
        solicitudServicioCliente.setConfirmacion2(request.get("confirmacion2"));
        solicitudServicioCliente.setEstatusPago("Pagado");

        solicitudServicioClienteService.save(solicitudServicioCliente);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //ConfirmarFechasRecepción
    @RequestMapping(method = RequestMethod.POST, value = "/confirmarFechas3")
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> fechaRecepcion(@RequestBody Map<String, String> request) throws Exception, SQLException, SQLIntegrityConstraintViolationException {
        System.out.println("Apartado de confirmación de fechas" + calendario.getTime());
        SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteService.findById(Long.parseLong(request.get("idSolicitud")));
        solicitudServicioCliente.setFechaRecepcionMuestras(request.get("fechaRecepcionMuestras"));

        solicitudServicioClienteService.save(solicitudServicioCliente);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //ConfirmarFechasCompromiso
    @RequestMapping(method = RequestMethod.POST, value = "/confirmarFechas4")
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> fechaPagoCompromiso(@RequestBody Map<String, String> request) throws Exception, SQLException, SQLIntegrityConstraintViolationException {
        System.out.println("Apartado de confirmación de fechas" + calendario.getTime());
        SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteService.findById(Long.parseLong(request.get("idSolicitud")));
        solicitudServicioCliente.setFechaCompromisoEntrega(request.get("fechaCompromisoEntrega"));

        solicitudServicioClienteService.save(solicitudServicioCliente);

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

        return feim_soc_005_service.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimirEtiquetasIdentificacionIndividual/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimirIdentificacion(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FEIM-SOC-005");
        System.out.println(LocalTime.now());

        return feim_soc_005_service.crearFormato(id, 2);
    }

    @RequestMapping(value = "/imprimirEtiquetasRetencion/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir3(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FERI-MIE-002");
        System.out.println(LocalTime.now());

        return feri_mie_002_service.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimirEtiquetasRetencionTodas/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir4(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FERI-MIE-002");
        System.out.println(LocalTime.now());

        return feri_mie_002_service.crearFormato(id, 3);
    }

    @RequestMapping(value = "/imprimirInforme/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir20(@PathVariable("id") Long id) throws Exception {
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

    @RequestMapping(value = "/generarListaSolicitud/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir6(@PathVariable("id") String filtro) throws Exception {
        System.out.println("Se generó LFS-SOC-001");
        System.out.println(LocalTime.now());

        return solicitudServicioClienteService.generarListaSolicitud(filtro);
    }

    @RequestMapping(value = "/imprimirSolicitudServicioInterno/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimirSolicitudServicioInterno(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FSI-SOC-006");
        System.out.println(LocalTime.now());

        return solicitudServicioClienteService.crearSolicitudServicioInterno(id);
    }

    //APARTADO PARA CREAR RESPALDO DE LA BASE DE DATOS
    //TODO: HACERLO DINÁMICO PARA QUE SE EJECUTE A CIERTA HORA DEL DÍA CON EL COMPONENTE O CONFIGURATION CORRESPONDIENTE
    @RequestMapping(value = "/extraerBD", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> extraer() throws Exception {
        System.out.println("BackUp realizado a las: " + new Date());

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String dbNameList = "demo"; //si se requieren varias bd se indican separadas por espacio
        String dbUserName = "desarrollo";
        String dbUserPassword = "Cuarenta40.";

        String executeCmd = "mysqldump -u"+dbUserName+" -p"+dbUserPassword+" " + dbNameList;

        Process runtimeProcess = null;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + new Date() + ".sql");
        MediaType word = MediaType.valueOf("application/sql");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(word)
                .body(new InputStreamResource(runtimeProcess.getInputStream()));

    }
}
