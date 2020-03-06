package com.demo.controller;

//import com.pmisys.admin.api.service.TokenValidator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.demo.model.Machine;
import com.demo.service.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/machine")
public class MachineController {

    @Autowired
    private MachineService machineService;

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<Machine> getAll() {
        return machineService.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{machineId}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public Machine get(@PathVariable("machineId") Long machineId) {
        Machine machine = machineService.findById(machineId);

        if (machine == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (machine.getCodigoInterno() == null) {
            machine.getCodigoInterno();
        }

        return machine;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public Machine create(@RequestBody Map<String, String> request) throws Exception {
        Machine machine = new Machine();
        machine.setCodigoInterno(request.get("codigoInterno"));
        machine.setAreaResponsable(request.get("areaResponsable"));
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
        machine.setMantenimientoPeriodico(request.get("mantenimientoPeriodico"));


        machineService.save(machine);

        return machine;
    }

    //EliminarElemento
    @RequestMapping(method = RequestMethod.DELETE, value = "/{machineId}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE})
    public void delete(@PathVariable("machineId") Long machineId){
        machineService.delete(machineId);
    }
}
