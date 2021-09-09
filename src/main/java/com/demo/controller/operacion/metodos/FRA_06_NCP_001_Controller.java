package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra06ncp.FRA_NCP_001;
import com.demo.model.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_01;
import com.demo.model.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_02;
import com.demo.model.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_03;
import com.demo.model.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_04;
import com.demo.repository.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_01_Repository;
import com.demo.repository.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_02_Repository;
import com.demo.repository.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_03_Repository;
import com.demo.repository.operacion.metodos.fra06ncp.datas.FRA_NCP_001_DATA_04_Repository;
import com.demo.service.formatos.metodos.FRA_06_NCP_Print;
import com.demo.service.formatos.metodos.listas.LFF_MIE_MET_XX_Print;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.metodos.FRA_NCP_001_Service;
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
@RequestMapping(path = "/FRANCP")
public class FRA_06_NCP_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_NCP_001_Service fra_ncp_001_service;

    @Autowired
    private FRA_NCP_001_DATA_01_Repository fra_ncp_001_data_01_repository;

    @Autowired
    private FRA_NCP_001_DATA_02_Repository fra_ncp_001_data_02_repository;

    @Autowired
    private FRA_NCP_001_DATA_03_Repository fra_ncp_001_data_03_repository;

    @Autowired
    private FRA_NCP_001_DATA_04_Repository fra_ncp_001_data_04_repository;

    @Autowired
    private FRA_06_NCP_Print fra_06_ncp_print;

    @Autowired
    private LFF_MIE_MET_XX_Print lff_mie_met_xx_print;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_NCP_001> getAll() {
        return fra_ncp_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_NCP_001 get(@PathVariable("id") Long id) {
        FRA_NCP_001 fra_ncp_001 = fra_ncp_001_service.findById(id);

        if (fra_ncp_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_ncp_001;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/idInterno/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_NCP_001 getInterno(@PathVariable("id") String id) {
        FRA_NCP_001 fra_ncp_001 = fra_ncp_001_service.findByIdInternoMuestra(id);

        if (fra_ncp_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_ncp_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestPart("francp") Map<String, String> request,
                                    @RequestPart("imagen") MultipartFile file,
                                    @RequestPart("imagen2") MultipartFile file2,
                                    @RequestPart("signature") MultipartFile signature,
                                    Principal principal) {

        FRA_NCP_001 fra_ncp_001 = new FRA_NCP_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        SaveInServer saveInServer = new SaveInServer();

        try {
            fra_ncp_001.setFolioTecnica(request.get("folioTecnica"));
            fra_ncp_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
            fra_ncp_001.setIdInternoMuestra(request.get("idInternoMuestra"));
            fra_ncp_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_ncp_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_ncp_001.setTemperatura(request.get("temperatura"));
            fra_ncp_001.setHumedadRelativa(request.get("humedadRelativa"));

            fra_ncp_001.setCodigoMicrometro(request.get("codigoMicrometro"));
            fra_ncp_001.setCodigoParrillaCalentamiento(request.get("codigoParrillaCalentamiento"));
            fra_ncp_001.setCodigoMicroscopio(request.get("codigoMicroscopio"));
            fra_ncp_001.setLente(request.get("lente"));

            fra_ncp_001.setObservaciones(request.get("observaciones"));
            fra_ncp_001.setRealizo(request.get("realizo"));
            fra_ncp_001.setSupervisor(request.get("supervisor"));

            fra_ncp_001.setMetodoMuestra(metodoMuestra);

            fra_ncp_001_service.save(fra_ncp_001);

            Double espesorTotalPromedio1 = 0.0, espesorTotalPromedio2 = 0.0;

            for (int i = 1; i < 4; i++) {
                FRA_NCP_001_DATA_01 fra_ncp_001_data_01 = new FRA_NCP_001_DATA_01();
                FRA_NCP_001_DATA_02 fra_ncp_001_data_02 = new FRA_NCP_001_DATA_02();

                fra_ncp_001_data_01.setEspesorTotalMicrometro(request.get("1espesorTotalMicrometro" + i));
                fra_ncp_001_data_02.setEspesorTotalMicrometro(request.get("2espesorTotalMicrometro" + i));

                fra_ncp_001_data_01.setFra_ncp_001(fra_ncp_001);
                fra_ncp_001_data_02.setFra_ncp_001(fra_ncp_001);

                fra_ncp_001_data_01_repository.save(fra_ncp_001_data_01);
                fra_ncp_001_data_02_repository.save(fra_ncp_001_data_02);

                espesorTotalPromedio1 = espesorTotalPromedio1 + Double.parseDouble(fra_ncp_001_data_01.getEspesorTotalMicrometro());
                espesorTotalPromedio2 = espesorTotalPromedio2 + Double.parseDouble(fra_ncp_001_data_02.getEspesorTotalMicrometro());
            }

            fra_ncp_001.setEspesorTotalPromedioMM1(String.format("%.3f", (espesorTotalPromedio1 / 3)));
            fra_ncp_001.setEspesorTotalPromedioMM2(String.format("%.3f", (espesorTotalPromedio2 / 3)));

            fra_ncp_001.setEspesorTotalPromedioUM1((Double.parseDouble(fra_ncp_001.getEspesorTotalPromedioMM1()) * 1000) + "");
            fra_ncp_001.setEspesorTotalPromedioUM2((Double.parseDouble(fra_ncp_001.getEspesorTotalPromedioMM2()) * 1000) + "");

            /**/

            fra_ncp_001.setNumeroTotalCapas1(request.get("numeroTotalCapas1"));
            fra_ncp_001.setNumeroTotalCapas2(request.get("numeroTotalCapas2"));

            Double sumatoria1 = 0.0, sumatoria2 = 0.0;

            for (int i = 1; i <= Integer.parseInt(fra_ncp_001.getNumeroTotalCapas1()); i++) {
                FRA_NCP_001_DATA_03 fra_ncp_001_data_03 = new FRA_NCP_001_DATA_03();

                fra_ncp_001_data_03.setEspesorPorMicroscopia(request.get("1espesorPorMicroscopia" + i));

                fra_ncp_001_data_03.setFra_ncp_001(fra_ncp_001);

                sumatoria1 = sumatoria1 + Double.parseDouble(fra_ncp_001_data_03.getEspesorPorMicroscopia());

                fra_ncp_001_data_03_repository.save(fra_ncp_001_data_03);
            }

            for (int i = 1; i <= Integer.parseInt(fra_ncp_001.getNumeroTotalCapas2()); i++) {
                FRA_NCP_001_DATA_04 fra_ncp_001_data_04 = new FRA_NCP_001_DATA_04();

                fra_ncp_001_data_04.setEspesorPorMicroscopia(request.get("2espesorPorMicroscopia" + i));

                fra_ncp_001_data_04.setFra_ncp_001(fra_ncp_001);

                sumatoria2 = sumatoria2 + Double.parseDouble(fra_ncp_001_data_04.getEspesorPorMicroscopia());

                fra_ncp_001_data_04_repository.save(fra_ncp_001_data_04);
            }

            fra_ncp_001.setEspesorTotalMicroscopia1(String.format("%.3f", sumatoria1));
            fra_ncp_001.setEspesorTotalMicroscopia2(String.format("%.3f", sumatoria2));

            fra_ncp_001.setPathImagen(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.RUTA_IMG_06_NCP + saveInServer.SaveInServer(file, Constantes.RUTA_IMG_06_NCP));
            fra_ncp_001.setPathImagen2(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.RUTA_IMG_06_NCP + saveInServer.SaveInServer(file2, Constantes.RUTA_IMG_06_NCP));
            fra_ncp_001.setRubricaRealizo(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_REALIZO_NCP + saveInServer.SaveInServer(signature, Constantes.SIGNATURE_REALIZO_NCP));

            fra_ncp_001.setEstatus("FINALIZADO");
            fra_ncp_001.setCantidadModificaciones("3");
            fra_ncp_001.setMuestraEnReporte(request.get("muestraEnReporte"));

            fra_ncp_001_service.save(fra_ncp_001);

            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_NCP a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_06_ncp_print.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_NCP a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_06_ncp_print.crearFormato(id, 2);
    }

    @RequestMapping(value = "/imprimir3", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir3() throws Exception {

        return lff_mie_met_xx_print.crearListaFolios("06-LFF-MIE-MET-NCP-001", 36L);
    }
}
