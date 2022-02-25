package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra15dsc.FRA_DSC;
import com.demo.model.operacion.metodos.fra17if.FRA_IF_001;
import com.demo.model.operacion.metodos.fra17if.datas.FRA_IF_001_DATA;
import com.demo.repository.operacion.metodos.fra17if.datas.FRA_IF_001_DATA_Repository;
import com.demo.service.formatos.metodos.FRA_17_IF_Print;
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
@RequestMapping(path = "/FRAIF")
public class FRA_17_IF_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_IF_001_Service fra_if_001_service;

    @Autowired
    private FRA_IF_001_DATA_Repository fra_if_001_data_repository;

    @Autowired
    private FRA_17_IF_Print fra_17_if_print;

    @Autowired
    private LFF_MIE_MET_XX_Print lff_mie_met_xx_print;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_IF_001> getAll() {
        return fra_if_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_IF_001 get(@PathVariable("id") Long id) {
        FRA_IF_001 fra_if_001 = fra_if_001_service.findById(id);

        if (fra_if_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_if_001;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/idInterno/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_IF_001 getInterno(@PathVariable("id") String id) {
        FRA_IF_001 fra_if_001 = fra_if_001_service.findByIdInternoMuestra(id);

        if (fra_if_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_if_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestPart("fraif") Map<String, String> request,
                                    //@RequestPart("imagen") MultipartFile file,
                                    @RequestPart("signature") MultipartFile signature,
                                    Principal principal) {

        FRA_IF_001 fra_if_001 = new FRA_IF_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        SaveInServer saveInServer = new SaveInServer();
        try{
            fra_if_001.setFolioTecnica(request.get("folioTecnica"));
            fra_if_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
            fra_if_001.setIdInternoMuestra(request.get("idInternoMuestra"));
            fra_if_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_if_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_if_001.setTemperatura(request.get("temperatura"));
            fra_if_001.setHumedadRelativa(request.get("humedadRelativa"));
            
            fra_if_001.setCodigoBalanzaAnalitica(request.get("codigoBalanzaAnalitica"));
            fra_if_001.setCodigoPlastometro(request.get("codigoPlastometro"));
            fra_if_001.setTemperaturaEnsayo(request.get("temperaturaEnsayo"));
            fra_if_001.setPesaEnsayo(request.get("pesaEnsayo"));
            fra_if_001.setTiempoCorte(request.get("tiempoCorte"));

            fra_if_001.setTipoMaterial(request.get("tipoMaterial"));
            fra_if_001.setFormaFisicaMaterial(request.get("formaFisicaMaterial"));
            fra_if_001.setIndiceFuidez(request.get("indiceFuidez"));

            fra_if_001.setObservaciones(request.get("observaciones"));
            fra_if_001.setRealizo(request.get("realizo"));
            fra_if_001.setSupervisor(request.get("supervisor"));

            fra_if_001.setMetodoMuestra(metodoMuestra);

            fra_if_001_service.save(fra_if_001);

            List<String> listaIndices = new ArrayList<>();
            listaIndices.add("0.15 a 1.0");
            listaIndices.add(">1.0 a 3.5");
            listaIndices.add(">3.5 a 10");
            listaIndices.add(">10 a 25");
            listaIndices.add(">25");
            List<String> listaFactores = new ArrayList<>();
            listaFactores.add("1.67");
            listaFactores.add("3.33");
            listaFactores.add("10.00");
            listaFactores.add("20.00");
            listaFactores.add("40.00");

            double promedioW = 0.0, promedioMFI = 0.0, promedioMFI2 = 0.0;

            for (int i=0; i<4; i++) {
                double mfi = 0.0;
                FRA_IF_001_DATA fra_if_001_data = new FRA_IF_001_DATA();
                fra_if_001_data.setPesoFilamento(request.get("pesoFilamento" + i));
                fra_if_001_data.setIndiceFluidez(request.get("indiceFluidez" + i));
                if (fra_if_001.getIndiceFuidez().equals("No especificado")) {
                    fra_if_001_data.setMfi(request.get("mfi" + i));
                } else {
                    for (int j=0; j< listaIndices.size(); j++){
                        if (fra_if_001.getIndiceFuidez().equals(listaIndices.get(j))) {
                            mfi = Double.parseDouble(fra_if_001_data.getPesoFilamento()) * Double.parseDouble(listaFactores.get(j));
                            fra_if_001_data.setMfi(String.format("%.4f",mfi));
                        }
                    }
                }
                fra_if_001_data.setFra_if_001(fra_if_001);

                promedioW = promedioW + Double.parseDouble(fra_if_001_data.getPesoFilamento());
                promedioMFI = promedioMFI + Double.parseDouble(fra_if_001_data.getIndiceFluidez());
                promedioMFI2 = promedioMFI2 + Double.parseDouble(fra_if_001_data.getMfi());

                fra_if_001_data_repository.save(fra_if_001_data);
            }

            fra_if_001.setPromedioPesoFilamento(String.format("%.2f",(promedioW/4)));
            fra_if_001.setPromedioIndiceFluidez(String.format("%.2f",(promedioMFI/4)));
            fra_if_001.setPromedioMFI(String.format("%.2f",(promedioMFI2/4)));

            //fra_if_001.setPathImagen(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.RUTA_IMG_15_DSC + saveInServer.SaveInServer(file, Constantes.RUTA_IMG_15_DSC));
            fra_if_001.setRubricaRealizo(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_REALIZO_IF + saveInServer.SaveInServer(signature, Constantes.SIGNATURE_REALIZO_IF));

            fra_if_001.setEstatus("FINALIZADO");
            fra_if_001.setCantidadModificaciones("3");

            fra_if_001_service.save(fra_if_001);

            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_IF a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_17_if_print.crearFormato(id,1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_IF a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_17_if_print.crearFormato(id,2);
    }

    @RequestMapping(value = "/imprimir3", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir3() throws Exception {

        return lff_mie_met_xx_print.crearListaFolios("17-LFF-MIE-MET-IF-001", 65L);
    }
}
