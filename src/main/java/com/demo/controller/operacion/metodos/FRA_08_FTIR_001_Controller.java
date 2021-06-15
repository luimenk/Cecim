package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra08ftir.FRA_FTIR_001;
import com.demo.model.operacion.metodos.fra08ftir.datas.FRA_FTIR_001_DATA;
import com.demo.repository.operacion.metodos.fra08ftir.datas.FRA_FTIR_001_DATA_Repository;
import com.demo.service.formatos.metodos.FRA_08_FTIR_Print;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.metodos.FRA_FTIR_001_Service;
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
@RequestMapping(path = "/FRAFTIR")
public class FRA_08_FTIR_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_FTIR_001_Service fra_ftir_001_service;

    @Autowired
    private FRA_FTIR_001_DATA_Repository fra_ftir_001_data_repository;

    @Autowired
    private FRA_08_FTIR_Print fra_08_ftir_print;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_FTIR_001> getAll() {
        return fra_ftir_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_FTIR_001 get(@PathVariable("id") Long id) {
        FRA_FTIR_001 fra_ftir_001 = fra_ftir_001_service.findById(id);

        if (fra_ftir_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_ftir_001;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/idInterno/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_FTIR_001 getInterno(@PathVariable("id") String id) {
        FRA_FTIR_001 fra_ftir_001 = fra_ftir_001_service.findByIdInternoMuestra(id);

        if (fra_ftir_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_ftir_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestPart("fraftir") Map<String, String> request,
                                    @RequestPart("imagen") MultipartFile file,
                                    @RequestPart("signature") MultipartFile signature,
                                    Principal principal) {

        FRA_FTIR_001 fra_ftir_001 = new FRA_FTIR_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        SaveInServer saveInServer = new SaveInServer();

        try {
            fra_ftir_001.setFolioTecnica(request.get("folioTecnica"));
            fra_ftir_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
            fra_ftir_001.setIdInternoMuestra(request.get("idInternoMuestra"));
            fra_ftir_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_ftir_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_ftir_001.setTemperatura(request.get("temperatura"));
            fra_ftir_001.setHumedadRelativa(request.get("humedadRelativa"));

            fra_ftir_001.setCodigoEspectrometro(request.get("codigoEspectrometro"));
            fra_ftir_001.setMetodoAnalisis(request.get("metodoAnalisis"));
            fra_ftir_001.setNumeroOnda(request.get("numeroOnda"));
            fra_ftir_001.setNumeroBarridos(request.get("numeroBarridos"));
            fra_ftir_001.setResolucion(request.get("resolucion"));

            fra_ftir_001.setObservaciones(request.get("observaciones"));
            fra_ftir_001.setRealizo(request.get("realizo"));
            fra_ftir_001.setSupervisor(request.get("supervisor"));

            fra_ftir_001.setMetodoMuestra(metodoMuestra);

            fra_ftir_001_service.save(fra_ftir_001);

            for (int i=1; i < 3; i++) {
                FRA_FTIR_001_DATA fra_ftir_001_data = new FRA_FTIR_001_DATA();

                fra_ftir_001_data.setTipoCompuesto(request.get("tipoCompuesto" + i));
                fra_ftir_001_data.setIdentidad(request.get("identidad" + i));
                fra_ftir_001_data.setFra_ftir_001(fra_ftir_001);

                fra_ftir_001_data_repository.save(fra_ftir_001_data);
            }

            fra_ftir_001.setTipoEspectro(request.get("tipoEspectro"));

            fra_ftir_001.setPathImagen(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.RUTA_IMG_08_FTIR + saveInServer.SaveInServer(file, Constantes.RUTA_IMG_08_FTIR));
            fra_ftir_001.setRubricaRealizo(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_REALIZO_FTIR + saveInServer.SaveInServer(signature, Constantes.SIGNATURE_REALIZO_FTIR));

            fra_ftir_001.setEstatus("FINALIZADO");
            fra_ftir_001.setCantidadModificaciones("3");

            fra_ftir_001_service.save(fra_ftir_001);

            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-FTIR-001");
        System.out.println(LocalTime.now());

        return fra_08_ftir_print.crearFormato(id,1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-FTIR-001");
        System.out.println(LocalTime.now());

        return fra_08_ftir_print.crearFormato(id,2);
    }
}
