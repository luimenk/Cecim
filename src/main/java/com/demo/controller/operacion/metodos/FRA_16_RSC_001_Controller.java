package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra16rsc.FRA_RSC_001;
import com.demo.model.operacion.metodos.fra16rsc.datas.FRA_RSC_001_DATA;
import com.demo.repository.operacion.metodos.fra16rsc.FRA_RSC_001_Repository;
import com.demo.repository.operacion.metodos.fra16rsc.datas.FRA_RSC_001_DATA_Repository;
import com.demo.service.formatos.metodos.FRA_16_RSC_Print;
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
import java.util.*;

@RestController
@RequestMapping(path = "/FRARSC")
public class FRA_16_RSC_001_Controller {

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_RSC_001_Repository fra_rsc_001_repository;

    @Autowired
    private FRA_RSC_001_DATA_Repository fra_rsc_001_data_repository;

    @Autowired
    private FRA_16_RSC_Print fra_16_rsc_print;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_RSC_001> getAll() {
        return fra_rsc_001_repository.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_RSC_001 get(@PathVariable("id") Long id) {
        FRA_RSC_001 fra_rsc_001 = fra_rsc_001_repository.findByIdFRARSC(id);

        if (fra_rsc_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_rsc_001;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/idInterno/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_RSC_001 getInterno(@PathVariable("id") String id) {
        FRA_RSC_001 fra_rsc_001 = fra_rsc_001_repository.findByIdInternoMuestra(id);

        if (fra_rsc_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_rsc_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestPart("frarsc") Map<String, String> request,
                                    @RequestPart("imagen") MultipartFile file,
                                    @RequestPart("signature") MultipartFile signature,
                                    Principal principal) {

        FRA_RSC_001 fra_rsc_001 = new FRA_RSC_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        SaveInServer saveInServer = new SaveInServer();

        try {

            fra_rsc_001.setFolioTecnica(request.get("folioTecnica"));
            fra_rsc_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
            fra_rsc_001.setIdInternoMuestra(request.get("idInternoMuestra"));
            fra_rsc_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_rsc_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_rsc_001.setTemperatura(request.get("temperatura"));
            fra_rsc_001.setHumedadRelativa(request.get("humedadRelativa"));

            fra_rsc_001.setCodigoLaboratorio(request.get("codigoLaboratorio"));
            fra_rsc_001.setCodigoMicrometro(request.get("codigoMicrometro"));

            fra_rsc_001.setAnchoMuestra(request.get("anchoMuestra"));
            fra_rsc_001.setAnchoMordazas(request.get("anchoMordazas"));
            fra_rsc_001.setTipoMordazas(request.get("tipoMordazas"));
            fra_rsc_001.setPresion(request.get("presion"));

            fra_rsc_001.setTiempoSellado(request.get("tiempoSellado"));
            fra_rsc_001.setTiempoRetardo(request.get("tiempoRetardo"));
            fra_rsc_001.setVelocidadDesprendimiento(request.get("velocidadDesprendimiento"));

            fra_rsc_001.setTemperaturaSellado1(request.get("temperaturaSellado1"));
            fra_rsc_001.setTemperaturaSellado2(request.get("temperaturaSellado2"));
            fra_rsc_001.setTemperaturaSellado3(request.get("temperaturaSellado3"));
            fra_rsc_001.setTemperaturaSellado4(request.get("temperaturaSellado4"));
            fra_rsc_001.setTemperaturaSellado5(request.get("temperaturaSellado5"));
            fra_rsc_001.setTemperaturaMordazaSuperior1(request.get("temperaturaMordazaSuperior1"));
            fra_rsc_001.setTemperaturaMordazaSuperior2(request.get("temperaturaMordazaSuperior2"));
            fra_rsc_001.setTemperaturaMordazaSuperior3(request.get("temperaturaMordazaSuperior3"));
            fra_rsc_001.setTemperaturaMordazaSuperior4(request.get("temperaturaMordazaSuperior4"));
            fra_rsc_001.setTemperaturaMordazaSuperior5(request.get("temperaturaMordazaSuperior5"));
            fra_rsc_001.setTemperaturaMordazaInferior1(request.get("temperaturaMordazaInferior1"));
            fra_rsc_001.setTemperaturaMordazaInferior2(request.get("temperaturaMordazaInferior2"));
            fra_rsc_001.setTemperaturaMordazaInferior3(request.get("temperaturaMordazaInferior3"));
            fra_rsc_001.setTemperaturaMordazaInferior4(request.get("temperaturaMordazaInferior4"));
            fra_rsc_001.setTemperaturaMordazaInferior5(request.get("temperaturaMordazaInferior5"));
            fra_rsc_001.setTasaIncrementoTemperaturaMordaza(request.get("tasaIncrementoTemperaturaMordaza"));

            fra_rsc_001.setEjeAnalisis(request.get("ejeAnalisis"));
            fra_rsc_001.setCaraAnalisis(request.get("caraAnalisis"));
            fra_rsc_001.setTipoMaterial(request.get("tipoMaterial"));

            fra_rsc_001.setObservaciones(request.get("observaciones"));
            fra_rsc_001.setRealizo(request.get("realizo"));
            fra_rsc_001.setSupervisor(request.get("supervisor"));

            fra_rsc_001.setMetodoMuestra(metodoMuestra);

            fra_rsc_001_repository.save(fra_rsc_001);

            List<Double> listaPromedioFuerzaSello = new ArrayList<>();
            List<Double> listaPromedioDesvEstandar = new ArrayList<>();

            for (int i = 1; i < 6; i++) {
                FRA_RSC_001_DATA fra_rsc_001_data = new FRA_RSC_001_DATA();
                fra_rsc_001_data.setTemperatura(request.get(i + "temperatura"));
                fra_rsc_001_data.setFuerzaSello1(request.get(i + "fuerzaSello1"));
                fra_rsc_001_data.setDesviacionEstandar1(request.get(i + "desviacionEstandar1"));
                fra_rsc_001_data.setModoFalla1(request.get(i + "modoFalla1"));
                fra_rsc_001_data.setFuerzaSello2(request.get(i + "fuerzaSello2"));
                fra_rsc_001_data.setDesviacionEstandar2(request.get(i + "desviacionEstandar2"));
                fra_rsc_001_data.setModoFalla2(request.get(i + "modoFalla2"));
                fra_rsc_001_data.setFuerzaSello3(request.get(i + "fuerzaSello3"));
                fra_rsc_001_data.setDesviacionEstandar3(request.get(i + "desviacionEstandar3"));
                fra_rsc_001_data.setModoFalla3(request.get(i + "modoFalla3"));

                double prom1 = (Double.parseDouble(fra_rsc_001_data.getFuerzaSello1()) +
                                Double.parseDouble(fra_rsc_001_data.getFuerzaSello2()) +
                                Double.parseDouble(fra_rsc_001_data.getFuerzaSello3())) / 3;

                double prom2 = (Double.parseDouble(fra_rsc_001_data.getDesviacionEstandar1()) +
                                Double.parseDouble(fra_rsc_001_data.getDesviacionEstandar2()) +
                                Double.parseDouble(fra_rsc_001_data.getDesviacionEstandar3())) / 3;

                listaPromedioFuerzaSello.add(prom1);
                listaPromedioDesvEstandar.add(prom2);

                fra_rsc_001_data.setFra_rsc_001(fra_rsc_001);

                fra_rsc_001_data_repository.save(fra_rsc_001_data);
            }

            fra_rsc_001.setPromedioFuerzaSello1(String.format("%.3f", listaPromedioFuerzaSello.get(0)));
            fra_rsc_001.setPromedioFuerzaSello2(String.format("%.3f", listaPromedioFuerzaSello.get(1)));
            fra_rsc_001.setPromedioFuerzaSello3(String.format("%.3f", listaPromedioFuerzaSello.get(2)));
            fra_rsc_001.setPromedioFuerzaSello4(String.format("%.3f", listaPromedioFuerzaSello.get(3)));
            fra_rsc_001.setPromedioFuerzaSello5(String.format("%.3f", listaPromedioFuerzaSello.get(4)));

            fra_rsc_001.setPromedioDesvEstandar1(String.format("%.3f", listaPromedioDesvEstandar.get(0)));
            fra_rsc_001.setPromedioDesvEstandar2(String.format("%.3f", listaPromedioDesvEstandar.get(1)));
            fra_rsc_001.setPromedioDesvEstandar3(String.format("%.3f", listaPromedioDesvEstandar.get(2)));
            fra_rsc_001.setPromedioDesvEstandar4(String.format("%.3f", listaPromedioDesvEstandar.get(3)));
            fra_rsc_001.setPromedioDesvEstandar5(String.format("%.3f", listaPromedioDesvEstandar.get(4)));

            fra_rsc_001.setPathImagen(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.RUTA_IMG_16_RSC + saveInServer.SaveInServer(file, Constantes.RUTA_IMG_16_RSC));
            fra_rsc_001.setRubricaRealizo(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_REALIZO_RSC + saveInServer.SaveInServer(signature, Constantes.SIGNATURE_REALIZO_RSC));

            fra_rsc_001.setEstatus("FINALIZADO");
            fra_rsc_001.setCantidadModificaciones("3");

            fra_rsc_001_repository.save(fra_rsc_001);

            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        return fra_16_rsc_print.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        return fra_16_rsc_print.crearFormato(id, 2);
    }
}
