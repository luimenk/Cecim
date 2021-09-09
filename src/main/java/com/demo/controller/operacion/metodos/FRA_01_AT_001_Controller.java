package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.fra01at.FRA_AT_001;
import com.demo.service.DropboxService;
import com.demo.service.formatos.metodos.FRA_01_AT_Print;
import com.demo.service.formatos.metodos.listas.LFF_MIE_MET_XX_Print;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.metodos.*;
import com.demo.utils.Constantes;
import com.demo.utils.SaveInServer;
//import com.dropbox.core.v2.DbxClientV2;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;
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
@RequestMapping(path = "/FRAAT")
public class FRA_01_AT_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_AT_001_Service fra_at_001_service;

    @Autowired
    private FRA_01_AT_Print fra_01_at_print;

    @Autowired
    private LFF_MIE_MET_XX_Print lff_mie_met_xx_print;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

//    @Autowired
//    DropboxService dropboxService;

//    @Autowired
//    DbxClientV2 dropboxClient;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_AT_001> getAll() {
        return fra_at_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_AT_001 get(@PathVariable("id") Long id) {
        FRA_AT_001 fra_at_001 = fra_at_001_service.findById(id);

        if (fra_at_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_at_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestPart("fraat") Map<String, String> request,
                                    @RequestPart("imagen") MultipartFile file,
                                    @RequestPart("imagen2") MultipartFile file2,
                                    @RequestPart("imagen3") MultipartFile file3,
                                    @RequestPart("signature") MultipartFile file4,
                                    Principal principal) {

        FRA_AT_001 fra_at_001 = new FRA_AT_001();
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        SaveInServer saveInServer = new SaveInServer();

        try {
            fra_at_001.setFolioTecnica(request.get("folioTecnica"));
            fra_at_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
            fra_at_001.setIdInternoMuestra(request.get("idInternoMuestra"));
            fra_at_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
            fra_at_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
            fra_at_001.setTemperatura(request.get("temperatura"));
            fra_at_001.setHumedadRelativa(request.get("humedadRelativa"));

            fra_at_001.setDesprendimiento1(request.get("desprendimiento1"));
            fra_at_001.setDesprendimiento2(request.get("desprendimiento2"));
            fra_at_001.setDesprendimiento3(request.get("desprendimiento3"));

            fra_at_001.setAtp(request.get("atp"));

            /***** INICIO DE PRUEBA PARA GUARDAR EN DROPBOX *****/
//            Date date = new Date() ;
//            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
//            String fileName=date.getTime()+"."+extension;
//            dropboxService.uploadFile(file, "/abc/"+fileName);
            /***** FIN DE PRUEBA PARA GUARDAR EN DROPBOX *****/


            //dropboxService.getDropboxItems("/abc/1627686166050.jpg");

            fra_at_001.setZona1(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.RUTA_IMG_01_AT + saveInServer.SaveInServer(file, Constantes.RUTA_IMG_01_AT));
            fra_at_001.setZona2(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.RUTA_IMG_01_AT + saveInServer.SaveInServer(file2, Constantes.RUTA_IMG_01_AT));
            fra_at_001.setZona3(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.RUTA_IMG_01_AT + saveInServer.SaveInServer(file3, Constantes.RUTA_IMG_01_AT));
            fra_at_001.setRubricaRealizo(Constantes.PROTOCOLO + Constantes.SERVER + Constantes.CLIENTE + Constantes.SIGNATURE_REALIZO_AT + saveInServer.SaveInServer(file4, Constantes.SIGNATURE_REALIZO_AT));

            fra_at_001.setObservaciones(request.get("observaciones"));
            fra_at_001.setRealizo(request.get("realizo"));
            fra_at_001.setSupervisor(request.get("supervisor"));

            fra_at_001.setEstatus("FINALIZADO");
            fra_at_001.setCantidadModificaciones("3");

            fra_at_001.setMetodoMuestra(metodoMuestra);
            fra_at_001_service.save(fra_at_001);

            metodoMuestra.setEstatus("OK");
            metodoMuestraService.save(metodoMuestra);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_AT a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_01_at_print.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_AT a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_01_at_print.crearFormato(id, 2);
    }

    @RequestMapping(value = "/imprimir3", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir3() throws Exception {

        return lff_mie_met_xx_print.crearListaFolios("01-LFF-MIE-MET-AT-001", 31L);
    }
}
