package com.demo.controller.operacion;

import com.demo.model.Client;
import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.service.ClientService;
import com.demo.service.FoliosService;
import com.demo.service.MethodService;
import com.demo.service.operacion.SolicitudServicioClienteMuestrasService;
import com.demo.service.operacion.SolicitudServicioClienteService;
import com.demo.utils.EncryptedPasswordUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

                /*AppRole appRole = appRoleService.findById(Long.parseLong(request.get("rolUsuario")));
                UserRole userRole = userRoleRepository.findByAppUser_UserName(appUser.getUserName());

                userRole.setAppRole(appRole);
                userRole.setAppUser(appUser);

                userRoleRepository.save(userRole);

                mailService.modificacionUsuario(appUser.getUserName(), request.get("password"));*/
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }

        APP.debug("Apartado de modificación" + calendario.getTime());

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
            solicitudServicioClienteMuestras.setIdClienteMuestra(request.get("idClienteMuestra"));
            solicitudServicioClienteMuestras.setTipoMuestra(request.get("tipoMuestra"));
            solicitudServicioClienteMuestras.setDescripcionMuestra(request.get("descripcionMuestra"));
            solicitudServicioClienteMuestras.setCondicionesEspeciales(request.get("condicionesEspeciales"));
            solicitudServicioClienteMuestras.setObservaciones(request.get("observaciones"));
            solicitudServicioClienteMuestras.setMethod(methodService.findById(Long.parseLong(request.get("method"))));
            solicitudServicioClienteMuestras.setSolicitudServicioCliente(solicitudServicioCliente);
            solicitudServicioClienteMuestrasService.save(solicitudServicioClienteMuestras);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
