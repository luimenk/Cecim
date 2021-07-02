package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra15dsc.FRA_DSC;
import com.demo.model.operacion.metodos.fra15dsc.datas.FRA_DSC_001_DATA;
import com.demo.repository.operacion.metodos.fra15dsc.datas.FRA_DSC_DATA_Repository;
import com.demo.service.formatos.metodos.FRA_15_DSC_Print;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.metodos.FRA_DSC_Service;
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
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping(path = "/FRADSC")
public class FRA_15_DSC_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_DSC_Service fra_dsc_service;

    @Autowired
    private FRA_DSC_DATA_Repository fra_dsc_data_repository;

    @Autowired
    private FRA_15_DSC_Print fra_15_dsc_print;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_DSC> getAll() {
        return fra_dsc_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_DSC get(@PathVariable("id") Long id) {
        FRA_DSC fra_dsc = fra_dsc_service.findById(id);

        if (fra_dsc == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_dsc;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/idInterno/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_DSC getInterno(@PathVariable("id") String id) {
        FRA_DSC fra_dsc = fra_dsc_service.findByIdInternoMuestra(id);

        if (fra_dsc == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_dsc;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestPart("fradsc") Map<String, String> request,
                                    @RequestPart("imagen") MultipartFile file,
                                    @RequestPart("signature") MultipartFile signature,
                                    Principal principal) {

        FRA_DSC fra_dsc = new FRA_DSC();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        SaveInServer saveInServer = new SaveInServer();

        try {
            fra_dsc.setFolioTecnica(request.get("folioTecnica"));
            fra_dsc.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
            fra_dsc.setIdInternoMuestra(request.get("idInternoMuestra"));
            fra_dsc.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_dsc.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_dsc.setTemperatura(request.get("temperatura"));
            fra_dsc.setHumedadRelativa(request.get("humedadRelativa"));

            fra_dsc.setCodigoDSC(request.get("codigoDSC"));
            fra_dsc.setCodigoBalanza(request.get("codigoBalanza"));
            fra_dsc.setPeso(request.get("peso"));
            fra_dsc.setPosicionPortadorMuestra(request.get("posicionPortadorMuestra"));
            fra_dsc.setInicio(request.get("inicio"));
            fra_dsc.setDinamicaCal1(request.get("dinamicaCal1"));
            fra_dsc.setTiempoIsotermico1(request.get("tiempoIsotermico1"));
            fra_dsc.setDinamicaEnf1(request.get("dinamicaEnf1"));
            fra_dsc.setTiempoIsotermico2(request.get("tiempoIsotermico2"));
            fra_dsc.setDinamicaCal2(request.get("dinamicaCal2"));
            fra_dsc.setTiempoIsotermico3(request.get("tiempoIsotermico3"));
            fra_dsc.setDinamicaEnf2(request.get("dinamicaEnf2"));
            fra_dsc.setTemperaturaEmergencia(request.get("temperaturaEmergencia"));
            fra_dsc.setTasaCalentamiento(request.get("tasaCalentamiento"));
            fra_dsc.setTasaEnfriamiento(request.get("tasaEnfriamiento"));

            fra_dsc.setObservaciones(request.get("observaciones"));
            fra_dsc.setRealizo(request.get("realizo"));
            fra_dsc.setSupervisor(request.get("supervisor"));

            fra_dsc.setMetodoMuestra(metodoMuestra);

            fra_dsc_service.save(fra_dsc);

            for (int i = 0; i < 2; i++) {
                FRA_DSC_001_DATA fra_dsc_001_data = new FRA_DSC_001_DATA();
                fra_dsc_001_data.setEtapa(request.get("etapa" + i));
                fra_dsc_001_data.setTemperaturaTransicion(request.get("temperaturaTransicion" + i));
                fra_dsc_001_data.setTemperaturaFusion(request.get("temperaturaFusion" + i));
                fra_dsc_001_data.setCalorFusion(request.get("calorFusion" + i));
                fra_dsc_001_data.setTransicionTermica(request.get("transicionTermica" + i));
                fra_dsc_001_data.setTemperaturaCristalizacion(request.get("temperaturaCristalizacion" + i));
                fra_dsc_001_data.setCalorCristalizacion(request.get("calorCristalizacion" + i));
                fra_dsc_001_data.setTransmisionTermica(request.get("transmisionTermica" + i));
                fra_dsc_001_data.setFra_dsc(fra_dsc);

                fra_dsc_data_repository.save(fra_dsc_001_data);
            }

            fra_dsc.setPathImagen(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.RUTA_IMG_15_DSC + saveInServer.SaveInServer(file, Constantes.RUTA_IMG_15_DSC));
            fra_dsc.setRubricaRealizo(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_REALIZO_DSC + saveInServer.SaveInServer(signature, Constantes.SIGNATURE_REALIZO_DSC));

            fra_dsc.setEstatus(request.get("FINALIZADO"));
            fra_dsc.setCantidadModificaciones(request.get("3"));

            fra_dsc_service.save(fra_dsc);

            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-DSC-001");
        System.out.println(LocalTime.now());

        return fra_15_dsc_print.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-DSC-001");
        System.out.println(LocalTime.now());

        return fra_15_dsc_print.crearFormato(id, 2);
    }

    @RequestMapping(value = "/imprimir3", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir3() throws Exception {
        System.out.println("Se generó BFF-MIE-016");
        System.out.println(LocalTime.now());

        return fra_15_dsc_print.crearListaFolios();
    }
}
