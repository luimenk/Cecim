package com.demo.controller;

//import com.pmisys.admin.api.service.TokenValidator;

import com.demo.model.AppUser;
import com.demo.model.Client;
import com.demo.model.Method;
import com.demo.service.ClientService;
import com.demo.service.MethodService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.demo.model.Etiqueta;
import com.demo.service.EtiquetaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/etiqueta")
public class EtiquetaController {

    @Autowired
    private EtiquetaService etiquetaService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private MethodService methodService;

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<Etiqueta> getAll() {
        System.out.println("Entró");
        return etiquetaService.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{etiquetaId}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public Etiqueta get(@PathVariable("etiquetaId") Long etiquetaId) {
        Etiqueta method = etiquetaService.findById(etiquetaId);

        if (method == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (method.getEtiquetaId() == null) {
            method.setDescripcionMuestra("");
        }

        return method;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {
        //System.out.println(Long.parseLong(request.get("etiquetaId")));
        System.out.println(request.get("etiquetaId"));
        Client client = clientService.findById(Long.parseLong(request.get("empresa")));
        Method method = methodService.findById(Long.parseLong(request.get("metodo")));
        Etiqueta etiqueta = new Etiqueta();
        ///etiqueta.setEtiquetaId(Long.parseLong(request.get("etiquetaId")));
        etiqueta.setFecha(request.get("fecha"));
        etiqueta.setClient(client);
        etiqueta.setDescripcionMuestra(request.get("descripcionMuestra"));
        etiqueta.setTipoMaterial(request.get("tipoMaterial"));
        etiqueta.setCantidadMuestra(request.get("cantidadMuestra"));
        etiqueta.setLote(request.get("lote"));
        etiqueta.setMethod(method);
        etiqueta.setObservaciones(request.get("observaciones"));

        etiquetaService.save(etiqueta);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //EliminarElemento
    @RequestMapping(method = RequestMethod.DELETE, value = "/{etiquetaId}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE})
    public void delete(@PathVariable("etiquetaId") Long etiquetaId){
        etiquetaService.delete(etiquetaId);
    }
}
