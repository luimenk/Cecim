package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra14oit.FRA_OIT_001;
import com.demo.model.operacion.metodos.fra14oit.datas.FRA_OIT_001_DATA;
import com.demo.repository.operacion.metodos.fra14oit.datas.FRA_OIT_001_DATA_Repository;
import com.demo.service.formatos.metodos.FRA_14_OIT_Print;
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
@RequestMapping(path = "/FRAOIT")
public class FRA_14_OIT_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_OIT_001_Service fra_oit_001_service;

    @Autowired
    private FRA_OIT_001_DATA_Repository fra_oit_001_data_repository;

    @Autowired
    private FRA_14_OIT_Print fra_14_oit_print;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_OIT_001> getAll() {
        return fra_oit_001_service.findAll();
    }

    //ListarTodoPorData
    @RequestMapping(method = RequestMethod.GET, value = "/getAllBy/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_OIT_001_DATA> getAllBy(@PathVariable("id") Long id) {
        return fra_oit_001_data_repository.buscarTodosPorEnsayo(id);
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_OIT_001 get(@PathVariable("id") Long id) {
        FRA_OIT_001 fra_oit_001 = fra_oit_001_service.findById(id);

        if (fra_oit_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_oit_001;
    }

    //ListarUnElementoDATAS
    @RequestMapping(method = RequestMethod.GET, value = "/individual/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_OIT_001_DATA getIndividual(@PathVariable("id") Long id) {
        FRA_OIT_001_DATA fra_oit_001_data = fra_oit_001_data_repository.findByIdFRAOITDATA(id);

        if (fra_oit_001_data == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_oit_001_data;
    }

    //ObtenerPorFolio
    @RequestMapping(method = RequestMethod.GET, value = "/busquedaFolio/{folio}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_OIT_001 getByFolio(@PathVariable("folio") String folio) {
        FRA_OIT_001 fra_oit_001 = fra_oit_001_service.findByFolio(folio);

        if (fra_oit_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_oit_001;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/idInterno/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_OIT_001 getInterno(@PathVariable("id") String id) {
        FRA_OIT_001 fra_oit_001 = fra_oit_001_service.findByIdInternoMuestra(id);

        if (fra_oit_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_oit_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestPart("fraoit") Map<String, String> request, Principal principal) {

        APP.debug("Registro FRA_OIT a las: " + calendario.getTime());
        FRA_OIT_001 fra_oit_001 = new FRA_OIT_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));

        try {
            fra_oit_001.setFolioTecnica(request.get("folioTecnica"));
            fra_oit_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
            fra_oit_001.setIdInternoMuestra(request.get("idInternoMuestra"));
            fra_oit_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_oit_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_oit_001.setTemperatura(request.get("temperatura"));
            fra_oit_001.setHumedadRelativa(request.get("humedadRelativa"));

            fra_oit_001.setCodigoDSC(request.get("codigoDSC"));
            fra_oit_001.setCodigoBalanza(request.get("codigoBalanza"));
            fra_oit_001.setPesom1(request.get("pesom1"));
            fra_oit_001.setPesom2(request.get("pesom2"));
            fra_oit_001.setPosicionm1(request.get("posicionm1"));
            fra_oit_001.setPosicionm2(request.get("posicionm2"));
            fra_oit_001.setPurga(request.get("purga"));
            fra_oit_001.setTpurga(request.get("tpurga"));
            fra_oit_001.setTequilibrio(request.get("tequilibrio"));
            fra_oit_001.setTiempoEquilibrio(request.get("tiempoEquilibrio"));
            fra_oit_001.setDinamicaCal1(request.get("dinamicaCal1"));
            fra_oit_001.setTasaCalentamiento(request.get("tasaCalentamiento"));
            fra_oit_001.setTiempoIsotermico1(request.get("tiempoIsotermico1"));
            fra_oit_001.setTiempoIsotermico2(request.get("tiempoIsotermico2"));
            fra_oit_001.setDinamicaEnf1(request.get("dinamicaEnf1"));
            fra_oit_001.setTasaEnfriamiento(request.get("tasaEnfriamiento"));
            fra_oit_001.setTemperaturaEmergencia(request.get("temperaturaEmergencia"));
            fra_oit_001.setEstatus("INICIADO");

            fra_oit_001.setMetodoMuestra(metodoMuestra);

            fra_oit_001_service.save(fra_oit_001);

            for (int i=0; i<Integer.parseInt(request.get("numeroRepeticiones")); i++){
                FRA_OIT_001_DATA fra_oit_001_data = new FRA_OIT_001_DATA();
                double promedio = 0.0;

                fra_oit_001_data.setNoDobleces(request.get("noDobleces" + i));
                fra_oit_001_data.setE1(request.get("e1" + i));
                fra_oit_001_data.setE2(request.get("e2" + i));
                fra_oit_001_data.setE3(request.get("e3" + i));
                promedio = (Double.parseDouble(fra_oit_001_data.getE1()) + Double.parseDouble(fra_oit_001_data.getE2()) + Double.parseDouble(fra_oit_001_data.getE3())) / 3;
                fra_oit_001_data.setEspesorPromedio(String.format("%.1f", promedio));
                fra_oit_001_data.setFra_oit_001(fra_oit_001);

                fra_oit_001_data_repository.save(fra_oit_001_data);
            }

            fra_oit_001.setNumeroRepeticiones(request.get("numeroRepeticiones"));
            fra_oit_001.setMetodoMuestra(metodoMuestra);
            fra_oit_001_service.save(fra_oit_001);
            metodoMuestra.setEstatus("INICIADO");
            metodoMuestraService.save(metodoMuestra);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/finalizar")
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create2(@RequestPart("fraoit") Map<String, String> request,
                                     @RequestPart("imagen") MultipartFile file,
                                     @RequestPart("signature") MultipartFile signature,
                                     Principal principal) {
        APP.debug("Registro FRA_OIT a las: " + calendario.getTime());

        FRA_OIT_001 fra_oit_001 = fra_oit_001_service.findById(Long.parseLong(request.get("id")));
        //MetodoMuestra metodoMuestra = metodoMuestraService.findById(fra_oit_001.getMetodoMuestra().getMetodoMuestraId());

        SaveInServer saveInServer = new SaveInServer();
        try {
            fra_oit_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));

            if (fra_oit_001.getNumeroRepeticiones().equals("1")) {
                fra_oit_001.setM1OIT(request.get("m1OIT"));
                fra_oit_001.setM2OIT("N/A");
                fra_oit_001.setPromedioOIT(String.format("%.1f", Double.parseDouble(fra_oit_001.getM1OIT())));
            } else {
                float r1 = Float.parseFloat(request.get("m1OIT"));
                float r2 = Float.parseFloat(request.get("m2OIT"));
                float promedio = (r1+r2) / 2;
                fra_oit_001.setM1OIT(r1 + "");
                fra_oit_001.setM2OIT(r2 + "");
                fra_oit_001.setPromedioOIT(String.format("%.1f", promedio));
            }

            fra_oit_001.setObservaciones(request.get("observaciones"));
            fra_oit_001.setRealizo(request.get("realizo"));
            fra_oit_001.setSupervisor(request.get("supervisor"));
            fra_oit_001.setPathImagen(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.RUTA_IMG_14_OIT + saveInServer.SaveInServer(file, Constantes.RUTA_IMG_14_OIT));
            fra_oit_001.setRubricaRealizo(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_REALIZO_OIT + saveInServer.SaveInServer(signature, Constantes.SIGNATURE_REALIZO_OIT));

            fra_oit_001.setEstatus("FINALIZADO");
            fra_oit_001.setCantidadModificaciones("3");

            fra_oit_001_service.save(fra_oit_001);

            MetodoMuestra metodoMuestra = fra_oit_001.getMetodoMuestra();
            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);

        } catch (NullPointerException | IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/modificar")
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> modificar(@RequestPart("fraoit") Map<String, String> request,
                                       @RequestPart(value = "imagen", required = false) MultipartFile file,
                                       Principal principal) {
        APP.debug("Modificar FRA_OIT a las: " + calendario.getTime());
        String filePath = "";

        FRA_OIT_001 fra_oit_001 = fra_oit_001_service.findById(Long.parseLong(request.get("id")));

        if (fra_oit_001.getCantidadModificaciones().equals("0")){
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }

//        try {
            fra_oit_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_oit_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_oit_001.setTemperatura(request.get("temperatura"));
            fra_oit_001.setHumedadRelativa(request.get("humedadRelativa"));
//            fra_oit_001.setTiempoIsoterma(request.get("tiempoIsoterma"));
//            if (fra_oit_001.getNumeroRepeticiones().equals("1")) {
//                fra_oit_001.setEspesor1(request.get("espesor1"));
//                fra_oit_001.setPeso1(request.get("peso1"));
//                fra_oit_001.setPpmdsc1(request.get("ppmdsc1"));
//                fra_oit_001.setRepeticion1OIT(request.get("repeticion1OIT"));
//                fra_oit_001.setPromedio(fra_oit_001.getRepeticion1OIT());
//            } else {
//                fra_oit_001.setEspesor1(request.get("espesor1"));
//                fra_oit_001.setPeso1(request.get("peso1"));
//                fra_oit_001.setPpmdsc1(request.get("ppmdsc1"));
//                fra_oit_001.setEspesor2(request.get("espesor2"));
//                fra_oit_001.setPeso2(request.get("peso2"));
//                fra_oit_001.setPpmdsc2(request.get("ppmdsc2"));
//                float r1 = Float.parseFloat(request.get("repeticion1OIT"));
//                float r2 = Float.parseFloat(request.get("repeticion2OIT"));
//                float promedio = (r1 + r2) / 2;
//                fra_oit_001.setRepeticion1OIT(request.get("repeticion1OIT"));
//                fra_oit_001.setRepeticion2OIT(request.get("repeticion2OIT"));
//                fra_oit_001.setPromedio(promedio + "");
//            }

            fra_oit_001.setObservaciones(request.get("observaciones"));
            fra_oit_001.setRealizo(request.get("realizo"));
            fra_oit_001.setSupervisor(request.get("supervisor"));
            fra_oit_001.setEstatus("MODIFICADO");
            int aux = Integer.parseInt(fra_oit_001.getCantidadModificaciones());
            aux--;

            fra_oit_001.setCantidadModificaciones(aux + "");

            if (file == null) {
                System.out.println("El archivo no fue modificado");
            } else {
//                fra_oit_001.setPathImage(filePath + saveInServer.SaveInServer(file, filePath));
            }

            fra_oit_001_service.save(fra_oit_001);

//        } catch (NullPointerException | IOException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-OIT-001");
        System.out.println(LocalTime.now());

        return fra_14_oit_print.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-OIT-001");
        System.out.println(LocalTime.now());

        return fra_14_oit_print.crearFormato(id, 2);
    }

    @RequestMapping(value = "/imprimir3", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir3() throws Exception {
        System.out.println("Se generó BFF-MIE-015");
        System.out.println(LocalTime.now());

        return fra_14_oit_print.crearListaFolios();
    }
}
