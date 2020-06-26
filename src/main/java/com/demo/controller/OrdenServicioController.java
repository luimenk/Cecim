package com.demo.controller;

//import com.pmisys.admin.api.service.TokenValidator;

import com.demo.model.Client;
import com.demo.model.DocumentOrdenServicio;
import com.demo.model.Machine;
import com.demo.service.DocumentServiceOrdenServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/osc")
public class OrdenServicioController {

    @Autowired
    private DocumentServiceOrdenServicio documentServiceOrdenServicio;

    private static final Logger APP = LoggerFactory.getLogger(OrdenServicioController.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<DocumentOrdenServicio> getAll() {
        System.out.println("Entró");
        return documentServiceOrdenServicio.findAll();
    }

    //GuardarElemento
    /*@RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public DocumentOrdenServicio create(@RequestBody Map<String, String> request) throws Exception {
        *//*DocumentOrdenServicio machine = new DocumentOrdenServicio();
        machine.setFechaOrdenServicio(request.get("fechaOrdenServicio"));
        Client client = new Client();
        machine.setClient(Client() client = request.get("areaResponsable"));
        machine.setNombreEquipoInstrumento(request.get("nombreEquipoInstrumento"));
        machine.setTipoEquipo(request.get("tipoEquipo"));
        machine.setRegistro(request.get("registro"));
        machine.setTipoServicioRequerido(request.get("tipoServicioRequerido"));
        machine.setCondicionEquipo(request.get("condicionEquipo"));
        machine.setNumeroSerie(request.get("numeroSerie"));
        machine.setModelo(request.get("modelo"));
        machine.setMarca(request.get("marca"));
        machine.setGarantia(request.get("garantia"));
        machine.setFabricante(request.get("fabricante"));
        machine.setLargo(request.get("largo"));
        machine.setAncho(request.get("ancho"));
        machine.setAlto(request.get("alto"));
        machine.setPeso(request.get("peso"));
        machine.setZonaUbicacion(request.get("zonaUbicacion"));
        machine.setPlanoAnexo(request.get("planoAnexo"));
        machine.setProveedor(request.get("proveedor"));
        machine.setDireccionProveedor(request.get("direccionProveedor"));
        machine.setPersonaContacto(request.get("personaContacto"));
        machine.setTelefonoProveedor(request.get("telefonoProveedor"));
        machine.setCorreoProveedor(request.get("correoProveedor"));
        machine.setFechaAdquisicion(request.get("fechaAdquisicion"));
        machine.setFechaInstalacion(request.get("fechaInstalacion"));
        machine.setAgua(request.get("agua"));
        machine.setAireComprimido(request.get("aireComprimido"));
        machine.setGas(request.get("gas"));
        machine.setVoltajeAlimentacion(request.get("voltajeAlimentacion"));
        machine.setConsumoElectrico(request.get("consumoElectrico"));
        machine.setOtroEspecifique(request.get("otroEspecifique"));
        machine.setNombreVersion(request.get("nombreVersion"));
        machine.setAplicacion(request.get("aplicacion"));
        machine.setAccesorios(request.get("accesorios"));
        machine.setUsuarios(request.get("usuarios"));
        machine.setIdiomaManual(request.get("idiomaManual"));
        machine.setEntregaPlanos(request.get("entregaPlanos"));
        machine.setCertificadoCalibracion(request.get("certificadoCalibracion"));
        machine.setCertificadoMaterialesAccesorios(request.get("certificadoMaterialesAccesorios"));
        machine.setInstalacionCapacitacion(request.get("instalacionCapacitacion"));
        machine.setMantenimientoPeriodico(request.get("mantenimientoPeriodico"));*//*


        documentServiceOrdenServicio.save(machine);

        return machine;
    }*/
}
