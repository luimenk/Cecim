package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra18tto.FRA_TTO_001;
import com.demo.model.operacion.metodos.fra18tto.datas.FRA_TTO_001_DATA;
import com.demo.repository.operacion.metodos.fra18tto.FRA_TTO_001_Repository;
import com.demo.repository.operacion.metodos.fra18tto.datas.FRA_TTO_001_DATA_Repository;
import com.demo.service.formatos.metodos.FRA_18_TTO_Print;
import com.demo.service.formatos.metodos.listas.LFF_MIE_MET_XX_Print;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.utils.Constantes;
import com.demo.utils.SaveInServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.Principal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/FRATTO")
public class FRA_18_TTO_001_Controller {

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_TTO_001_Repository fra_tto_001_repository;

    @Autowired
    private FRA_TTO_001_DATA_Repository fra_tto_001_data_repository;

    @Autowired
    private FRA_18_TTO_Print fra_18_tto_print;

    @Autowired
    private LFF_MIE_MET_XX_Print lff_mie_met_xx_print;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_TTO_001> getAll() {
        return fra_tto_001_repository.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_TTO_001 get(@PathVariable("id") Long id) {
        FRA_TTO_001 fra_tto_001 = fra_tto_001_repository.findByIdFRATTO(id);

        if (fra_tto_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_tto_001;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/idInterno/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_TTO_001 getInterno(@PathVariable("id") String id) {
        FRA_TTO_001 fra_tto_001 = fra_tto_001_repository.findByIdInternoMuestra(id);

        if (fra_tto_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_tto_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestPart("fratto") Map<String, String> request,
                                    @RequestPart("imagen") MultipartFile file,
                                    @RequestPart("signature") MultipartFile signature,
                                    Principal principal) {

        FRA_TTO_001 fra_tto_001 = new FRA_TTO_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        SaveInServer saveInServer = new SaveInServer();

        try {
            fra_tto_001.setFolioTecnica(request.get("folioTecnica"));
            fra_tto_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
            fra_tto_001.setIdInternoMuestra(request.get("idInternoMuestra"));
            fra_tto_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_tto_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_tto_001.setTemperatura(request.get("temperatura"));
            fra_tto_001.setHumedadRelativa(request.get("humedadRelativa"));

            fra_tto_001.setCodigoOxtran(request.get("codigoOxtran"));
            fra_tto_001.setCodigoMicrometro(request.get("codigoMicrometro"));

            fra_tto_001.setMascarillaCeldaA(request.get("mascarillaCeldaA"));
            fra_tto_001.setMascarillaCeldaB(request.get("mascarillaCeldaB"));
            fra_tto_001.setPresionBarometrica(request.get("presionBarometrica"));

            fra_tto_001.setObservaciones(request.get("observaciones"));
            fra_tto_001.setRealizo(request.get("realizo"));
            fra_tto_001.setSupervisor(request.get("supervisor"));

            fra_tto_001.setMetodoMuestra(metodoMuestra);

            fra_tto_001_repository.save(fra_tto_001);

            for (int i = 0; i < 2; i++) {
                FRA_TTO_001_DATA fra_tto_001_data = new FRA_TTO_001_DATA();
                fra_tto_001_data.setE1(request.get("e1" + i));
                fra_tto_001_data.setE2(request.get("e2" + i));
                fra_tto_001_data.setE3(request.get("e3" + i));
                fra_tto_001_data.setE4(request.get("e4" + i));
                fra_tto_001_data.setE5(request.get("e5" + i));
                fra_tto_001_data.setCc(request.get("cc" + i));
                fra_tto_001_data.setMol(request.get("mol" + i));

                double promedio = (
                        Double.parseDouble(fra_tto_001_data.getE1()) +
                        Double.parseDouble(fra_tto_001_data.getE2()) +
                        Double.parseDouble(fra_tto_001_data.getE3()) +
                        Double.parseDouble(fra_tto_001_data.getE4()) +
                        Double.parseDouble(fra_tto_001_data.getE5())) / 5;

                fra_tto_001_data.setPromedio(String.format("%.4f", promedio));

                fra_tto_001_data.setFra_tto_001(fra_tto_001);

                fra_tto_001_data_repository.save(fra_tto_001_data);
            }

            fra_tto_001.setPathImagen(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.RUTA_IMG_18_TTO + saveInServer.SaveInServer(file, Constantes.RUTA_IMG_18_TTO));
            fra_tto_001.setRubricaRealizo(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_REALIZO_TTO + saveInServer.SaveInServer(signature, Constantes.SIGNATURE_REALIZO_TTO));

            fra_tto_001.setEstatus("FINALIZADO");
            fra_tto_001.setCantidadModificaciones("3");

            fra_tto_001_repository.save(fra_tto_001);

            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        return fra_18_tto_print.crearFormato(id,1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        return fra_18_tto_print.crearFormato(id,2);
    }

    @RequestMapping(value = "/imprimir3", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir3() throws Exception {

        return lff_mie_met_xx_print.crearListaFolios("18-LFF-MIE-MET-TTO-001", 66L);
    }
}
