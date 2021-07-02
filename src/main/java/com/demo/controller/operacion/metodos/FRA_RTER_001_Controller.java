package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra20rter.FRA_RTER_001;
import com.demo.service.formatos.metodos.IMPRIMIR_FRA_RTER_001_Service;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.metodos.FRA_RTER_001_Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping(path = "/elongacionRuptura")
public class FRA_RTER_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_RTER_001_Service fra_rter_001_service;

    @Autowired
    private IMPRIMIR_FRA_RTER_001_Service imprimir_fra_rter_001_service;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_RTER_001> getAll() {
        return fra_rter_001_service.findAll();
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

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {
        APP.debug("Apartado de registro nuevo" + calendario.getTime());

        FRA_RTER_001 fra_rter_001 = new FRA_RTER_001();

        fra_rter_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_rter_001.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_rter_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_rter_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));

        fra_rter_001.setTemperatura(request.get("temperatura"));
        fra_rter_001.setHumedadRelativa(request.get("humedadRelativa"));
        fra_rter_001.setCodigoEquipoUniversal(request.get("codigoEquipoUniversal"));
        fra_rter_001.setCodigoMicrometro(request.get("codigoMicrometro"));
//        fra_rter_001.setVelocidadMordazas(request.get("velocidadMordazas"));
        fra_rter_001.setDistanciaEntreMordazas(request.get("distanciaEntreMordazas"));

//        fra_rter_001.setMDespesor1(request.get("MDespesor1"));
//        fra_rter_001.setMDfuerzaFluencia1(request.get("MDfuerzaFluencia1"));
//        fra_rter_001.setMDelongacionRuptura1(request.get("MDelongacionRuptura1"));
//        fra_rter_001.setMDresistenciaTension1(request.get("MDresistenciaTension1"));
//        fra_rter_001.setMDmoduloElastico1(request.get("MDmoduloElastico1"));
//
//        fra_rter_001.setMDespesor2(request.get("MDespesor2"));
//        fra_rter_001.setMDfuerzaFluencia2(request.get("MDfuerzaFluencia2"));
//        fra_rter_001.setMDelongacionRuptura2(request.get("MDelongacionRuptura2"));
//        fra_rter_001.setMDresistenciaTension2(request.get("MDresistenciaTension2"));
//        fra_rter_001.setMDmoduloElastico2(request.get("MDmoduloElastico2"));
//
//        fra_rter_001.setMDespesor3(request.get("MDespesor3"));
//        fra_rter_001.setMDfuerzaFluencia3(request.get("MDfuerzaFluencia3"));
//        fra_rter_001.setMDelongacionRuptura3(request.get("MDelongacionRuptura3"));
//        fra_rter_001.setMDresistenciaTension3(request.get("MDresistenciaTension3"));
//        fra_rter_001.setMDmoduloElastico3(request.get("MDmoduloElastico3"));
//
//        fra_rter_001.setMDespesor4(request.get("MDespesor4"));
//        fra_rter_001.setMDfuerzaFluencia4(request.get("MDfuerzaFluencia4"));
//        fra_rter_001.setMDelongacionRuptura4(request.get("MDelongacionRuptura4"));
//        fra_rter_001.setMDresistenciaTension4(request.get("MDresistenciaTension4"));
//        fra_rter_001.setMDmoduloElastico4(request.get("MDmoduloElastico4"));
//
//        fra_rter_001.setMDespesor5(request.get("MDespesor5"));
//        fra_rter_001.setMDfuerzaFluencia5(request.get("MDfuerzaFluencia5"));
//        fra_rter_001.setMDelongacionRuptura5(request.get("MDelongacionRuptura5"));
//        fra_rter_001.setMDresistenciaTension5(request.get("MDresistenciaTension5"));
//        fra_rter_001.setMDmoduloElastico5(request.get("MDmoduloElastico5"));
//
//        Float MDEspesor =   Float.parseFloat(fra_rter_001.getMDespesor1()) +
//                            Float.parseFloat(fra_rter_001.getMDespesor2()) +
//                            Float.parseFloat(fra_rter_001.getMDespesor3()) +
//                            Float.parseFloat(fra_rter_001.getMDespesor4()) +
//                            Float.parseFloat(fra_rter_001.getMDespesor5());
//
//        fra_rter_001.setMDpromedioEspesor(String.valueOf(MDEspesor/5));

