package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra04gr.FRA_GR_001;
import com.demo.model.operacion.metodos.fra04gr.datas.FRA_GR_001_DATA;
import com.demo.repository.operacion.metodos.fra04gr.datas.FRA_GR_001_DATA_Repository;
import com.demo.service.formatos.metodos.FRA_04_GR_Print;
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
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping(path = "/FRAGR")
public class FRA_04_GR_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_GR_001_Service fra_gr_001_service;

    @Autowired
    private FRA_GR_001_DATA_Repository fra_gr_001_data_repository;

    @Autowired
    private FRA_04_GR_Print fra_04_gr_print;

    @Autowired
    private LFF_MIE_MET_XX_Print lff_mie_met_xx_print;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_GR_001> getAll() {
        return fra_gr_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_GR_001 get(@PathVariable("id") Long id) {
        FRA_GR_001 fra_gr_001 = fra_gr_001_service.findById(id);

        if (fra_gr_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_gr_001;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/idInterno/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_GR_001 getInterno(@PathVariable("id") String id) {
        FRA_GR_001 fra_gr_001 = fra_gr_001_service.findByIdInternoMuestra(id);

        if (fra_gr_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_gr_001;
    }


    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestPart("fragr") Map<String, String> request,
                                    @RequestPart("signature") MultipartFile signature,
                                    Principal principal) {

        FRA_GR_001 fra_gr_001 = new FRA_GR_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        SaveInServer saveInServer = new SaveInServer();

        try {
            fra_gr_001.setFolioTecnica(request.get("folioTecnica"));
            fra_gr_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
            fra_gr_001.setIdInternoMuestra(request.get("idInternoMuestra"));
            fra_gr_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_gr_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_gr_001.setTemperatura(request.get("temperatura"));
            fra_gr_001.setHumedadRelativa(request.get("humedadRelativa"));

            fra_gr_001.setCodigoBalanzaAnalitica(request.get("codigoBalanzaAnalitica"));
            fra_gr_001.setAreaProbeta(request.get("areaProbeta"));

            fra_gr_001.setRubricaRealizo(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_REALIZO_GR + saveInServer.SaveInServer(signature, Constantes.SIGNATURE_REALIZO_GR));

            fra_gr_001.setObservaciones(request.get("observaciones"));
            fra_gr_001.setRealizo(request.get("realizo"));
            fra_gr_001.setSupervisor(request.get("supervisor"));

            fra_gr_001.setMetodoMuestra(metodoMuestra);

            fra_gr_001_service.save(fra_gr_001);

            Double pesoPromedio = 0.0;

            for (int i = 1; i < 6; i++) {
                FRA_GR_001_DATA fra_gr_001_data = new FRA_GR_001_DATA();
                fra_gr_001_data.setPeso(request.get("peso" + i));
                fra_gr_001_data.setFra_gr_001(fra_gr_001);
                fra_gr_001_data_repository.save(fra_gr_001_data);
                pesoPromedio = pesoPromedio + Double.parseDouble(request.get("peso" + i));
            }

            fra_gr_001.setPromedio(String.format("%.4f", (pesoPromedio / 5)));
            fra_gr_001.setGramaje(String.format("%.2f", ((pesoPromedio / 5) / 0.01)));

            fra_gr_001.setEstatus("FINALIZADO");
            fra_gr_001.setCantidadModificaciones("3");

            fra_gr_001_service.save(fra_gr_001);

            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-GR-001");
        System.out.println(LocalTime.now());

        return fra_04_gr_print.crearFormato(id,1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-GR-001");
        System.out.println(LocalTime.now());

        return fra_04_gr_print.crearFormato(id,2);
    }

    @RequestMapping(value = "/imprimir3", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir3() throws Exception {

        return lff_mie_met_xx_print.crearListaFolios("04-LFF-MIE-MET-GR-001", 34L);
    }
}
