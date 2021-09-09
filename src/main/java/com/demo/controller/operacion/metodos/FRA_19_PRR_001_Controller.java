package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra19prr.FRA_PRR_001;
import com.demo.model.operacion.metodos.fra19prr.datas.FRA_PRR_001_DATA_01;
import com.demo.model.operacion.metodos.fra19prr.datas.FRA_PRR_001_DATA_02;
import com.demo.repository.operacion.metodos.fra19prr.datas.FRA_PRR_001_DATA_01_Repository;
import com.demo.repository.operacion.metodos.fra19prr.datas.FRA_PRR_001_DATA_02_Repository;
import com.demo.service.formatos.metodos.FRA_19_PRR_Print;
import com.demo.service.formatos.metodos.listas.LFF_MIE_MET_XX_Print;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.metodos.FRA_PRR_001_Service;
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
@RequestMapping(path = "/FRAPRR")
public class FRA_19_PRR_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_PRR_001_Service fra_prr_001_service;

    @Autowired
    private FRA_PRR_001_DATA_01_Repository fra_prr_001_data_01_repository;

    @Autowired
    private FRA_PRR_001_DATA_02_Repository fra_prr_001_data_02_repository;

    @Autowired
    private FRA_19_PRR_Print fra_19_prr_print;

    @Autowired
    private LFF_MIE_MET_XX_Print lff_mie_met_xx_print;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_PRR_001> getAll() {
        return fra_prr_001_service.findAll();
    }

    //ListarTodoPorData
    @RequestMapping(method = RequestMethod.GET, value = "/getAll1By/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_PRR_001_DATA_01> getAllBy1(@PathVariable("id") Long id) {
        return fra_prr_001_data_01_repository.buscarTodosPorEnsayo(id);
    }

    //ListarTodoPorData
    @RequestMapping(method = RequestMethod.GET, value = "/getAll2By/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_PRR_001_DATA_02> getAllBy2(@PathVariable("id") Long id) {
        return fra_prr_001_data_02_repository.buscarTodosPorEnsayo(id);
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_PRR_001 get(@PathVariable("id") Long id) {
        FRA_PRR_001 fra_prr_001 = fra_prr_001_service.findById(id);

        if (fra_prr_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_prr_001;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/idInterno/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_PRR_001 getInterno(@PathVariable("id") String id) {
        FRA_PRR_001 fra_prr_001 = fra_prr_001_service.findByIdInternoMuestra(id);

        if (fra_prr_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_prr_001;
    }

    //ListarUnElementoDATAS
    @RequestMapping(method = RequestMethod.GET, value = "/individual1/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_PRR_001_DATA_01 getIndividual(@PathVariable("id") Long id) {
        FRA_PRR_001_DATA_01 fra_prr_001_data_01 = fra_prr_001_data_01_repository.findByIdFRAPRRDATA01(id);

        if (fra_prr_001_data_01 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_prr_001_data_01;
    }

    //ListarUnElementoDATAS
    @RequestMapping(method = RequestMethod.GET, value = "/individual2/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_PRR_001_DATA_02 getIndividual2(@PathVariable("id") Long id) {
        FRA_PRR_001_DATA_02 fra_prr_001_data_02 = fra_prr_001_data_02_repository.findByIdFRAPRRDATA02(id);

        if (fra_prr_001_data_02 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_prr_001_data_02;
    }

    //ObtenerPorFolio
    @RequestMapping(method = RequestMethod.GET, value = "/busquedaFolio/{folio}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_PRR_001 getByFolio(@PathVariable("folio") String folio) {
        FRA_PRR_001 fra_prr_001 = fra_prr_001_service.findByFolio(folio);

        if (fra_prr_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_prr_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {

        FRA_PRR_001 fra_prr_001 = new FRA_PRR_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));

        fra_prr_001.setFolioTecnica(request.get("folioTecnica"));
        fra_prr_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_prr_001.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_prr_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_prr_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
        fra_prr_001.setTemperatura(request.get("temperatura"));
        fra_prr_001.setHumedadRelativa(request.get("humedadRelativa"));

        fra_prr_001.setCodigoPendulo(request.get("codigoPendulo"));
        fra_prr_001.setCodigoManometro(request.get("codigoManometro"));
        fra_prr_001.setCapacidadEquipo(request.get("capacidadEquipo"));
        fra_prr_001.setPrensaEnsayo(request.get("prensaEnsayo"));
        fra_prr_001.setPesaCalibracion(request.get("pesaCalibracion"));

        fra_prr_001.setMetodoMuestra(metodoMuestra);

        fra_prr_001.setEstatus("inicio");
        fra_prr_001.setCantidadModificaciones("3");

        fra_prr_001_service.save(fra_prr_001);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/iniciar/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    public ResponseEntity<?> iniciar(@PathVariable("id") Long id) throws Exception {
        APP.debug("Se inició FRA_PRR a las: " + calendario.getTime());

        FRA_PRR_001 fra_prr_001 = fra_prr_001_service.findById(id);
        fra_prr_001.setEstatus("progreso");

        fra_prr_001_service.save(fra_prr_001);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //GuardarElemento
    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> agrega(@RequestPart("fraprr") Map<String, String> request,
                                    Principal principal) {

        FRA_PRR_001 fra_prr_001 = fra_prr_001_service.findById(Long.parseLong(request.get("id")));
        FRA_PRR_001_DATA_01 fra_prr_001_data_01 = new FRA_PRR_001_DATA_01();
        FRA_PRR_001_DATA_02 fra_prr_001_data_02 = new FRA_PRR_001_DATA_02();

        fra_prr_001_data_01.setEspesor1(request.get("espesor1MD"));
        fra_prr_001_data_01.setEspesor2(request.get("espesor2MD"));
        fra_prr_001_data_01.setEspesor3(request.get("espesor3MD"));
        fra_prr_001_data_01.setResistenciaRasgado(request.get("resistenciaRasgadoMD"));
        fra_prr_001_data_01.setDesgarreOblicuo(request.get("desgarreOblicuoMD"));

        fra_prr_001_data_02.setEspesor1(request.get("espesor1TD"));
        fra_prr_001_data_02.setEspesor2(request.get("espesor2TD"));
        fra_prr_001_data_02.setEspesor3(request.get("espesor3TD"));
        fra_prr_001_data_02.setResistenciaRasgado(request.get("resistenciaRasgadoTD"));
        fra_prr_001_data_02.setDesgarreOblicuo(request.get("desgarreOblicuoTD"));

        double promedioTD = 0.0, promedioMD = 0.0;

        promedioMD = (
                Double.parseDouble(fra_prr_001_data_01.getEspesor1()) +
                        Double.parseDouble(fra_prr_001_data_01.getEspesor2()) +
                        Double.parseDouble(fra_prr_001_data_01.getEspesor3())) / 3;

        promedioTD = (
                Double.parseDouble(fra_prr_001_data_02.getEspesor1()) +
                        Double.parseDouble(fra_prr_001_data_02.getEspesor2()) +
                        Double.parseDouble(fra_prr_001_data_02.getEspesor3())) / 3;

        fra_prr_001_data_01.setEspesorPromedio(String.format("%.3f", promedioMD));
        fra_prr_001_data_02.setEspesorPromedio(String.format("%.3f", promedioTD));

        fra_prr_001_data_01.setFra_prr_001(fra_prr_001);
        fra_prr_001_data_02.setFra_prr_001(fra_prr_001);

        fra_prr_001_data_01_repository.save(fra_prr_001_data_01);
        fra_prr_001_data_02_repository.save(fra_prr_001_data_02);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //GuardarElemento
    @RequestMapping(value = "/terminar", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create2(@RequestPart("fraprr") Map<String, String> request,
                                     @RequestPart("signature") MultipartFile signature,
                                     Principal principal) {
        APP.debug("Se finalizó FRA_PRR a las: " + calendario.getTime());

        FRA_PRR_001 fra_prr_001 = fra_prr_001_service.findById(Long.parseLong(request.get("id")));
        List<FRA_PRR_001_DATA_01> lista1 = fra_prr_001_data_01_repository.buscarTodosPorEnsayo(fra_prr_001.getIdFRAPRR());
        List<FRA_PRR_001_DATA_02> lista2 = fra_prr_001_data_02_repository.buscarTodosPorEnsayo(fra_prr_001.getIdFRAPRR());
        SaveInServer saveInServer = new SaveInServer();

        try {
            fra_prr_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_prr_001.setObservaciones(request.get("observaciones"));
            fra_prr_001.setRealizo(request.get("realizo"));
            fra_prr_001.setSupervisor(request.get("supervisor"));

            fra_prr_001.setRubricaRealizo(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_REALIZO_PRR + saveInServer.SaveInServer(signature, Constantes.SIGNATURE_REALIZO_PRR));

            double promedioMD = 0.0, promedioTD = 0.0, espesorPromedioMD = 0.0, espesorPromedioTD = 0.0;
            int desgarreMD = 0, desgarreTD = 0;
            for (int i = 0; i < lista1.size(); i++) {
                espesorPromedioMD = espesorPromedioMD + Double.parseDouble(lista1.get(i).getEspesorPromedio());
                espesorPromedioTD = espesorPromedioTD + Double.parseDouble(lista2.get(i).getEspesorPromedio());
                promedioMD = promedioMD + Double.parseDouble(lista1.get(i).getResistenciaRasgado());
                promedioTD = promedioTD + Double.parseDouble(lista2.get(i).getResistenciaRasgado());

                if (lista1.get(i).getDesgarreOblicuo().equals("O")) {
                    desgarreMD = desgarreMD + 10;
                }

                if (lista2.get(i).getDesgarreOblicuo().equals("O")) {
                    desgarreTD = desgarreTD + 10;
                }
            }

            fra_prr_001.setPromedioResistenciaRasgadoMD(String.format("%.3f", promedioMD / 10));
            fra_prr_001.setPromedioResistenciaRasgadoTD(String.format("%.3f", promedioTD / 10));

            fra_prr_001.setDesgarreOblicuioMD(desgarreMD + "");
            fra_prr_001.setDesgarreOblicuioTD(desgarreTD + "");

            fra_prr_001.setEspesorPromedioMD(String.format("%.3f", espesorPromedioMD / 10));
            fra_prr_001.setEspesorPromedioTD(String.format("%.3f", espesorPromedioTD / 10));

            Double minMD = Double.parseDouble(lista1.get(0).getResistenciaRasgado());
            Double maxMD = Double.parseDouble(lista1.get(0).getResistenciaRasgado());
            Double minTD = Double.parseDouble(lista2.get(0).getResistenciaRasgado());
            Double maxTD = Double.parseDouble(lista2.get(0).getResistenciaRasgado());
            Double desviacionEstandarMD = 0.0, desviacionEstandarTD = 0.0;

            for (int i = 0; i < lista1.size(); i++) {
                if (maxMD < Double.parseDouble(lista1.get(i).getResistenciaRasgado())){
                    maxMD = Double.parseDouble(lista1.get(i).getResistenciaRasgado());
                }
                if (minMD > Double.parseDouble(lista1.get(i).getResistenciaRasgado())){
                    minMD = Double.parseDouble(lista1.get(i).getResistenciaRasgado());
                }
                if (maxTD < Double.parseDouble(lista2.get(i).getResistenciaRasgado())){
                    maxTD = Double.parseDouble(lista2.get(i).getResistenciaRasgado());
                }
                if (minTD > Double.parseDouble(lista2.get(i).getResistenciaRasgado())){
                    minTD = Double.parseDouble(lista2.get(i).getResistenciaRasgado());
                }

                desviacionEstandarMD = desviacionEstandarMD + Math.pow(Double.parseDouble(lista1.get(i).getResistenciaRasgado()) - promedioMD, 2);
                desviacionEstandarTD = desviacionEstandarTD + Math.pow(Double.parseDouble(lista2.get(i).getResistenciaRasgado()) - promedioTD, 2);
            }

            fra_prr_001.setMinMD(minMD + "");
            fra_prr_001.setMaxMD(maxMD + "");
            fra_prr_001.setMinTD(minTD + "");
            fra_prr_001.setMaxTD(maxTD + "");
            fra_prr_001.setDesvEstandarMD(String.format("%.3f", Math.sqrt((desviacionEstandarMD / 10))));
            fra_prr_001.setDesvEstandarTD(String.format("%.3f", Math.sqrt((desviacionEstandarTD / 10))));

            fra_prr_001.setEstatus("terminado");

            fra_prr_001_service.save(fra_prr_001);

            MetodoMuestra metodoMuestra = fra_prr_001.getMetodoMuestra();
            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-PRR-001");
        System.out.println(LocalTime.now());

        return fra_19_prr_print.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-PRR-001");
        System.out.println(LocalTime.now());

        return fra_19_prr_print.crearFormato(id, 2);
    }

    @RequestMapping(value = "/imprimir3", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir3() throws Exception {

        return lff_mie_met_xx_print.crearListaFolios("19-LFF-MIE-MET-PRR-001", 69L);
    }
}