//        Float MDFuerzaFluencia =    Float.parseFloat(fra_rter_001.getMDfuerzaFluencia1()) +
//                                    Float.parseFloat(fra_rter_001.getMDfuerzaFluencia2()) +
//                                    Float.parseFloat(fra_rter_001.getMDfuerzaFluencia3()) +
//                                    Float.parseFloat(fra_rter_001.getMDfuerzaFluencia4()) +
//                                    Float.parseFloat(fra_rter_001.getMDfuerzaFluencia5());
//
//        fra_rter_001.setMDpromedioFuerzaFluencia(String.valueOf(MDFuerzaFluencia/5));
//
//        Float MDElongacionRuptura = Float.parseFloat(fra_rter_001.getMDelongacionRuptura1()) +
//                                    Float.parseFloat(fra_rter_001.getMDelongacionRuptura2()) +
//                                    Float.parseFloat(fra_rter_001.getMDelongacionRuptura3()) +
//                                    Float.parseFloat(fra_rter_001.getMDelongacionRuptura4()) +
//                                    Float.parseFloat(fra_rter_001.getMDelongacionRuptura5());
//
//        fra_rter_001.setMDpromedioElongacionRuptura(String.valueOf(MDElongacionRuptura/5));
//
//        Float MDResistenciaTension =    Float.parseFloat(fra_rter_001.getMDresistenciaTension1()) +
//                                        Float.parseFloat(fra_rter_001.getMDresistenciaTension2()) +
//                                        Float.parseFloat(fra_rter_001.getMDresistenciaTension3()) +
//                                        Float.parseFloat(fra_rter_001.getMDresistenciaTension4()) +
//                                        Float.parseFloat(fra_rter_001.getMDresistenciaTension5());
//
//        fra_rter_001.setMDpromedioResistenciaTension(String.valueOf(MDResistenciaTension/5));
//
//        Float MDModuloElastico =    Float.parseFloat(fra_rter_001.getMDmoduloElastico1()) +
//                                    Float.parseFloat(fra_rter_001.getMDmoduloElastico2()) +
//                                    Float.parseFloat(fra_rter_001.getMDmoduloElastico3()) +
//                                    Float.parseFloat(fra_rter_001.getMDmoduloElastico4()) +
//                                    Float.parseFloat(fra_rter_001.getMDmoduloElastico5());
//
//        fra_rter_001.setMDpromedioModuloElastico(String.valueOf(MDModuloElastico/5));
//
//        fra_rter_001.setTDespesor1(request.get("TDespesor1"));
//        fra_rter_001.setTDfuerzaFluencia1(request.get("TDfuerzaFluencia1"));
//        fra_rter_001.setTDelongacionRuptura1(request.get("TDelongacionRuptura1"));
//        fra_rter_001.setTDresistenciaTension1(request.get("TDresistenciaTension1"));
//        fra_rter_001.setTDmoduloElastico1(request.get("TDmoduloElastico1"));
//
//        fra_rter_001.setTDespesor2(request.get("TDespesor2"));
//        fra_rter_001.setTDfuerzaFluencia2(request.get("TDfuerzaFluencia2"));
//        fra_rter_001.setTDelongacionRuptura2(request.get("TDelongacionRuptura2"));
//        fra_rter_001.setTDresistenciaTension2(request.get("TDresistenciaTension2"));
//        fra_rter_001.setTDmoduloElastico2(request.get("TDmoduloElastico2"));
//
//        fra_rter_001.setTDespesor3(request.get("TDespesor3"));
//        fra_rter_001.setTDfuerzaFluencia3(request.get("TDfuerzaFluencia3"));
//        fra_rter_001.setTDelongacionRuptura3(request.get("TDelongacionRuptura3"));
//        fra_rter_001.setTDresistenciaTension3(request.get("TDresistenciaTension3"));
//        fra_rter_001.setTDmoduloElastico3(request.get("TDmoduloElastico3"));
//
//        fra_rter_001.setTDespesor4(request.get("TDespesor4"));
//        fra_rter_001.setTDfuerzaFluencia4(request.get("TDfuerzaFluencia4"));
//        fra_rter_001.setTDelongacionRuptura4(request.get("TDelongacionRuptura4"));
//        fra_rter_001.setTDresistenciaTension4(request.get("TDresistenciaTension4"));
//        fra_rter_001.setTDmoduloElastico4(request.get("TDmoduloElastico4"));
//
//        fra_rter_001.setTDespesor5(request.get("TDespesor5"));
//        fra_rter_001.setTDfuerzaFluencia5(request.get("TDfuerzaFluencia5"));
//        fra_rter_001.setTDelongacionRuptura5(request.get("TDelongacionRuptura5"));
//        fra_rter_001.setTDresistenciaTension5(request.get("TDresistenciaTension5"));
//        fra_rter_001.setTDmoduloElastico5(request.get("TDmoduloElastico5"));
//
//        Float TDEspesor =   Float.parseFloat(fra_rter_001.getTDespesor1()) +
//                Float.parseFloat(fra_rter_001.getTDespesor2()) +
//                Float.parseFloat(fra_rter_001.getTDespesor3()) +
//                Float.parseFloat(fra_rter_001.getTDespesor4()) +
//                Float.parseFloat(fra_rter_001.getTDespesor5());
//
//        fra_rter_001.setTDpromedioEspesor(String.valueOf(TDEspesor/5));
//
//        Float TDFuerzaFluencia =    Float.parseFloat(fra_rter_001.getTDfuerzaFluencia1()) +
//                Float.parseFloat(fra_rter_001.getTDfuerzaFluencia2()) +
//                Float.parseFloat(fra_rter_001.getTDfuerzaFluencia3()) +
//                Float.parseFloat(fra_rter_001.getTDfuerzaFluencia4()) +
//                Float.parseFloat(fra_rter_001.getTDfuerzaFluencia5());
//
//        fra_rter_001.setTDpromedioFuerzaFluencia(String.valueOf(TDFuerzaFluencia/5));
//
//        Float TDElongacionRuptura = Float.parseFloat(fra_rter_001.getTDelongacionRuptura1()) +
//                Float.parseFloat(fra_rter_001.getTDelongacionRuptura2()) +
//                Float.parseFloat(fra_rter_001.getTDelongacionRuptura3()) +
//                Float.parseFloat(fra_rter_001.getTDelongacionRuptura4()) +
//                Float.parseFloat(fra_rter_001.getTDelongacionRuptura5());
//
//        fra_rter_001.setTDpromedioElongacionRuptura(String.valueOf(TDElongacionRuptura/5));
//
//        Float TDResistenciaTension =    Float.parseFloat(fra_rter_001.getTDresistenciaTension1()) +
//                Float.parseFloat(fra_rter_001.getTDresistenciaTension2()) +
//                Float.parseFloat(fra_rter_001.getTDresistenciaTension3()) +
//                Float.parseFloat(fra_rter_001.getTDresistenciaTension4()) +
//                Float.parseFloat(fra_rter_001.getTDresistenciaTension5());
//
//        fra_rter_001.setTDpromedioResistenciaTension(String.valueOf(TDResistenciaTension/5));
//
//        Float TDModuloElastico =    Float.parseFloat(fra_rter_001.getTDmoduloElastico1()) +
//                Float.parseFloat(fra_rter_001.getTDmoduloElastico2()) +
//                Float.parseFloat(fra_rter_001.getTDmoduloElastico3()) +
//                Float.parseFloat(fra_rter_001.getTDmoduloElastico4()) +
//                Float.parseFloat(fra_rter_001.getTDmoduloElastico5());
//
//        fra_rter_001.setTDpromedioModuloElastico(String.valueOf(TDModuloElastico/5));

        fra_rter_001.setObservaciones(request.get("observaciones"));
        fra_rter_001.setRealizo(request.get("realizo"));
        fra_rter_001.setSupervisor(request.get("supervisor"));

        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        fra_rter_001.setMetodoMuestra(metodoMuestra);

        fra_rter_001_service.save(fra_rter_001);

        metodoMuestra.setEstatus("OK");
        metodoMuestraService.save(metodoMuestra);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimirElongacionRuptura/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-RTER-001");
        System.out.println(LocalTime.now());

        return imprimir_fra_rter_001_service.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimirElongacionRuptura2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-RTER-001");
        System.out.println(LocalTime.now());

        return imprimir_fra_rter_001_service.crearFormato(id, 2);
    }
}
