package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra20rter.FRA_RTER_001;
import com.demo.model.operacion.metodos.fra20rter.datas.FRA_RTER_001_DATA_01;
import com.demo.model.operacion.metodos.fra20rter.datas.FRA_RTER_001_DATA_02;
import com.demo.repository.operacion.metodos.fra20rter.datas.FRA_RTER_001_DATA_01_Repository;
import com.demo.repository.operacion.metodos.fra20rter.datas.FRA_RTER_001_DATA_02_Repository;
import com.demo.service.formatos.metodos.FRA_20_RTER_Print;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.metodos.FRA_RTER_001_Service;
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
@RequestMapping(path = "/FRARTER")
public class FRA_20_RTER_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_RTER_001_Service fra_rter_001_service;

    @Autowired
    private FRA_RTER_001_DATA_01_Repository fra_rter_001_data_01_repository;

    @Autowired
    private FRA_RTER_001_DATA_02_Repository fra_rter_001_data_02_repository;

    @Autowired
    private FRA_20_RTER_Print fra_20_rter_print;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_RTER_001> getAll() {
        return fra_rter_001_service.findAll();
    }

    //ListarTodoPorData
    @RequestMapping(method = RequestMethod.GET, value = "/getAll1By/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_RTER_001_DATA_01> getAllBy1(@PathVariable("id") Long id) {
        return fra_rter_001_data_01_repository.buscarTodosPorEnsayo(id);
    }

    //ListarTodoPorData
    @RequestMapping(method = RequestMethod.GET, value = "/getAll2By/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_RTER_001_DATA_02> getAllBy2(@PathVariable("id") Long id) {
        return fra_rter_001_data_02_repository.buscarTodosPorEnsayo(id);
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_RTER_001 get(@PathVariable("id") Long id) {
        FRA_RTER_001 fra_rter_001 = fra_rter_001_service.findById(id);

        if (fra_rter_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_rter_001;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/idInterno/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_RTER_001 getInterno(@PathVariable("id") String id) {
        FRA_RTER_001 fra_rter_001 = fra_rter_001_service.findByIdInternoMuestra(id);

        if (fra_rter_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_rter_001;
    }

    //ListarUnElementoDATAS
    @RequestMapping(method = RequestMethod.GET, value = "/individual1/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_RTER_001_DATA_01 getIndividual(@PathVariable("id") Long id) {
        FRA_RTER_001_DATA_01 fra_rter_001_data_01 = fra_rter_001_data_01_repository.findByIdFRARTERDATA01(id);

        if (fra_rter_001_data_01 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_rter_001_data_01;
    }

    //ListarUnElementoDATAS
    @RequestMapping(method = RequestMethod.GET, value = "/individual2/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_RTER_001_DATA_02 getIndividual2(@PathVariable("id") Long id) {
        FRA_RTER_001_DATA_02 fra_rter_001_data_02 = fra_rter_001_data_02_repository.findByIdFRARTERDATA02(id);

        if (fra_rter_001_data_02 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_rter_001_data_02;
    }

    //ObtenerPorFolio
    @RequestMapping(method = RequestMethod.GET, value = "/busquedaFolio/{folio}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_RTER_001 getByFolio(@PathVariable("folio") String folio) {
        FRA_RTER_001 fra_rter_001 = fra_rter_001_service.findByFolio(folio);

        if (fra_rter_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_rter_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {

        FRA_RTER_001 fra_rter_001 = new FRA_RTER_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));

        fra_rter_001.setFolioTecnica(request.get("folioTecnica"));
        fra_rter_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_rter_001.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_rter_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_rter_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
        fra_rter_001.setTemperatura(request.get("temperatura"));
        fra_rter_001.setHumedadRelativa(request.get("humedadRelativa"));

        fra_rter_001.setCodigoEquipoUniversal(request.get("codigoEquipoUniversal"));
        fra_rter_001.setCodigoMicrometro(request.get("codigoMicrometro"));
        fra_rter_001.setDistanciaEntreMordazas(request.get("distanciaEntreMordazas"));
        fra_rter_001.setVelocidadDeformacion(request.get("velocidadDeformacion"));
        fra_rter_001.setAnchoProbeta(request.get("anchoProbeta"));

        fra_rter_001.setMetodoMuestra(metodoMuestra);

        fra_rter_001.setEstatus("inicio");
        fra_rter_001.setCantidadModificaciones("3");

        fra_rter_001_service.save(fra_rter_001);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/iniciar/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    public ResponseEntity<?> iniciar(@PathVariable("id") Long id) throws Exception {
        APP.debug("Se inició FRA_RTER a las: " + calendario.getTime());

        FRA_RTER_001 fra_rter_001 = fra_rter_001_service.findById(id);
        fra_rter_001.setEstatus("progreso");

        fra_rter_001_service.save(fra_rter_001);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //GuardarElemento
    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> agrega(@RequestPart("frarter") Map<String, String> request,
                                    Principal principal) {

        FRA_RTER_001 fra_rter_001 = fra_rter_001_service.findById(Long.parseLong(request.get("id")));
        FRA_RTER_001_DATA_01 fra_rter_001_data_01 = new FRA_RTER_001_DATA_01();
        FRA_RTER_001_DATA_02 fra_rter_001_data_02 = new FRA_RTER_001_DATA_02();

        fra_rter_001_data_01.setMedicion(request.get("medicionMD"));
        fra_rter_001_data_01.setE1(request.get("e1MD"));
        fra_rter_001_data_01.setE2(request.get("e2MD"));
        fra_rter_001_data_01.setE3(request.get("e3MD"));
        fra_rter_001_data_01.setFuerzaFluenciaTension(request.get("fuerzaFluenciaTensionMD"));
        fra_rter_001_data_01.setElongacionRuptura(request.get("elongacionRupturaMD"));
        fra_rter_001_data_01.setResistenciaTension(request.get("resistenciaTensionMD"));
        fra_rter_001_data_01.setPendiente(request.get("pendienteMD"));
        fra_rter_001_data_01.setCoeficienteCorrelacionPearson(request.get("coeficienteCorrelacionPearsonMD"));

        fra_rter_001_data_02.setMedicion(request.get("medicionTD"));
        fra_rter_001_data_02.setE1(request.get("e1TD"));
        fra_rter_001_data_02.setE2(request.get("e2TD"));
        fra_rter_001_data_02.setE3(request.get("e3TD"));
        fra_rter_001_data_02.setFuerzaFluenciaTension(request.get("fuerzaFluenciaTensionTD"));
        fra_rter_001_data_02.setElongacionRuptura(request.get("elongacionRupturaTD"));
        fra_rter_001_data_02.setResistenciaTension(request.get("resistenciaTensionTD"));
        fra_rter_001_data_02.setPendiente(request.get("pendienteTD"));
        fra_rter_001_data_02.setCoeficienteCorrelacionPearson(request.get("coeficienteCorrelacionPearsonTD"));

        double espesorPromedioMD, espesorPromedioTD;
        double moduloElasticoMD, moduloElasticoTD;

        espesorPromedioMD = (
                            Double.parseDouble(fra_rter_001_data_01.getE1()) +
                            Double.parseDouble(fra_rter_001_data_01.getE2()) +
                            Double.parseDouble(fra_rter_001_data_01.getE3())) / 3;

        espesorPromedioTD = (
                            Double.parseDouble(fra_rter_001_data_02.getE1()) +
                            Double.parseDouble(fra_rter_001_data_02.getE2()) +
                            Double.parseDouble(fra_rter_001_data_02.getE3())) / 3;

        moduloElasticoMD = (Double.parseDouble(fra_rter_001_data_01.getPendiente()) * 100) / (Math.pow(10, 6));
        moduloElasticoTD = (Double.parseDouble(fra_rter_001_data_02.getPendiente()) * 100) / (Math.pow(10, 6));

        fra_rter_001_data_01.setPromedioEspesor(String.format("%.3f", espesorPromedioMD));
        fra_rter_001_data_02.setPromedioEspesor(String.format("%.3f", espesorPromedioTD));
        fra_rter_001_data_01.setModuloElastico(String.format("%.4f", moduloElasticoMD));
        fra_rter_001_data_02.setModuloElastico(String.format("%.4f", moduloElasticoTD));


        fra_rter_001_data_01.setFra_rter_001(fra_rter_001);
        fra_rter_001_data_02.setFra_rter_001(fra_rter_001);

        fra_rter_001_data_01_repository.save(fra_rter_001_data_01);
        fra_rter_001_data_02_repository.save(fra_rter_001_data_02);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //GuardarElemento
    @RequestMapping(value = "/terminar", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create2(@RequestPart("frarter") Map<String, String> request,
                                     @RequestPart("signature") MultipartFile signature,
                                     Principal principal) {

        FRA_RTER_001 fra_rter_001 = fra_rter_001_service.findById(Long.parseLong(request.get("id")));
        List<FRA_RTER_001_DATA_01> lista1 = fra_rter_001_data_01_repository.buscarTodosPorEnsayo(fra_rter_001.getIdFRARTER());
        List<FRA_RTER_001_DATA_02> lista2 = fra_rter_001_data_02_repository.buscarTodosPorEnsayo(fra_rter_001.getIdFRARTER());
        SaveInServer saveInServer = new SaveInServer();

        try {
            fra_rter_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_rter_001.setTasaDeformacion(request.get("tasaDeformacion"));
            fra_rter_001.setObservaciones(request.get("observaciones"));
            fra_rter_001.setRealizo(request.get("realizo"));
            fra_rter_001.setSupervisor(request.get("supervisor"));

            fra_rter_001.setRubricaRealizo(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_REALIZO_RTER + saveInServer.SaveInServer(signature, Constantes.SIGNATURE_REALIZO_RTER));

            List<Double> listaPromediosMD = new ArrayList<>(Collections.nCopies(7, (Double) 0.0));
            List<Double> listaPromediosTD = new ArrayList<>(Collections.nCopies(7, (Double) 0.0));

            for (int i = 0; i < lista1.size(); i++) {
                listaPromediosMD.set(0, listaPromediosMD.get(0) + Double.parseDouble(lista1.get(i).getPromedioEspesor()));
                listaPromediosMD.set(1, listaPromediosMD.get(1) + Double.parseDouble(lista1.get(i).getFuerzaFluenciaTension()));
                listaPromediosMD.set(2, listaPromediosMD.get(2) + Double.parseDouble(lista1.get(i).getElongacionRuptura()));
                listaPromediosMD.set(3, listaPromediosMD.get(3) + Double.parseDouble(lista1.get(i).getResistenciaTension()));
                listaPromediosMD.set(4, listaPromediosMD.get(4) + Double.parseDouble(lista1.get(i).getPendiente()));
                listaPromediosMD.set(5, listaPromediosMD.get(5) + Double.parseDouble(lista1.get(i).getCoeficienteCorrelacionPearson()));
                listaPromediosMD.set(6, listaPromediosMD.get(6) + Double.parseDouble(lista1.get(i).getModuloElastico()));

                listaPromediosTD.set(0, listaPromediosTD.get(0) + Double.parseDouble(lista2.get(i).getPromedioEspesor()));
                listaPromediosTD.set(1, listaPromediosTD.get(1) + Double.parseDouble(lista2.get(i).getFuerzaFluenciaTension()));
                listaPromediosTD.set(2, listaPromediosTD.get(2) + Double.parseDouble(lista2.get(i).getElongacionRuptura()));
                listaPromediosTD.set(3, listaPromediosTD.get(3) + Double.parseDouble(lista2.get(i).getResistenciaTension()));
                listaPromediosTD.set(4, listaPromediosTD.get(4) + Double.parseDouble(lista2.get(i).getPendiente()));
                listaPromediosTD.set(5, listaPromediosTD.get(5) + Double.parseDouble(lista2.get(i).getCoeficienteCorrelacionPearson()));
                listaPromediosTD.set(6, listaPromediosTD.get(6) + Double.parseDouble(lista2.get(i).getModuloElastico()));
            }

            fra_rter_001.setPromedioEspesorPromedioMD(              String.format("%.3f", listaPromediosMD.get(0) / 5 ));
            fra_rter_001.setPromedioFuerzaFluenciatensiónMD(        String.format("%.3f", listaPromediosMD.get(1) / 5 ));
            fra_rter_001.setPromedioElongacionRupturaMD(            String.format("%.3f", listaPromediosMD.get(2) / 5 ));
            fra_rter_001.setPromedioResistenciaTensionMD(           String.format("%.3f", listaPromediosMD.get(3) / 5 ));
            fra_rter_001.setPromedioPendienteMD(                    String.format("%.3f", listaPromediosMD.get(4) / 5 ));
            fra_rter_001.setPromedioCoeficienteCorrelacionPearsonMD(String.format("%.3f", listaPromediosMD.get(5) / 5 ));
            fra_rter_001.setPromedioModuloElasticoMD(               String.format("%.4f", listaPromediosMD.get(6) / 5 ));

            fra_rter_001.setPromedioEspesorPromedioTD(              String.format("%.3f", listaPromediosTD.get(0) / 5 ));
            fra_rter_001.setPromedioFuerzaFluenciatensiónTD(        String.format("%.3f", listaPromediosTD.get(1) / 5 ));
            fra_rter_001.setPromedioElongacionRupturaTD(            String.format("%.3f", listaPromediosTD.get(2) / 5 ));
            fra_rter_001.setPromedioResistenciaTensionTD(           String.format("%.3f", listaPromediosTD.get(3) / 5 ));
            fra_rter_001.setPromedioPendienteTD(                    String.format("%.3f", listaPromediosTD.get(4) / 5 ));
            fra_rter_001.setPromedioCoeficienteCorrelacionPearsonTD(String.format("%.3f", listaPromediosTD.get(5) / 5 ));
            fra_rter_001.setPromedioModuloElasticoTD(               String.format("%.4f", listaPromediosTD.get(6) / 5 ));


            fra_rter_001.setEstatus("terminado");

            fra_rter_001_service.save(fra_rter_001);

            MetodoMuestra metodoMuestra = fra_rter_001.getMetodoMuestra();
            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-RTER-001");
        System.out.println(LocalTime.now());

        return fra_20_rter_print.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-RTER-001");
        System.out.println(LocalTime.now());

        return fra_20_rter_print.crearFormato(id, 2);
    }
}
