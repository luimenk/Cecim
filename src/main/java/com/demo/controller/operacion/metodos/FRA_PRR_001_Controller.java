package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.FRA_CST_001;
import com.demo.model.operacion.metodos.FRA_PRR_001;
import com.demo.service.ClientService;
import com.demo.service.FoliosService;
import com.demo.service.MethodService;
import com.demo.service.QR.QRService;
import com.demo.service.formatos.FEIM_SOC_005_Service;
import com.demo.service.formatos.FSS_SOC_001_Service;
import com.demo.service.formatos.metodos.IMPRIMIR_FRA_CST_001_Service;
import com.demo.service.formatos.metodos.IMPRIMIR_FRA_DI_001_Service;
import com.demo.service.formatos.metodos.IMPRIMIR_FRA_PRR_001_Service;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.RecepcionVerificacionRegistroCodificacionService;
import com.demo.service.operacion.SolicitudServicioClienteMuestrasService;
import com.demo.service.operacion.SolicitudServicioClienteService;
import com.demo.service.operacion.metodos.FRA_CST_001_Service;
import com.demo.service.operacion.metodos.FRA_DI_001_Service;
import com.demo.service.operacion.metodos.FRA_PRR_001_Service;
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
@RequestMapping(path = "/resistenciaRasgado")
public class FRA_PRR_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_PRR_001_Service fra_prr_001_service;

    @Autowired
    private IMPRIMIR_FRA_PRR_001_Service imprimir_fra_prr_001_service;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_PRR_001> getAll() {
        return fra_prr_001_service.findAll();
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

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {
        APP.debug("Apartado de registro nuevo" + calendario.getTime());

        FRA_PRR_001 fra_prr_001 = new FRA_PRR_001();

        fra_prr_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_prr_001.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_prr_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_prr_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));

        fra_prr_001.setTemperatura(request.get("temperatura"));
        fra_prr_001.setHumedadRelativa(request.get("humedadRelativa"));
        fra_prr_001.setCodigoPendulo(request.get("codigoPendulo"));
        fra_prr_001.setCodigoManometro(request.get("codigoManometro"));
        fra_prr_001.setPrensaEnsayo(request.get("prensaEnsayo"));

        fra_prr_001.setTemperatura(request.get("temperatura"));
        fra_prr_001.setHumedadRelativa(request.get("humedadRelativa"));
        fra_prr_001.setCodigoPendulo(request.get("codigoPendulo"));
        fra_prr_001.setCodigoManometro(request.get("codigoManometro"));
        fra_prr_001.setPrensaEnsayo(request.get("prensaEnsayo"));

        // *** APARTADO MD ***
        fra_prr_001.setMDespesor11(request.get("MDespesor11"));
        fra_prr_001.setMDespesor12(request.get("MDespesor12"));
        fra_prr_001.setMDespesor13(request.get("MDespesor13"));

        fra_prr_001.setMDespesor21(request.get("MDespesor21"));
        fra_prr_001.setMDespesor22(request.get("MDespesor22"));
        fra_prr_001.setMDespesor23(request.get("MDespesor23"));

        fra_prr_001.setMDespesor31(request.get("MDespesor31"));
        fra_prr_001.setMDespesor32(request.get("MDespesor32"));
        fra_prr_001.setMDespesor33(request.get("MDespesor33"));

        fra_prr_001.setMDespesor41(request.get("MDespesor41"));
        fra_prr_001.setMDespesor42(request.get("MDespesor42"));
        fra_prr_001.setMDespesor43(request.get("MDespesor43"));

        fra_prr_001.setMDespesor51(request.get("MDespesor51"));
        fra_prr_001.setMDespesor52(request.get("MDespesor52"));
        fra_prr_001.setMDespesor53(request.get("MDespesor53"));

        fra_prr_001.setMDespesor61(request.get("MDespesor61"));
        fra_prr_001.setMDespesor62(request.get("MDespesor62"));
        fra_prr_001.setMDespesor63(request.get("MDespesor63"));

        fra_prr_001.setMDespesor71(request.get("MDespesor71"));
        fra_prr_001.setMDespesor72(request.get("MDespesor72"));
        fra_prr_001.setMDespesor73(request.get("MDespesor73"));

        fra_prr_001.setMDespesor81(request.get("MDespesor81"));
        fra_prr_001.setMDespesor82(request.get("MDespesor82"));
        fra_prr_001.setMDespesor83(request.get("MDespesor83"));

        fra_prr_001.setMDespesor91(request.get("MDespesor91"));
        fra_prr_001.setMDespesor92(request.get("MDespesor92"));
        fra_prr_001.setMDespesor93(request.get("MDespesor93"));

        fra_prr_001.setMDespesor101(request.get("MDespesor101"));
        fra_prr_001.setMDespesor102(request.get("MDespesor102"));
        fra_prr_001.setMDespesor103(request.get("MDespesor103"));

        fra_prr_001.setMDResistenciaRasgado1(request.get("MDResistenciaRasgado1"));
        fra_prr_001.setMDResistenciaRasgado2(request.get("MDResistenciaRasgado2"));
        fra_prr_001.setMDResistenciaRasgado3(request.get("MDResistenciaRasgado3"));
        fra_prr_001.setMDResistenciaRasgado4(request.get("MDResistenciaRasgado4"));
        fra_prr_001.setMDResistenciaRasgado5(request.get("MDResistenciaRasgado5"));
        fra_prr_001.setMDResistenciaRasgado6(request.get("MDResistenciaRasgado6"));
        fra_prr_001.setMDResistenciaRasgado7(request.get("MDResistenciaRasgado7"));
        fra_prr_001.setMDResistenciaRasgado8(request.get("MDResistenciaRasgado8"));
        fra_prr_001.setMDResistenciaRasgado9(request.get("MDResistenciaRasgado9"));
        fra_prr_001.setMDResistenciaRasgado10(request.get("MDResistenciaRasgado10"));

        Float MDEspesor1 = Float.parseFloat(fra_prr_001.getMDespesor11()) +
                Float.parseFloat(fra_prr_001.getMDespesor12()) +
                Float.parseFloat(fra_prr_001.getMDespesor13());

        fra_prr_001.setMDEspesorPromedio1(String.valueOf(MDEspesor1 / 3));

        Float MDEspesor2 = Float.parseFloat(fra_prr_001.getMDespesor21()) +
                Float.parseFloat(fra_prr_001.getMDespesor22()) +
                Float.parseFloat(fra_prr_001.getMDespesor23());

        fra_prr_001.setMDEspesorPromedio2(String.valueOf(MDEspesor2 / 3));

        Float MDEspesor3 = Float.parseFloat(fra_prr_001.getMDespesor31()) +
                Float.parseFloat(fra_prr_001.getMDespesor32()) +
                Float.parseFloat(fra_prr_001.getMDespesor33());

        fra_prr_001.setMDEspesorPromedio3(String.valueOf(MDEspesor3 / 3));

        Float MDEspesor4 = Float.parseFloat(fra_prr_001.getMDespesor41()) +
                Float.parseFloat(fra_prr_001.getMDespesor42()) +
                Float.parseFloat(fra_prr_001.getMDespesor43());

        fra_prr_001.setMDEspesorPromedio4(String.valueOf(MDEspesor4 / 3));

        Float MDEspesor5 = Float.parseFloat(fra_prr_001.getMDespesor51()) +
                Float.parseFloat(fra_prr_001.getMDespesor52()) +
                Float.parseFloat(fra_prr_001.getMDespesor53());

        fra_prr_001.setMDEspesorPromedio5(String.valueOf(MDEspesor5 / 3));

        Float MDEspesor6 = Float.parseFloat(fra_prr_001.getMDespesor61()) +
                Float.parseFloat(fra_prr_001.getMDespesor62()) +
                Float.parseFloat(fra_prr_001.getMDespesor63());

        fra_prr_001.setMDEspesorPromedio6(String.valueOf(MDEspesor6 / 3));

        Float MDEspesor7 = Float.parseFloat(fra_prr_001.getMDespesor71()) +
                Float.parseFloat(fra_prr_001.getMDespesor72()) +
                Float.parseFloat(fra_prr_001.getMDespesor73());

        fra_prr_001.setMDEspesorPromedio7(String.valueOf(MDEspesor7 / 3));

        Float MDEspesor8 = Float.parseFloat(fra_prr_001.getMDespesor81()) +
                Float.parseFloat(fra_prr_001.getMDespesor82()) +
                Float.parseFloat(fra_prr_001.getMDespesor83());

        fra_prr_001.setMDEspesorPromedio8(String.valueOf(MDEspesor8 / 3));

        Float MDEspesor9 = Float.parseFloat(fra_prr_001.getMDespesor91()) +
                Float.parseFloat(fra_prr_001.getMDespesor92()) +
                Float.parseFloat(fra_prr_001.getMDespesor93());

        fra_prr_001.setMDEspesorPromedio9(String.valueOf(MDEspesor9 / 3));

        Float MDEspesor10 = Float.parseFloat(fra_prr_001.getMDespesor101()) +
                Float.parseFloat(fra_prr_001.getMDespesor102()) +
                Float.parseFloat(fra_prr_001.getMDespesor103());

        fra_prr_001.setMDEspesorPromedio10(String.valueOf(MDEspesor10 / 3));

        Float MDPromedio = Float.parseFloat(fra_prr_001.getMDResistenciaRasgado1()) +
                Float.parseFloat(fra_prr_001.getMDResistenciaRasgado2()) +
                Float.parseFloat(fra_prr_001.getMDResistenciaRasgado3()) +
                Float.parseFloat(fra_prr_001.getMDResistenciaRasgado4()) +
                Float.parseFloat(fra_prr_001.getMDResistenciaRasgado5()) +
                Float.parseFloat(fra_prr_001.getMDResistenciaRasgado6()) +
                Float.parseFloat(fra_prr_001.getMDResistenciaRasgado7()) +
                Float.parseFloat(fra_prr_001.getMDResistenciaRasgado8()) +
                Float.parseFloat(fra_prr_001.getMDResistenciaRasgado9()) +
                Float.parseFloat(fra_prr_001.getMDResistenciaRasgado10());

        fra_prr_001.setMDPromedio(String.valueOf(MDPromedio / 10));

        // *** APARTADO TD ***
        fra_prr_001.setTDespesor11(request.get("TDespesor11"));
        fra_prr_001.setTDespesor12(request.get("TDespesor12"));
        fra_prr_001.setTDespesor13(request.get("TDespesor13"));

        fra_prr_001.setTDespesor21(request.get("TDespesor21"));
        fra_prr_001.setTDespesor22(request.get("TDespesor22"));
        fra_prr_001.setTDespesor23(request.get("TDespesor23"));

        fra_prr_001.setTDespesor31(request.get("TDespesor31"));
        fra_prr_001.setTDespesor32(request.get("TDespesor32"));
        fra_prr_001.setTDespesor33(request.get("TDespesor33"));

        fra_prr_001.setTDespesor41(request.get("TDespesor41"));
        fra_prr_001.setTDespesor42(request.get("TDespesor42"));
        fra_prr_001.setTDespesor43(request.get("TDespesor43"));

        fra_prr_001.setTDespesor51(request.get("TDespesor51"));
        fra_prr_001.setTDespesor52(request.get("TDespesor52"));
        fra_prr_001.setTDespesor53(request.get("TDespesor53"));

        fra_prr_001.setTDespesor61(request.get("TDespesor61"));
        fra_prr_001.setTDespesor62(request.get("TDespesor62"));
        fra_prr_001.setTDespesor63(request.get("TDespesor63"));

        fra_prr_001.setTDespesor71(request.get("TDespesor71"));
        fra_prr_001.setTDespesor72(request.get("TDespesor72"));
        fra_prr_001.setTDespesor73(request.get("TDespesor73"));

        fra_prr_001.setTDespesor81(request.get("TDespesor81"));
        fra_prr_001.setTDespesor82(request.get("TDespesor82"));
        fra_prr_001.setTDespesor83(request.get("TDespesor83"));

        fra_prr_001.setTDespesor91(request.get("TDespesor91"));
        fra_prr_001.setTDespesor92(request.get("TDespesor92"));
        fra_prr_001.setTDespesor93(request.get("TDespesor93"));

        fra_prr_001.setTDespesor101(request.get("TDespesor101"));
        fra_prr_001.setTDespesor102(request.get("TDespesor102"));
        fra_prr_001.setTDespesor103(request.get("TDespesor103"));

        fra_prr_001.setTDResistenciaRasgado1(request.get("TDResistenciaRasgado1"));
        fra_prr_001.setTDResistenciaRasgado2(request.get("TDResistenciaRasgado2"));
        fra_prr_001.setTDResistenciaRasgado3(request.get("TDResistenciaRasgado3"));
        fra_prr_001.setTDResistenciaRasgado4(request.get("TDResistenciaRasgado4"));
        fra_prr_001.setTDResistenciaRasgado5(request.get("TDResistenciaRasgado5"));
        fra_prr_001.setTDResistenciaRasgado6(request.get("TDResistenciaRasgado6"));
        fra_prr_001.setTDResistenciaRasgado7(request.get("TDResistenciaRasgado7"));
        fra_prr_001.setTDResistenciaRasgado8(request.get("TDResistenciaRasgado8"));
        fra_prr_001.setTDResistenciaRasgado9(request.get("TDResistenciaRasgado9"));
        fra_prr_001.setTDResistenciaRasgado10(request.get("TDResistenciaRasgado10"));

        Float TDEspesor1 = Float.parseFloat(fra_prr_001.getTDespesor11()) +
                Float.parseFloat(fra_prr_001.getTDespesor12()) +
                Float.parseFloat(fra_prr_001.getTDespesor13());

        fra_prr_001.setTDEspesorPromedio1(String.valueOf(TDEspesor1 / 3));

        Float TDEspesor2 = Float.parseFloat(fra_prr_001.getTDespesor21()) +
                Float.parseFloat(fra_prr_001.getTDespesor22()) +
                Float.parseFloat(fra_prr_001.getTDespesor23());

        fra_prr_001.setTDEspesorPromedio2(String.valueOf(TDEspesor2 / 3));

        Float TDEspesor3 = Float.parseFloat(fra_prr_001.getTDespesor31()) +
                Float.parseFloat(fra_prr_001.getTDespesor32()) +
                Float.parseFloat(fra_prr_001.getTDespesor33());

        fra_prr_001.setTDEspesorPromedio3(String.valueOf(TDEspesor3 / 3));

        Float TDEspesor4 = Float.parseFloat(fra_prr_001.getTDespesor41()) +
                Float.parseFloat(fra_prr_001.getTDespesor42()) +
                Float.parseFloat(fra_prr_001.getTDespesor43());

        fra_prr_001.setTDEspesorPromedio4(String.valueOf(TDEspesor4 / 3));

        Float TDEspesor5 = Float.parseFloat(fra_prr_001.getTDespesor51()) +
                Float.parseFloat(fra_prr_001.getTDespesor52()) +
                Float.parseFloat(fra_prr_001.getTDespesor53());

        fra_prr_001.setTDEspesorPromedio5(String.valueOf(TDEspesor5 / 3));

        Float TDEspesor6 = Float.parseFloat(fra_prr_001.getTDespesor61()) +
                Float.parseFloat(fra_prr_001.getTDespesor62()) +
                Float.parseFloat(fra_prr_001.getTDespesor63());

        fra_prr_001.setTDEspesorPromedio6(String.valueOf(TDEspesor6 / 3));

        Float TDEspesor7 = Float.parseFloat(fra_prr_001.getTDespesor71()) +
                Float.parseFloat(fra_prr_001.getTDespesor72()) +
                Float.parseFloat(fra_prr_001.getTDespesor73());

        fra_prr_001.setTDEspesorPromedio7(String.valueOf(TDEspesor7 / 3));

        Float TDEspesor8 = Float.parseFloat(fra_prr_001.getTDespesor81()) +
                Float.parseFloat(fra_prr_001.getTDespesor82()) +
                Float.parseFloat(fra_prr_001.getTDespesor83());

        fra_prr_001.setTDEspesorPromedio8(String.valueOf(TDEspesor8 / 3));

        Float TDEspesor9 = Float.parseFloat(fra_prr_001.getTDespesor91()) +
                Float.parseFloat(fra_prr_001.getTDespesor92()) +
                Float.parseFloat(fra_prr_001.getTDespesor93());

        fra_prr_001.setTDEspesorPromedio9(String.valueOf(TDEspesor9 / 3));

        Float TDEspesor10 = Float.parseFloat(fra_prr_001.getTDespesor101()) +
                Float.parseFloat(fra_prr_001.getTDespesor102()) +
                Float.parseFloat(fra_prr_001.getTDespesor103());

        fra_prr_001.setTDEspesorPromedio10(String.valueOf(TDEspesor10 / 3));

        Float TDPromedio = Float.parseFloat(fra_prr_001.getTDResistenciaRasgado1()) +
                Float.parseFloat(fra_prr_001.getTDResistenciaRasgado2()) +
                Float.parseFloat(fra_prr_001.getTDResistenciaRasgado3()) +
                Float.parseFloat(fra_prr_001.getTDResistenciaRasgado4()) +
                Float.parseFloat(fra_prr_001.getTDResistenciaRasgado5()) +
                Float.parseFloat(fra_prr_001.getTDResistenciaRasgado6()) +
                Float.parseFloat(fra_prr_001.getTDResistenciaRasgado7()) +
                Float.parseFloat(fra_prr_001.getTDResistenciaRasgado8()) +
                Float.parseFloat(fra_prr_001.getTDResistenciaRasgado9()) +
                Float.parseFloat(fra_prr_001.getTDResistenciaRasgado10());

        fra_prr_001.setTDPromedio(String.valueOf(TDPromedio / 10));


        Double[] valoresMD = {Double.parseDouble(fra_prr_001.getMDResistenciaRasgado1()),
                Double.parseDouble(fra_prr_001.getMDResistenciaRasgado2()),
                Double.parseDouble(fra_prr_001.getMDResistenciaRasgado3()),
                Double.parseDouble(fra_prr_001.getMDResistenciaRasgado4()),
                Double.parseDouble(fra_prr_001.getMDResistenciaRasgado5()),
                Double.parseDouble(fra_prr_001.getMDResistenciaRasgado6()),
                Double.parseDouble(fra_prr_001.getMDResistenciaRasgado7()),
                Double.parseDouble(fra_prr_001.getMDResistenciaRasgado8()),
                Double.parseDouble(fra_prr_001.getMDResistenciaRasgado9()),
                Double.parseDouble(fra_prr_001.getMDResistenciaRasgado10())};
        Double[] valoresTD = {Double.parseDouble(fra_prr_001.getTDResistenciaRasgado1()),
                Double.parseDouble(fra_prr_001.getTDResistenciaRasgado2()),
                Double.parseDouble(fra_prr_001.getTDResistenciaRasgado3()),
                Double.parseDouble(fra_prr_001.getTDResistenciaRasgado4()),
                Double.parseDouble(fra_prr_001.getTDResistenciaRasgado5()),
                Double.parseDouble(fra_prr_001.getTDResistenciaRasgado6()),
                Double.parseDouble(fra_prr_001.getTDResistenciaRasgado7()),
                Double.parseDouble(fra_prr_001.getTDResistenciaRasgado8()),
                Double.parseDouble(fra_prr_001.getTDResistenciaRasgado9()),
                Double.parseDouble(fra_prr_001.getTDResistenciaRasgado10())};
        Double minMD = valoresMD[0];
        Double maxMD = valoresMD[0];
        Double minTD = valoresTD[0];
        Double maxTD = valoresTD[0];

        for (Double valoresLargo : valoresMD) {
            if (maxMD < valoresLargo) {
                maxMD = valoresLargo;
            }
            if (minMD > valoresLargo) {
                minMD = valoresLargo;
            }
        }

        for (Double valoresAncho : valoresTD) {
            if (maxTD < valoresAncho) {
                maxTD = valoresAncho;
            }
            if (minTD > valoresAncho) {
                minTD = valoresAncho;
            }
        }

        Double desviacionEstandarMD = Math.sqrt((
                                      Math.pow(Double.parseDouble(fra_prr_001.getMDResistenciaRasgado1()) - MDPromedio, 2) +
                                      Math.pow(Double.parseDouble(fra_prr_001.getMDResistenciaRasgado2()) - MDPromedio, 2) +
                                      Math.pow(Double.parseDouble(fra_prr_001.getMDResistenciaRasgado3()) - MDPromedio, 2) +
                                      Math.pow(Double.parseDouble(fra_prr_001.getMDResistenciaRasgado4()) - MDPromedio, 2) +
                                      Math.pow(Double.parseDouble(fra_prr_001.getMDResistenciaRasgado5()) - MDPromedio, 2) +
                                      Math.pow(Double.parseDouble(fra_prr_001.getMDResistenciaRasgado6()) - MDPromedio, 2) +
                                      Math.pow(Double.parseDouble(fra_prr_001.getMDResistenciaRasgado7()) - MDPromedio, 2) +
                                      Math.pow(Double.parseDouble(fra_prr_001.getMDResistenciaRasgado8()) - MDPromedio, 2) +
                                      Math.pow(Double.parseDouble(fra_prr_001.getMDResistenciaRasgado9()) - MDPromedio, 2) +
                                      Math.pow(Double.parseDouble(fra_prr_001.getMDResistenciaRasgado10()) - MDPromedio, 2)) / 10);

        Double desviacionEstandarTD = Math.sqrt((
                                      Math.pow(Double.parseDouble(fra_prr_001.getTDResistenciaRasgado1()) - TDPromedio, 2) +
                                      Math.pow(Double.parseDouble(fra_prr_001.getTDResistenciaRasgado2()) - TDPromedio, 2) +
                                      Math.pow(Double.parseDouble(fra_prr_001.getTDResistenciaRasgado3()) - TDPromedio, 2) +
                                      Math.pow(Double.parseDouble(fra_prr_001.getTDResistenciaRasgado4()) - TDPromedio, 2) +
                                      Math.pow(Double.parseDouble(fra_prr_001.getTDResistenciaRasgado5()) - TDPromedio, 2) +
                                      Math.pow(Double.parseDouble(fra_prr_001.getTDResistenciaRasgado6()) - TDPromedio, 2) +
                                      Math.pow(Double.parseDouble(fra_prr_001.getTDResistenciaRasgado7()) - TDPromedio, 2) +
                                      Math.pow(Double.parseDouble(fra_prr_001.getTDResistenciaRasgado8()) - TDPromedio, 2) +
                                      Math.pow(Double.parseDouble(fra_prr_001.getTDResistenciaRasgado9()) - TDPromedio, 2) +
                                      Math.pow(Double.parseDouble(fra_prr_001.getTDResistenciaRasgado10()) -TDPromedio, 2)) / 10);

        fra_prr_001.setMaxMD(String.valueOf(maxMD));
        fra_prr_001.setMaxTD(String.valueOf(maxTD));
        fra_prr_001.setMinMD(String.valueOf(minMD));
        fra_prr_001.setMinTD(String.valueOf(minTD));

        fra_prr_001.setDesvEstandarMD(String.valueOf(desviacionEstandarMD));
        fra_prr_001.setDesvEstandarTD(String.valueOf(desviacionEstandarTD));


        fra_prr_001.setObservaciones(request.get("observaciones"));
        fra_prr_001.setRealizo(request.get("realizo"));
        fra_prr_001.setSupervisor(request.get("supervisor"));

        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        fra_prr_001.setMetodoMuestra(metodoMuestra);

        fra_prr_001_service.save(fra_prr_001);
        metodoMuestra.setEstatus("OK");
        metodoMuestraService.save(metodoMuestra);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-PRR-001");
        System.out.println(LocalTime.now());

        return fra_prr_001_service.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-PRR-001");
        System.out.println(LocalTime.now());

        return fra_prr_001_service.crearFormato(id, 2);
    }
}
