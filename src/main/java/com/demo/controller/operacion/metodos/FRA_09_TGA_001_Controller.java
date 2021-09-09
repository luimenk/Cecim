package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra07ppg.FRA_PPG_001;
import com.demo.model.operacion.metodos.fra09tga.FRA_TGA_001;
import com.demo.model.operacion.metodos.fra09tga.datas.FRA_TGA_001_DATA;
import com.demo.repository.operacion.metodos.fra09tga.datas.FRA_TGA_001_DATA_Repository;
import com.demo.service.formatos.metodos.FRA_09_TGA_Print;
import com.demo.service.formatos.metodos.listas.LFF_MIE_MET_XX_Print;
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
@RequestMapping(path = "/FRATGA")
public class FRA_09_TGA_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_TGA_001_Service fra_tga_001_service;

    @Autowired
    private FRA_TGA_001_DATA_Repository fra_tga_001_data_repository;

    @Autowired
    private FRA_09_TGA_Print fra_09_tga_print;

    @Autowired
    private LFF_MIE_MET_XX_Print lff_mie_met_xx_print;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_TGA_001> getAll() {
        return fra_tga_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_TGA_001 get(@PathVariable("id") Long id) {
        FRA_TGA_001 fra_tga_001 = fra_tga_001_service.findById(id);

        if (fra_tga_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_tga_001;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/idInterno/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_TGA_001 getInterno(@PathVariable("id") String id) {
        FRA_TGA_001 fra_tga_001 = fra_tga_001_service.findByIdInternoMuestra(id);

        if (fra_tga_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_tga_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestPart("fratga") Map<String, String> request,
                                    @RequestPart("imagen") MultipartFile file,
                                    @RequestPart("signature") MultipartFile signature,
                                    Principal principal) {

        FRA_TGA_001 fra_tga_001 = new FRA_TGA_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        SaveInServer saveInServer = new SaveInServer();

        try {
            fra_tga_001.setFolioTecnica(request.get("folioTecnica"));
            fra_tga_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
            fra_tga_001.setIdInternoMuestra(request.get("idInternoMuestra"));
            fra_tga_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_tga_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_tga_001.setTemperatura(request.get("temperatura"));
            fra_tga_001.setHumedadRelativa(request.get("humedadRelativa"));

            fra_tga_001.setCodigoTGA(request.get("codigoTGA"));
            fra_tga_001.setCodigoBalanza(request.get("codigoBalanza"));

            fra_tga_001.setGasPrueba(request.get("gasPrueba"));
            fra_tga_001.setGasProteccion(request.get("gasProteccion"));

            fra_tga_001.setPeso(request.get("peso"));
            fra_tga_001.setPosicionPortadorMuestra(request.get("posicionPortadorMuestra"));
            fra_tga_001.setTipoMaterial(request.get("tipoMaterial"));
            fra_tga_001.setPurga(request.get("purga"));
            fra_tga_001.setInicio(request.get("inicio"));
            fra_tga_001.setDinamicaCal1(request.get("dinamicaCal1"));
            fra_tga_001.setDinamicaCal2(request.get("dinamicaCal2"));
            fra_tga_001.setTasaCalentamiento(request.get("tasaCalentamiento"));
            fra_tga_001.setDinamicaEnf1(request.get("dinamicaEnf1"));
            fra_tga_001.setTasaEnfriamiento(request.get("tasaEnfriamiento"));
            fra_tga_001.setTemperaturaEmergencia(request.get("temperaturaEmergencia"));
            fra_tga_001.setTipoAtmosfera1(request.get("tipoAtmosfera1"));
            fra_tga_001.setTipoAtmosfera2(request.get("tipoAtmosfera2"));
            fra_tga_001.setTiempoPurga(request.get("tiempoPurga"));
            fra_tga_001.setDinamica(request.get("dinamica"));

            fra_tga_001.setObservaciones(request.get("observaciones"));
            fra_tga_001.setRealizo(request.get("realizo"));
            fra_tga_001.setSupervisor(request.get("supervisor"));

            fra_tga_001.setMetodoMuestra(metodoMuestra);

            fra_tga_001_service.save(fra_tga_001);

            FRA_TGA_001_DATA fra_tga_001_data = new FRA_TGA_001_DATA();

            fra_tga_001_data.setAvTemperatura(request.get("avTemperatura"));
            fra_tga_001_data.setAvPeso(request.get("avPeso"));
            fra_tga_001_data.setMvTemperatura(request.get("mvTemperatura"));
            fra_tga_001_data.setMvPeso(request.get("mvPeso"));
            fra_tga_001_data.setCombustibleTemperatura(request.get("combustibleTemperatura"));
            fra_tga_001_data.setCombustiblePeso(request.get("combustiblePeso"));
            fra_tga_001_data.setCenizasTemperatura(request.get("cenizasTemperatura"));
            fra_tga_001_data.setCenizasPeso(request.get("cenizasPeso"));
            fra_tga_001_data.setFra_tga_001(fra_tga_001);

            fra_tga_001_data_repository.save(fra_tga_001_data);

            fra_tga_001.setPathImagen(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.RUTA_IMG_09_TGA + saveInServer.SaveInServer(file, Constantes.RUTA_IMG_09_TGA));
            fra_tga_001.setRubricaRealizo(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_REALIZO_TGA + saveInServer.SaveInServer(signature, Constantes.SIGNATURE_REALIZO_TGA));

            fra_tga_001.setEstatus("FINALIZADO");
            fra_tga_001.setCantidadModificaciones("3");

            fra_tga_001_service.save(fra_tga_001);

            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_TGA a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_09_tga_print.crearFormato(id,1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_TGA a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_09_tga_print.crearFormato(id,2);
    }

    @RequestMapping(value = "/imprimir3", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir3() throws Exception {

        return lff_mie_met_xx_print.crearListaFolios("09-LFF-MIE-MET-TGA-001", 57L);
    }
}
