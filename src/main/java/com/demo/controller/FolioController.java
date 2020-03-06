package com.demo.controller;

import com.demo.model.Folios;
import com.demo.service.FoliosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/folio")
public class FolioController {

    @Autowired
    private FoliosService foliosService;

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<Folios> getAll() {
        System.out.println("Entró");
        return foliosService.findAll();
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public Folios create(@RequestBody Map<String, String> request) throws Exception {
        Folios folios = new Folios();

        folios.setNombreFolio(request.get("nombreFolio"));
        folios.setConsecutivo(request.get("consecutivo"));

        return foliosService.save(folios);
    }
}
